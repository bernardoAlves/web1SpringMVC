package com.example.demo.service;


import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Usuario;

public class LoginService {

    public Usuario autenticar(String nome, String senha) {
        try {
            return new UsuarioDAO().autenticar(nome, senha);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
