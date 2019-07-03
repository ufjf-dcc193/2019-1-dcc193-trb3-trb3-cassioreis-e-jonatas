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
import br.ufjf.dcc193.acervosystem.model.Vinculo;
import br.ufjf.dcc193.acervosystem.repository.EtiquetaRepository;
import br.ufjf.dcc193.acervosystem.repository.ItemRepository;
import br.ufjf.dcc193.acervosystem.repository.VinculoRepository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/vinculo")
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EtiquetaRepository etiquetaRepository;


    
    @RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ModelAndView vinculos(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Item currentItem = itemRepository.getOne(id);
        List<Vinculo> vinculosOrigem = currentItem.getVinculosOrigem();

        mv.addObject("vinculos", vinculosOrigem);
        mv.addObject("itemId", id);
        mv.setViewName("vinculo-item-listar");
        return mv;
    }
    
    
    @RequestMapping(value = { "/detalhe" }, method = RequestMethod.GET)
    public ModelAndView detalhe(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = vinculoRepository.getOne(id);
        mv.addObject("vinculo", vinculo);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.addObject("idItemOrigem", vinculo.getItemOrigem().getId());
        List<Item> itensDestino = itemRepository.findByIdNot(vinculo.getItemOrigem().getId());
        mv.addObject("itensDestino", itensDestino);
        mv.setViewName("vinculo-detalhe");
        return mv;
    }
    
    @RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
    public ModelAndView novo(@RequestParam("itemId") Long itemId){ 
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = new Vinculo();
        vinculo.setItemOrigem(itemRepository.getOne(itemId));
        mv.addObject("vinculo", vinculo);
        mv.addObject("isInsert", true);
        List<Item> itensDestino = itemRepository.findByIdNot(itemId);
        mv.addObject("itensDestino", itensDestino);
        mv.addObject("idItemOrigem", itemId);
        mv.setViewName("vinculo-detalhe");
        return mv;
    }
    
    @PostMapping(value = "/salvar")
    public ModelAndView salvar(@Valid Vinculo vinculo,@RequestParam("idItemOrigem")  String idItemOrigem, BindingResult binding) {
        boolean isInsert = vinculo.getId() == null;
        ModelAndView mv = new ModelAndView();
        Vinculo current;
        if (isInsert)
            current = new Vinculo();
        else
        current = vinculoRepository.getOne(vinculo.getId());
        
        current.setItemOrigem(itemRepository.getOne(Long.parseLong(idItemOrigem)));
        current.setItemDestino(vinculo.getItemDestino());
        vinculoRepository.save(current);
        mv.addObject("id", current.getItemOrigem().getId());
        mv.setViewName("redirect:listar");
        return mv;
    }
    
    @RequestMapping(value = { "/excluir" }, method = RequestMethod.GET)
    public ModelAndView excluir(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Long itemId = vinculoRepository.getOne(id).getItemOrigem().getId();
        vinculoRepository.deleteById(id);
        mv.addObject("id", itemId);
        mv.setViewName("redirect:listar");
        return mv;
    }

    

    @RequestMapping(value = { "/etiquetas" }, method = RequestMethod.GET)
    public ModelAndView etiquetas(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Vinculo currentVinculo = vinculoRepository.getOne(id);
        List<Etiqueta> etiquetas = currentVinculo.getEtiquetas();
        List<Etiqueta> allEtiquetas = etiquetaRepository.findAll();

        for (Etiqueta e : allEtiquetas) {
            if (etiquetas.indexOf(e) != -1)
                e.setChecked();
        }

        mv.addObject("etiquetas", allEtiquetas);
        mv.addObject("idVinculo", id);
        mv.setViewName("etiqueta-vinculo-listar");
        return mv;
    }

    @RequestMapping(value = "/associar-etiquetas", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String associarEtiquetas(@RequestParam(value="idVinculo") Long idVinculo, @RequestParam(value="listIdsEtiquetas[]") List<Long> listIdsEtiquetas) {

        try{

            Vinculo vinculo = vinculoRepository.getOne(idVinculo);
            List<Etiqueta> etiquetas = vinculo.getEtiquetas();
            etiquetas.clear();
            for (Long idEtiqueta: listIdsEtiquetas) {
                etiquetas.add(etiquetaRepository.getOne(idEtiqueta));
            }
            vinculoRepository.save(vinculo);
            
            return "Success";
        }catch(Exception e){
            
            return e.getMessage();
        }
    }

}