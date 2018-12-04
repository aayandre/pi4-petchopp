package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.daos.EnderecoDAO;
import com.senac.petchopp.daos.VendaDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.cliente.Endereco;
import com.senac.petchopp.model.venda.Venda;
import com.senac.petchopp.service.ClienteService;
import com.senac.petchopp.service.LoginService;
import org.mindrot.jbcrypt.BCrypt;

@Controller
@RequestMapping("cliente")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ClienteController {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private ClienteService service = new ClienteService();

    @GetMapping("conta")
    public String minhaConta() {
        return "cli/cliente-index";
    }

    @GetMapping("Cadastro")
    public ModelAndView novoCliente(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cli/novoCliente");
        modelAndView.addObject("cliente", new Cliente());
        modelAndView.addObject("endereco", new Endereco());
        System.out.println(session.getId());
        return modelAndView;
    }

    @PostMapping("salvar")
    public ModelAndView salvar(Cliente cliente, @RequestParam(value = "dtNasc") String dtNasc,
            Endereco endereco,
            RedirectAttributes redirect) throws SQLException, Exception {
        ModelAndView modelAndView = new ModelAndView("cli/login");
        LocalDate stringToLocalDateParse = Auxiliares.stringToLocalDateParse(dtNasc);
        cliente.addEnderecoToList(endereco);
        try {
            cliente.setDtCadastro(LocalDate.now());
            cliente.setDtNasc(stringToLocalDateParse);
            cliente.setAtivo(true);
            clienteDAO.salvar(cliente);
            String msg = "Cliente cadastrado com sucesso";
//			System.out.println("Usuário cadastrado = " + cliente.getNome());
//			System.out.println("senha = " + cliente.getSenha());
            redirect.addAttribute("msg", msg);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView.addObject("redirect:cliente");
    }

    @GetMapping("minhaconta")
    public String minhaconta() {
        return "cli/cliente-index";
    }

    @PostMapping("/minhaconta")
    public ModelAndView atualizar(HttpSession session, @RequestParam(value = "nome") String nome,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "cel") String telefone1,
            @RequestParam(value = "tel") String telefone2) {
        ModelAndView modelAndView = new ModelAndView("cli/cliente-index");

        Cliente cli = (Cliente) session.getAttribute("cliente");

        try {

            if (!cli.getNome().equals(nome)) {
                cli.setNome(nome);
            }
            if (!cli.getEmail().equals(email)) {
                cli.setEmail(email);
            }
            if (!cli.getTelefone1().equals(telefone1)) {
                cli.setTelefone1(telefone1);
            }
            if (cli.getTelefone2().isEmpty() || cli.getTelefone2().equals("") || !cli.getTelefone2().equals(telefone2)) {
                cli.setTelefone2(telefone2);
            }
            System.out.println("ID do Cliente" + cli.getIdCliente());
            service.atualizar(cli);
        } catch (Exception e) {
            System.out.println("Erro ao atualiza Cliente");
        }

        return modelAndView;
    }

    @GetMapping("login")
    public ModelAndView loginCliente() {
        ModelAndView modelAndView = new ModelAndView("cli/login");
        return modelAndView;
    }

    @PostMapping("logon")
    public ModelAndView logonUsurio(@RequestParam(value = "email") String email, @RequestParam(value = "password") String senha,
            RedirectAttributes redirect, HttpSession session) throws SQLException {

        Cliente cliente = new LoginService().clienteLogon(email, senha);
//        System.out.println(cliente.isLogado());

        if (cliente.isLogado()) {
            System.out.println("Cliente logado: " + cliente.getEmail());
            session.setAttribute("cliente", cliente);
            return new ModelAndView("index");
        }

        return new ModelAndView("cli/login");
    }

    @GetMapping("orders")
    public ModelAndView visualizarPedidos(@SessionAttribute("cliente") Cliente cliente) throws SQLException {
        VendaDAO vendaDAO = new VendaDAO();

        List<Venda> vendas = vendaDAO.getVendasByCliente(cliente.getIdCliente());
        ModelAndView modelAndView = new ModelAndView("cli/orderList");
        modelAndView.addObject("vendas", vendas);

        return modelAndView;
    }

    @GetMapping("logoff")
    public ModelAndView logoffCliente(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        session.setAttribute("cliente", new Cliente());
        session.invalidate();
        return modelAndView;
    }

//    ENDERECOS
    @GetMapping("enderecos")
    public ModelAndView enderecos(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cli/enderecos");
        Cliente cli = (Cliente) session.getAttribute("cliente");
        List<Endereco> enderecos = cli.getEnderecos();
        modelAndView.addObject("enderecos", enderecos);

        return modelAndView;
    }

    @GetMapping("novoendereco")
    public ModelAndView endereco() {
        ModelAndView modelAndView = new ModelAndView("cli/enderecoForm");
        modelAndView.addObject("endereco", new Endereco());
        return modelAndView;
    }

    @PostMapping("enderecos")
    public ModelAndView novoEndereco(HttpSession session, Endereco end) {
        ModelAndView modelAndView = new ModelAndView("redirect:enderecos");
        Cliente cli = (Cliente) session.getAttribute("cliente");
        List<Endereco> enderecos = (List<Endereco>) session.getAttribute("enderecos");
        try {
            end.setAtivo(true);
            enderecoDAO.salvarNovoEndereco(end, cli.getIdCliente());
            cli.getEnderecos().add(end);
        } catch (SQLException e) {
        }
        return modelAndView;
    }
    
    @PostMapping("excluirendereco")
    public ModelAndView deleteEnd(@RequestParam(value = "id") String idEndereco
                                    , HttpSession session){
        ModelAndView modelAndView = new ModelAndView("redirect:enderecos");
        String msg = "Endereço removido com sucesso";
        Cliente cli = (Cliente) session.getAttribute("cliente");
        Long id = Long.parseLong(idEndereco);
        try {
            enderecoDAO.deletar(id);
            for(int i = 0; i<cli.getEnderecos().size(); i++){
                Endereco end = cli.getEnderecos().get(i);
                if(end.getIdEndereco() == id){
                    cli.getEnderecos().remove(i);
                }
                modelAndView.addObject("msg", msg);
            }
        } catch (Exception e) {
        }
        return modelAndView;
    }

    //  FIM  ENDERECOS
    @PostMapping("alterarsenha")
    public ModelAndView alterPass(@RequestParam(value = "passNew") String novaSenha,
             @RequestParam(value = "passNewConf") String novaSenhaConf,
             HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:minhaconta");
        Cliente cli = (Cliente) session.getAttribute("cliente");
        String aux;
        if (novaSenha.equals(novaSenhaConf)) {
            aux = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
            clienteDAO.atualizarSenha(cli.getIdCliente(), aux);
            System.out.println("Entrando no alterar senha");
            System.out.println(cli.getSenha());
//                modelAndView.addObject("cliente", cli);
        } else {
            modelAndView.addObject("msg", "Senha inválida!");
        }

        return modelAndView;
    }

}
