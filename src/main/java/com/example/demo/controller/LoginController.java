package com.example.demo.controller;

import com.example.demo.dao.LoginService;
import com.example.demo.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService = new LoginService();


    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @PostMapping
    public String autenticar(@RequestParam String nome, @RequestParam String senha, HttpSession session, Model model) {

        Usuario usuario = loginService.autenticar(nome, senha);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            return "redirect:/topicos/todos";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "index";
        }
    }

}
