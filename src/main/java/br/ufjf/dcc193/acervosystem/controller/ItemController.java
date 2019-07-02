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

import br.ufjf.dcc193.acervosystem.model.Item;
import br.ufjf.dcc193.acervosystem.repository.ItemRepository;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    public ModelAndView listar ()
    {
        ModelAndView mv = new ModelAndView();
        List<Item> itens = itemRepository.findAll();
        mv.addObject("itens", itens);
        mv.setViewName("item-listar");
        return mv;
    }

    @RequestMapping(value = {"/detalhe"}, method = RequestMethod.GET)
    public ModelAndView detalhe (@RequestParam("id") Long id)
    {
        ModelAndView mv = new ModelAndView();
        Item item = itemRepository.getOne(id);
        mv.addObject("item", item);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.setViewName("item-detalhe");
        return mv;
    }

    @RequestMapping(value = {"/novo"}, method = RequestMethod.GET)
    public ModelAndView novo ()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", new Item());
        mv.addObject("isInsert", true);
        mv.setViewName("item-detalhe");
        return mv;
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvar (@RequestParam(value = "id", required = true) Long id, @Valid Item item,BindingResult binding)
    {
        boolean isInsert = item.getId()==null;
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("item-detalhe");
            mv.addObject("item", item);
            return mv;
        }
        Item current;
        if (isInsert)
            current = new Item();
        else
            current = itemRepository.getOne(id);
        current.setTitulo(item.getTitulo());
        itemRepository.save(current);
        mv.setViewName("redirect:listar");
        return mv;
    }
    

    @RequestMapping(value = { "/excluir" }, method = RequestMethod.GET)
    public ModelAndView excluir(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        itemRepository.deleteById(id);
        mv.setViewName("redirect:listar");
        return mv;    
    }   
}