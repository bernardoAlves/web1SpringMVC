package com.example.demo.controller;

import com.example.demo.model.Topico;
import com.example.demo.model.Usuario;
import com.example.demo.service.TopicoService;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastro-topico")
public class CadastroTopicoController {

    private TopicoService topicoService = new TopicoService();
    private UsuarioService usuarioService = new UsuarioService();

    @GetMapping
    public String getCadastroTopico(@RequestParam (required = false) Integer idTopico,
                                    @RequestParam (required = false) String titulo,
                                    @RequestParam (required = false) String corpoTopico,
                                    HttpSession session,
                                    Model model) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (idTopico != null) {
            model.addAttribute("idTopico", idTopico);
        }
        model.addAttribute("titulo", titulo);
        model.addAttribute("corpoTopico", corpoTopico);
        return "cadastroEdicao";
    }

    @PostMapping
    public String salvarTopico(@RequestParam(required = false) Integer idTopico,
                               @RequestParam(required = false) String titulo,
                               @RequestParam(required = false) String corpoTopico,
                               HttpSession session, RedirectAttributes redirectAttrs) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        Usuario u = (Usuario) session.getAttribute("usuario");

        Topico t = new Topico(
                titulo,
                usuarioService.getUsuarioById(u.getIdUsuario()),
                corpoTopico
        );

        if (idTopico != null) {
            t.setIdTopico(idTopico);
            topicoService.updateTopico(t);
            redirectAttrs.addAttribute("msg", "editado");
        } else {
            topicoService.salvarTopico(t);
            redirectAttrs.addAttribute("msg", "criado");
        }

        return "redirect:/topicos/by-usuario-logado";
    }

}
