package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.model.retaguarda.Usuario;
import com.senac.petchopp.service.UsuarioService;
import com.senac.petchopp.wos.FormularioProduto;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("dash")
public class DashboardController {

    private ProdutoService produtoService = new ProdutoService();
    UsuarioService servicoUsuario = new UsuarioService();

    @RequestMapping("")
    public ModelAndView telaLogin() {
        return new ModelAndView("/dashboard/dashboard-login");
    }

    @PostMapping("logon")
    public ModelAndView logon(@RequestParam("email") String email,
            @RequestParam("senha") String senha, HttpSession session) {

        ModelAndView modelAndView;
        Usuario usuario = servicoUsuario.efetuaLogin(email, senha);

        if (usuario.isLogado()) {
            session.setAttribute("usuario", usuario);
            modelAndView = new ModelAndView("redirect:home");
        } else {
            modelAndView = new ModelAndView("redirect:logon");
        }

        return modelAndView;
    }

    @RequestMapping("home")
    public ModelAndView home(HttpSession session) {
//		admin.setNome("Leonildo"); // pro teste
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (admin.isLogado()) {
            session.setAttribute("usuario", admin);
            return new ModelAndView("/dashboard/dashboard-home").addObject("admin", admin).addObject("titulo", "PetChopp - Dashboard");
        } else {
            return new ModelAndView("redirect:logon").addObject("msg", "Usuario não tem permissão para acessar esta área!"
                    + "\nContate o administrador do sistema.");
        }
    }

    @RequestMapping("/novoproduto")
    public ModelAndView novoProdutoForm() {
        String msg;
        try {
            FormularioProduto formProd = produtoService.populaFormularioProduto();
            return new ModelAndView("/dashboard/dashboard-produto").addObject("liActivator", "liNewProdId")
                    .addObject("formularioProduto", formProd).addObject("titulo", "PetChopp - Dashboard Novo Produto");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Erro ao criar o FormularioProduto.";
            return new ModelAndView("/dashboard/dashboard-produto").addObject("falha", msg).addObject("titulo", "PetChopp - Dashboard Novo Produto");
        }

    }

    @RequestMapping("/relestoque")
    public ModelAndView relatorioEstoque() {
        return new ModelAndView("/dashboard/relatorio-estoque").addObject("titulo", "PetChopp - Dashboard Estoque Produtos");
    }

    @RequestMapping("/reltransestoque")
    public ModelAndView relatorioTransEstoque() {
        return new ModelAndView("/dashboard/rel-transacoes").addObject("titulo", "PetChopp - Dashboard Transações Estoque");
    }

    @GetMapping("user-manager")
    public ModelAndView managerUser(HttpSession session, Usuario novo) {

        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (admin != null && admin.isLogado()) {
            if (novo.getIdUsuario() == null) {
                System.out.println(admin.getNome() + " - " + admin.isLogado());
                List<Usuario> usuariosList = servicoUsuario.listaUsuarios();
                return new ModelAndView("/dashboard/dashboard-usuario")
                        .addObject("novo", new Usuario())
                        .addObject("usuariosList", usuariosList);
            } else if (servicoUsuario.atualizar(novo)) {
                List<Usuario> usuariosList = servicoUsuario.listaUsuarios();
                new ModelAndView("/dashboard/dashboard-usuario").addObject("usuariosList", usuariosList);
            }
        }
        return new ModelAndView("redirect:logon");
    }
    
    @GetMapping("user-manager-cadastro")
    public ModelAndView novousuario() {
        return new ModelAndView("/dashboard/dashboard-usuario-cadastrar")
                .addObject("novo", new Usuario());
    }
    
    @PostMapping("user-manager-cadastro")
    public ModelAndView userRegister(Usuario novo
                    , @RequestParam(value = "roleUser") String role) {
        ModelAndView modelAndView = new ModelAndView("redirect:user-manager");
        List<Usuario> usuariosList;
        try {
            System.out.println("Entrando no cadastro novo usuario");
            novo.setRole(role);
            novo.setSenha(BCrypt.hashpw(novo.getSenha(), BCrypt.gensalt()));
            if (servicoUsuario.salvar(novo)) {
                usuariosList = servicoUsuario.listaUsuarios();
                modelAndView.addObject("usuariosList", usuariosList);
            }
        } catch (Exception e) {
        }

        return modelAndView.addObject("msg", "Falha ao salvar usuário!");
    }
    
    @PostMapping("user-manager-update")
    public ModelAndView userUpdate(@RequestParam(value = "id") String idUsuario,
            @RequestParam(value = "roleUser") String novaRole,
            @RequestParam(value = "senhaUser") String novaSenha) {
        ModelAndView modelAndView = new ModelAndView("redirect:user-manager");
        List<Usuario> usuariosList;
        
        try {
            Usuario user = servicoUsuario.buscar(Long.parseLong(idUsuario));
            if(novaRole != null){
                user.setRole(novaRole);
            }
            if(novaSenha != null){
                user.setSenha(BCrypt.hashpw(novaSenha, BCrypt.gensalt()));
            }
               if (servicoUsuario.atualizar(user)) {
                usuariosList = servicoUsuario.listaUsuarios();
                modelAndView.addObject("usuariosList", usuariosList);
            }
        } catch (Exception e) {
        }

        return modelAndView.addObject("msg", "Falha ao salvar usuário!");
    }

}
