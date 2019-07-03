package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.acervosystem.model.Etiqueta;
import br.ufjf.dcc193.acervosystem.model.Item;
import br.ufjf.dcc193.acervosystem.repository.EtiquetaRepository;



@Controller
@RequestMapping("/etiqueta")
public class EtiquetaController {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    public ModelAndView listar ()
    {
        ModelAndView mv = new ModelAndView();
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        mv.addObject("etiquetas", etiquetas);
        mv.setViewName("etiqueta-listar");
        return mv;
    }

    @RequestMapping(value = {"/detalhe"}, method = RequestMethod.GET)
    public ModelAndView detalhe (@RequestParam("id") Long id)
    {
        ModelAndView mv = new ModelAndView();
        Etiqueta etiqueta = etiquetaRepository.getOne(id);
        mv.addObject("etiqueta", etiqueta);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.setViewName("etiqueta-detalhe");
        return mv;
    }

    @RequestMapping(value = {"/novo"}, method = RequestMethod.GET)
    public ModelAndView novo ()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiqueta", new Etiqueta());
        mv.addObject("isInsert", true);
        mv.setViewName("etiqueta-detalhe");
        return mv;
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvar (@RequestParam(value = "id", required = true) Long id, @Valid Etiqueta etiqueta,BindingResult binding)
    {
        boolean isInsert = etiqueta.getId()==null;
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("etiqueta-detalhe");
            mv.addObject("etiqueta", etiqueta);
            return mv;
        }
        Etiqueta current;
        if (isInsert)
            current = new Etiqueta();
        else
            current = etiquetaRepository.getOne(id);
        current.setTitulo(etiqueta.getTitulo());
        current.setDescricao(etiqueta.getDescricao());
        current.setUrl(etiqueta.getUrl());
        etiquetaRepository.save(current);
        mv.setViewName("redirect:listar");
        return mv;
    }
    

    @RequestMapping(value = { "/excluir" }, method = RequestMethod.GET)
    public ModelAndView excluir(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        etiquetaRepository.deleteById(id);
        mv.setViewName("redirect:listar");
        return mv;    
    }
    

    @RequestMapping(value = {"/itens"}, method = RequestMethod.GET)
    public ModelAndView listarItensPorEtiqueta (@RequestParam("id") Long id)
    {
        ModelAndView mv = new ModelAndView();
        Etiqueta etiqueta = etiquetaRepository.getOne(id);
        List<Item> itens = etiqueta.getItens();
        mv.addObject("itens", itens);
        mv.addObject("etiquetaTitulo", etiqueta.getTitulo());
        mv.setViewName("item-etiqueta-listar");
        return mv;
    }

}