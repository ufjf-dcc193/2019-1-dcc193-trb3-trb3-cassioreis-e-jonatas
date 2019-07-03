package br.ufjf.dcc193.acervosystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.acervosystem.model.Etiqueta;
import br.ufjf.dcc193.acervosystem.model.Item;
import br.ufjf.dcc193.acervosystem.repository.EtiquetaRepository;
import br.ufjf.dcc193.acervosystem.repository.ItemRepository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        List<Item> itens = itemRepository.findAll();
        mv.addObject("itens", itens);
        mv.setViewName("item-listar");
        return mv;
    }

    @RequestMapping(value = { "/detalhe" }, method = RequestMethod.GET)
    public ModelAndView detalhe(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Item item = itemRepository.getOne(id);
        mv.addObject("item", item);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.setViewName("item-detalhe");
        return mv;
    }

    @RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", new Item());
        mv.addObject("isInsert", true);
        mv.setViewName("item-detalhe");
        return mv;
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvar(@RequestParam(value = "id", required = true) Long id, @Valid Item item,
            BindingResult binding) {
        boolean isInsert = item.getId() == null;
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

    @RequestMapping(value = { "/etiquetas" }, method = RequestMethod.GET)
    public ModelAndView etiquetas(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Item currentItem = itemRepository.getOne(id);
        List<Etiqueta> etiquetas = currentItem.getEtiquetas();
        List<Etiqueta> allEtiquetas = etiquetaRepository.findAll();

        for (Etiqueta e : allEtiquetas) {
            if (etiquetas.indexOf(e) != -1)
                e.setChecked();
        }

        mv.addObject("etiquetas", allEtiquetas);
        mv.addObject("idItem", id);
        mv.setViewName("etiqueta-item-listar");
        return mv;
    }


    

    @RequestMapping(value = "/associar-etiquetas", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String associarEtiquetas(@RequestParam(value="idItem") Long idItem, @RequestParam(value="listIdsEtiquetas[]") List<Long> listIdsEtiquetas) {

        try{

            Item item = itemRepository.getOne(idItem);
            List<Etiqueta> etiquetas = item.getEtiquetas();
            etiquetas.clear();
            for (Long idEtiqueta: listIdsEtiquetas) {
                etiquetas.add(etiquetaRepository.getOne(idEtiqueta));
            }
            itemRepository.save(item);
            
            return "Success";
        }catch(Exception e){
            
            return e.getMessage();
        }


    }



}