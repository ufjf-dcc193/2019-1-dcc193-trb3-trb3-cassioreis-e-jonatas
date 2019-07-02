package br.ufjf.dcc193.tbr3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.tbr3.model.Etiqueta;
import br.ufjf.dcc193.tbr3.repository.EtiquetaRepository;



@Controller
public class EtiquetaController {

    @Autowired
    private EtiquetaRepository repositoryEtiqueta;
   

    @RequestMapping(value = { "/excluir-etiqueta/{id}" }, method = RequestMethod.GET)
    public ModelAndView carregaExcluirItemEtiqueta(@PathVariable(value = "id", required = true) Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        repositoryEtiqueta.deleteById(id);
        return mv;    
    }

    
    @RequestMapping(value = {"/cria-etiqueta/"}, method = RequestMethod.GET)
    public ModelAndView editaretiqueta ()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("criar-etiqueta");
        return mv;
    }


    @RequestMapping(value = {"/editar-etiqueta/{id}"}, method = RequestMethod.GET)
    public ModelAndView editaretiqueta (@PathVariable(value = "id", required = true) Long id, HttpSession session)
    {
        ModelAndView mv = new ModelAndView();
        Etiqueta etiqueta = repositoryEtiqueta.getOne(id);
        mv.addObject("etiqueta", etiqueta);
        mv.addObject("id2", id);
        mv.setViewName("editar-etiqueta");
        return mv;
    }



}