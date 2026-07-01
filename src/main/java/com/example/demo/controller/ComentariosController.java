package com.example.demo.controller;

import com.example.demo.model.Comentario;
import com.example.demo.model.Topico;
import com.example.demo.model.Usuario;
import com.example.demo.service.ComentarioService;
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

import java.util.List;

@Controller
@RequestMapping("/comentarios")
public class ComentariosController {

    private TopicoService topicoService = new TopicoService();
    private ComentarioService comentarioService = new ComentarioService();
    private UsuarioService usuarioService = new UsuarioService();

    @GetMapping
    public String getComentarios(@RequestParam Integer idTopico, @RequestParam(required = false) String msg, HttpSession session, Model model) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Topico t = topicoService.getTopicoById(idTopico);
        List<Comentario> c = comentarioService.getComentariosByTopico(t);

        model.addAttribute("topico", t);
        model.addAttribute("comentarios", c);
        if(msg != null) model.addAttribute("msg", msg);

        return "comentarios";
    }

    @PostMapping
    public String salvarComentario(@RequestParam Integer idTopico,
                                   @RequestParam String corpoComentario,
                                   HttpSession session, RedirectAttributes redirectAttrs) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Usuario u = (Usuario) session.getAttribute("usuario");

        Topico t = topicoService.getTopicoById(idTopico);

        comentarioService.salvarComentario(new Comentario(u, t, corpoComentario));

        redirectAttrs.addAttribute("msg", "criado");

        return "redirect:/comentarios?idTopico=" + idTopico;
    }
    @GetMapping("/excluir")
    public String excluirComentario(@RequestParam Integer idTopico, @RequestParam Integer idComentario, HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        comentarioService.excluirComentario(idComentario);

        redirectAttributes.addAttribute("msg", "excluido");
        return "redirect:/comentarios?idTopico=" + idTopico;
    }
    @PostMapping("/editar")
    public String editarComentario(@RequestParam Integer idTopico, @RequestParam Integer idComentario, @RequestParam String corpoComentario , HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Usuario u = (Usuario) session.getAttribute("usuario");
        Topico t = topicoService.getTopicoById(idTopico);
        Comentario c = new Comentario(u, t, corpoComentario);
        c.setIdComentario(idComentario);

        comentarioService.updateComentario(c);

        redirectAttributes.addAttribute("msg", "editado");

        return "redirect:/comentarios?idTopico=" + idTopico;
    }
    @GetMapping("/get-editar-comentario")
    public String getEditarComentario(@RequestParam Integer idComentario,
                                      @RequestParam Integer idTopico,
                                      HttpSession session, Model model) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        Usuario u = (Usuario) session.getAttribute("usuario");


        Comentario c = comentarioService.getComentarioById(idComentario);
        Topico t = topicoService.getTopicoById(idTopico);
        c.setTopico(t);
        c.setAutor(usuarioService.getUsuarioById(u.getIdUsuario()));

        model.addAttribute("comentario", c);

        return "editarComentario";
    }
}
