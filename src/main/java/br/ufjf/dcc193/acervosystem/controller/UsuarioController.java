package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;



@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = {"/lista-usuarios"}, method = RequestMethod.GET)
    public ModelAndView carregaUsuarios ()
    {
        ModelAndView mv = new ModelAndView();
        List<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);
        mv.setViewName("lista-usuarios");
        return mv;
    }

    @RequestMapping(value = {"/cadastro-usuario"}, method = RequestMethod.GET)
    public ModelAndView cadastroUsuario ()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cadastro-usuario");
        return mv;
    }

    @RequestMapping(value = {"/cadastro-usuario"}, method = RequestMethod.POST)
    public ModelAndView realizaCadastro (Usuario usuario)
    {
        ModelAndView mv = new ModelAndView();
        usuarioRepository.save(usuario);
        mv.setViewName("redirect:lista-usuarios");
        return mv;
    }    
    
    @RequestMapping(value = {"/editar-usuario/{id}"}, method = RequestMethod.GET)
    public ModelAndView carregaEditar (@PathVariable(value = "id", required = true) Long id)
    {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = usuarioRepository.getOne(id);
        mv.addObject("usuario", usuario);
        mv.addObject("id", id);
        mv.setViewName("editar-usuario");
        return mv;
    }

    @RequestMapping(value = {"/editar-usuario"}, method = RequestMethod.POST)
    public ModelAndView realizaEditar (@RequestParam(value = "id", required = true) Long id, Usuario usuario)
    {
        ModelAndView mv = new ModelAndView();
        usuario.setId(id);
        usuarioRepository.save(usuario);
        mv.addObject("usuario", usuario);
        mv.setViewName("editar-usuario");
        return mv;
    }     

    @RequestMapping(value = { "/excluir-usuario/{id}" }, method = RequestMethod.GET)
    public ModelAndView carregaExcluir(@PathVariable(value = "id", required = true) Long id) {
        ModelAndView mv = new ModelAndView();
        usuarioRepository.deleteById(id);
        mv.setViewName("redirect:/lista-usuarios");
        return mv;    
    }
    
}