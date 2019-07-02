package br.ufjf.dcc193.tbr3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.tbr3.model.Anotacao;
import br.ufjf.dcc193.tbr3.repository.AnotacaoRepository;



@Controller
@RequestMapping("/anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoRepository repositoryAnotacao;

    @RequestMapping({"", "/", "/index" })
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-anotacao");
        return mv;
    }
   

    @RequestMapping(value = { "/excluir-anotacao/{id}" }, method = RequestMethod.GET)
    public ModelAndView carregaExcluirItemEtiqueta(@PathVariable(value = "id", required = true) Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        repositoryAnotacao.deleteById(id);
        return mv;    
    }


    @RequestMapping(value = {"/editar-anotacao/{id}"}, method = RequestMethod.GET)
    public ModelAndView editarAnotacao (@PathVariable(value = "id", required = true) Long id, HttpSession session)
    {
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = repositoryAnotacao.getOne(id);
        mv.addObject("anotacao", anotacao);
        mv.addObject("id2", id);
        mv.setViewName("editar-anotacao");
        return mv;
    }

    @RequestMapping(value = {"/editar-anotacao"}, method = RequestMethod.POST)
    public ModelAndView realizaEditarAnotacao (@RequestParam(value = "id", required = true) Long id, Anotacao anotacao, HttpSession session)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-anotacao");
        return mv;
    }     


}