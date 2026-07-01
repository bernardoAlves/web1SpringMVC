package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioService();

    @PostMapping
    public String cadastrarUsuario(@RequestParam String nome,
                                   @RequestParam String senha,
                                   Model model) {
        Usuario u = new Usuario(nome, senha);

        boolean feito = usuarioService.inserirUsuario(u);

        if (feito) return "redirect:/login";

        model.addAttribute("erro", "Erro ao cadastrar usuário");
        return "cadastro";
    }

}
