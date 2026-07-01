package com.example.demo.service;


import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Usuario;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public boolean inserirUsuario(Usuario u) {
        return dao.inserirUsuario(u);
    }

    public boolean isNomeOcupado(Usuario u) {
        return dao.isNomeOcupado(u);
    }
    public Usuario getUsuarioById(int id) { return dao.getUsuarioById(id); }

}
