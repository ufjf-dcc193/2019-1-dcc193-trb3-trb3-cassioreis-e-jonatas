package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    @RequestMapping(value = {"/detalhe"}, method = RequestMethod.GET)
    public ModelAndView detalhe (@RequestParam("id") Long id)
    {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = usuarioRepository.getOne(id);
        mv.addObject("usuario", usuario);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.setViewName("usuario-detalhe");
        return mv;
    }

    @RequestMapping(value = {"/novo"}, method = RequestMethod.GET)
    public ModelAndView novo ()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.addObject("isInsert", true);
        mv.setViewName("usuario-detalhe");
        return mv;
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvar (@RequestParam(value = "id", required = true) Long id, @Valid Usuario usuario,BindingResult binding)
    {
        boolean isInsert = usuario.getId()==null;
        ModelAndView mv = new ModelAndView();
        List<ObjectError> errors =  binding.getAllErrors();
        if (binding.hasErrors()) {
            String fieldError = "";
            if (errors.size()==1){
                fieldError = ((FieldError) errors.get(0)).getField();
            }
            if ((!fieldError.equals("password")) || isInsert){
                mv.setViewName("usuario-detalhe");
                mv.addObject("usuario", usuario);            
                mv.addObject("isInsert", isInsert);            
                return mv;
            }
        }
        Usuario current;
        if (isInsert)
            current = new Usuario();
        else
            current = usuarioRepository.getOne(id);
        current.setDescricao(usuario.getDescricao());
        current.setNome(usuario.getNome());
        current.setEmail(usuario.getEmail());
        if (usuario.getPassword().length()>0)
            current.setPassword(usuario.getPassword());
        usuarioRepository.save(current);
        mv.setViewName("redirect:listar");
        return mv;
    }
    

    @RequestMapping(value = { "/excluir" }, method = RequestMethod.GET)
    public ModelAndView excluir(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Usuario currentUser = Helper.getUsuarioLogado(request, usuarioRepository);
        if (currentUser.getId().equals(id)){
            mv.addObject("errorMessage","Não é possível excluir o usuário logado");
            List<Usuario> usuarios = usuarioRepository.findAll();
            mv.addObject("usuarios", usuarios);
            mv.setViewName("usuario-listar");
            
        }
        else{
            usuarioRepository.deleteById(id);
            mv.setViewName("redirect:listar");
        }
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
    
}