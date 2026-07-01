package com.example.demo.controller;

import com.example.demo.model.Topico;
import com.example.demo.model.Usuario;
import com.example.demo.service.TopicoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    private TopicoService topicoService = new TopicoService();

    @GetMapping("/todos")
    public String getTodosTopicos(@RequestParam(required = false) String acao,
                                  @RequestParam(required = false) Integer id,
                                  @RequestParam(required = false) String msg,
                                  HttpSession session,
                                  Model model) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        List<Topico> topicos = topicoService.getTopicos();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "todos");
        model.addAttribute("msg", msg);

        return "topicos";
    }
    @GetMapping("/recentes")
    public String getRecentesTopicos(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        List<Topico> topicos = topicoService.getTopicosRecentes();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "recentes");
        return "topicos";
    }

    @GetMapping("/em-aberto")
    public String getEmAbertoTopicos(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        List<Topico> topicos = topicoService.getTopicosEmAberto();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "em-aberto");
        return "topicos";
    }
    @GetMapping("/by-usuario-logado")
    public String getByUsuarioLogadoTopicos(@RequestParam(required = false) String msg, HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        List<Topico> topicos = topicoService.getTopicosByIdAutor(usuarioLogado.getIdUsuario());
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "topicosByUsuarioLogado");
        if (msg != null) model.addAttribute("msg", msg);
        return "topicos";
    }

    @GetMapping("/resolvidos")
    public String getResolvidosTopicos(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        List<Topico> topicos = topicoService.getTopicosResolvidos();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "resolvidos");
        return "topicos";
    }

    @GetMapping("/resolver")
    public String resolverTopico(@RequestParam(required = true) int idTopico, HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        topicoService.resolverTopico(idTopico);
        model.addAttribute("msg", "resolvido");
        List<Topico> topicos = topicoService.getTopicosResolvidos();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "resolvidos");
        return "topicos";
    }

    @GetMapping("/reabrir")
    public String reabrirTopico(@RequestParam(required = true) int idTopico, HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        topicoService.reabrirTopico(idTopico);
        model.addAttribute("msg", "reaberto");
        List<Topico> topicos = topicoService.getTopicosEmAberto();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "em-aberto");
        return "topicos";
    }

    @GetMapping("/excluir")
    public String excluirTopico(@RequestParam(required = true) int idTopico, HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        topicoService.excluirTopicoById(idTopico);
        model.addAttribute("msg", "excluido");
        List<Topico> topicos = topicoService.getTopicos();
        model.addAttribute("topicos", topicos);
        model.addAttribute("selecionado", "todos");
        return "topicos";
    }



}
