package br.ufjf.dcc193.acervosystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.acervosystem.helper.Helper;
import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;


@Controller
public class HomeController {

    @Autowired
    UsuarioRepository usuarioRepository;

    
    @RequestMapping({ "", "/", "/index" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = Helper.getUsuarioLogado(request,usuarioRepository);
        if (usuario !=null)
            mv.setViewName("index");
        else
            mv.setViewName("index-nologged");
        return mv;
    }

    

    @RequestMapping({ "/login" })
    public ModelAndView Login() {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = new Usuario();
        usuario.setNome("noBlank");
        mv.addObject("usuario", usuario);
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping({ "/logout" })
    public ModelAndView Logout(HttpServletResponse response) {
        Helper.logout(response);
        ModelAndView mv = new ModelAndView();
        Usuario usuario = new Usuario();
        usuario.setNome("noBlank");
        mv.addObject("usuario", usuario);
        mv.setViewName("login");
        return mv;
    }

    

    @PostMapping(value = "/do-login")
    public ModelAndView EfetuarLogin(@Valid Usuario usuario, BindingResult binding, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("login");
            mv.addObject("usuario", usuario);
            return mv;
        }
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user == null)
            mv.addObject("errorMessage", "Usuário inválido");
        else if (user.getPassword().equals(usuario.getPassword())){
            Helper.doLogin(response,user.getId());
            mv.addObject("errorMessage", "");
            mv.setViewName("index");
            return mv;
        }
        else{
            mv.addObject("errorMessage", "Usuário inválido");
        }
        mv.setViewName("login");
        mv.addObject("usuario", usuario);
        return mv;
    }
}