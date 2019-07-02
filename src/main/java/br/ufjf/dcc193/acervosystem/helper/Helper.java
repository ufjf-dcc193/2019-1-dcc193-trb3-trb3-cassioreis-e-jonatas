package br.ufjf.dcc193.acervosystem.helper;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;

public class Helper {


    public static final String usuarioLogadoCookieName = "loggedUserAcervoSystem";

    public static final String getDataAtualFormatada(){

        String formato = "dd-MM-YY";
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.format(agora);
    }


    public static final Usuario getUsuarioLogado(HttpServletRequest request, UsuarioRepository usuarioRepository){
        String id = "-1";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(usuarioLogadoCookieName)){
                    id = cookie.getValue();
                    return usuarioRepository.getOne(Long.parseLong(id));
                }
            }
        }
        return null;
    }

    public static final void logout(HttpServletResponse response){
        Cookie cookie = new Cookie(usuarioLogadoCookieName, null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

    public static final void doLogin(HttpServletResponse response, Long userId){
        Cookie cookie = new Cookie(usuarioLogadoCookieName, userId+"");

        response.addCookie(cookie);
    } 
}
