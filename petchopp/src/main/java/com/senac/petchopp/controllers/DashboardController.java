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
import javax.servlet.http.HttpSession;

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
            modelAndView = new ModelAndView("redirect:home");
            session.setAttribute("usuario", usuario);
        } else {
            modelAndView = new ModelAndView("redirect:login");
        }

        return modelAndView;
    }

    @RequestMapping("home")
    public ModelAndView home(@ModelAttribute("usuario") Usuario admin) {
//		admin.setNome("Leonildo"); // pro teste
        if (admin.isLogado()) {
            return new ModelAndView("/dashboard/dashboard-home").addObject("admin", admin).addObject("titulo", "PetChopp - Dashboard");
        } else {
            return new ModelAndView("redirect:login").addObject("msg", "Usuario não tem permissão para acessar esta área!"
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

}
