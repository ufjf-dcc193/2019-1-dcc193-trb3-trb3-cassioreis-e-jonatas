package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.acervosystem.helper.Helper;
import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    public ModelAndView listar ()
    {
        ModelAndView mv = new ModelAndView();
        List<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);
        mv.setViewName("usuario-listar");
        return mv;
    }

    @RequestMapping(value = {"/detalhe/{id}"}, method = RequestMethod.GET)
    public ModelAndView detalhe (@PathVariable(value = "id", required = true) Long id)
    {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = usuarioRepository.getOne(id);
        mv.addObject("usuario", usuario);
        mv.addObject("id", id);
        mv.setViewName("usuario-detalhe");
        return mv;
    }

    @RequestMapping(value = {"/novo"}, method = RequestMethod.GET)
    public ModelAndView novo ()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("usuario-detalhe");
        return mv;
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvar (@Valid Usuario usuario,BindingResult binding)
    {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-detalhe");
            mv.addObject("usuario", usuario);
            return mv;
        }
        usuarioRepository.save(usuario);
        mv.setViewName("redirect:listar");
        return mv;
    }   

    @RequestMapping(value = { "/excluir/{id}" }, method = RequestMethod.GET)
    public ModelAndView carregaExcluir(@PathVariable(value = "id", required = true) Long id) {
        ModelAndView mv = new ModelAndView();
        usuarioRepository.deleteById(id);
        mv.setViewName("redirect:/listar");
        return mv;    
    }

    @RequestMapping(value = {"/cadastrar"}, method = RequestMethod.GET)
    public ModelAndView cadastrarPlataforma ()
    {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = new Usuario();
        mv.addObject("usuario", usuario);
        mv.setViewName("usuario-cadastrar");
        return mv;
    }



    @PostMapping(value = "/salvar-cadastro")
    public ModelAndView novoUsuarioNaPlataforma (@Valid Usuario usuario,BindingResult binding)
    {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-cadastrar");
            mv.addObject("usuario", usuario);
            return mv;
        }
        usuarioRepository.save(usuario);
        mv.setViewName("redirect:/login");
        return mv;
    }    
    
/*
    @RequestMapping(value = {"/editar-usuario"}, method = RequestMethod.POST)
    public ModelAndView realizaEditar (@RequestParam(value = "id", required = true) Long id, Usuario usuario)
    {
        ModelAndView mv = new ModelAndView();
        usuario.setId(id);
        usuarioRepository.save(usuario);
        mv.addObject("usuario", usuario);
        mv.setViewName("editar-usuario");
        return mv;
    }  */
    
}