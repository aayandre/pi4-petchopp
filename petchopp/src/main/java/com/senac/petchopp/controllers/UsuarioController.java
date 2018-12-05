/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.controllers;

import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.retaguarda.Usuario;
import com.senac.petchopp.service.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author macop
 */
@Controller
@RequestMapping("dashboard")
public class UsuarioController {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    UsuarioService servicoUsuario = new UsuarioService();
    
    @GetMapping("home")
    public ModelAndView homeAdmin(HttpSession session){
        ModelAndView modelAndView;
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario !=null && usuario.isLogado()){
            modelAndView = new ModelAndView("dashboard/dashboard-home")
                    .addObject("usuario", usuario);
        }else{
            modelAndView = new ModelAndView("redirect:login");
        }
        return modelAndView;
    }
    
    @GetMapping("login")
    public ModelAndView loginAdmin(){
        return new ModelAndView("dashboard/dashboard-login");
    }
    
    @PostMapping("login")
    public ModelAndView loginAdminDash(@RequestParam(value = "email") String email
            , @RequestParam(value = "senha") String senha, HttpSession session){
        ModelAndView modelAndView; 
        Usuario usuario = servicoUsuario.efetuaLogin(email, senha);
        
        if(usuario.isLogado()){
            modelAndView = new ModelAndView("redirect:home");
            session.setAttribute("usuario", usuario);
        }else{
            modelAndView = new ModelAndView("redirect:login");
        }
        
        return modelAndView;
    }
    
}
