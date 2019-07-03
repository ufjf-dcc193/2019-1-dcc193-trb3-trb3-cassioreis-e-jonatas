package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

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
import br.ufjf.dcc193.acervosystem.model.*;
import br.ufjf.dcc193.acervosystem.repository.*;


@Controller
public class HomeController {

    @Autowired
    UsuarioRepository usuarioRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	EtiquetaRepository etiquetaRepository;
	@Autowired
	AnotacaoRepository anotacaoRepository;

    
    @RequestMapping({ "", "/", "/index" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = Helper.getUsuarioLogado(request,usuarioRepository);
        if (usuario !=null)
            mv.setViewName("index");
        else
            mv.setViewName("index-nologged");

        //mv.addObject("objetos",listarTodosOsObjetos());
        return mv;
    }

    private String listarTodosOsObjetos(){


        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Item> itens = itemRepository.findAll();
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        List<Anotacao> anotacoes = anotacaoRepository.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<BR>");
        sb.append("USUARIOS");
        sb.append("<BR>");
        sb.append("<BR>");
        for (Usuario usuario: usuarios) {
            sb.append(usuario.toString());
            sb.append(";");
        }
        sb.append("<BR>");
        sb.append("ITENS");
        sb.append("<BR>");
        sb.append("<BR>");
        for (Item item: itens) {
            sb.append(item.toString());
            sb.append(";");
        }
        sb.append("<BR>");
        sb.append("ETIQUETAS");
        sb.append("<BR>");
        sb.append("<BR>");
        for (Etiqueta etiqueta: etiquetas) {
            sb.append(etiqueta.toString());
            sb.append(";");
        }
        sb.append("<BR>");
        sb.append("ANOTACOES");
        sb.append("<BR>");
        sb.append("<BR>");
        for (Anotacao anotacao: anotacoes) {
            sb.append(anotacao.toString());
            sb.append(";");
        }
        return sb.toString();
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