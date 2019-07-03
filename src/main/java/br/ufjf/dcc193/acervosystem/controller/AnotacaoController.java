package br.ufjf.dcc193.acervosystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import br.ufjf.dcc193.acervosystem.model.Anotacao;
import br.ufjf.dcc193.acervosystem.model.Item;
import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.model.Vinculo;
import br.ufjf.dcc193.acervosystem.repository.AnotacaoRepository;
import br.ufjf.dcc193.acervosystem.repository.ItemRepository;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;
import br.ufjf.dcc193.acervosystem.repository.VinculoRepository;



@Controller
@RequestMapping("/anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VinculoRepository vinculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = { "/listar-item" }, method = RequestMethod.GET)
    public ModelAndView anotacoesItem(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Item currentItem = itemRepository.getOne(id);
        List<Anotacao> anotacoes = currentItem.getAnotacoes();

        mv.addObject("anotacoes", anotacoes);
        mv.addObject("idItem", id);
        mv.setViewName("anotacao-item-listar");
        return mv;
    }

    @RequestMapping(value = { "/listar-vinculo" }, method = RequestMethod.GET)
    public ModelAndView anotacoesVinculo(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Vinculo currentItem = vinculoRepository.getOne(id);
        List<Anotacao> anotacoes = currentItem.getAnotacoes();

        mv.addObject("anotacoes", anotacoes);
        mv.addObject("idVinculo", id);
        mv.setViewName("anotacao-vinculo-listar");
        return mv;
    }
    
    
    @RequestMapping(value = { "/detalhe" }, method = RequestMethod.GET)
    public ModelAndView detalhe(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = anotacaoRepository.getOne(id);
        mv.addObject("anotacao", anotacao);
        mv.addObject("id", id);
        mv.addObject("isInsert", false);
        mv.setViewName("anotacao-detalhe");
        return mv;
    }
    
    @RequestMapping(value = { "/novo-item" }, method = RequestMethod.GET)
    public ModelAndView novoItem(@RequestParam("idItem") Long idItem){ 
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = new Anotacao();
        mv.addObject("anotacao", anotacao);
        mv.addObject("isInsert", true);
        mv.addObject("idItem", idItem);
        mv.setViewName("anotacao-detalhe");
        return mv;
    }
    
    @RequestMapping(value = { "/novo-vinculo" }, method = RequestMethod.GET)
    public ModelAndView novoVinculo(@RequestParam("idVinculo") Long idVinculo){ 
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = new Anotacao();
        mv.addObject("anotacao", anotacao);
        mv.addObject("isInsert", true);
        mv.addObject("idVinculo", idVinculo);
        mv.setViewName("anotacao-detalhe");
        return mv;
    }
    
    
    @PostMapping(value = "/salvar")
    public ModelAndView salvarItem(@Valid Anotacao anotacao,@RequestParam("idItem")  String idItem,@RequestParam("idVinculo")  String idVinculo, BindingResult binding,HttpServletRequest request) {
        boolean isInsert = anotacao.getId() == null;
        ModelAndView mv = new ModelAndView();
        Anotacao current;
        Item item;
        Vinculo vinculo;
        if (isInsert){
            current = new Anotacao();
            Usuario user = Helper.getUsuarioLogado(request, usuarioRepository);
            current.setUsuario(user);
            current.setDataDeInclusao(Helper.getDataAtualFormatada());
            if (idItem.length()>0){
                item = itemRepository.getOne(Long.parseLong(idItem));
                current.setItem(item);
            }
            if (idVinculo.length()>0){
                vinculo = vinculoRepository.getOne(Long.parseLong(idVinculo));
                current.setVinculo(vinculo);
            }
        }
        else
            current = anotacaoRepository.getOne(anotacao.getId());
        
        current.setTitulo(anotacao.getTitulo());
        current.setDescricao(anotacao.getDescricao());
        current.setUrl(anotacao.getUrl());
        current.atualizaDataDeAtualizacao();
        anotacaoRepository.save(current);
        Long id;
        if (current.getItem() != null){
            id = current.getItem().getId();
            mv.setViewName("redirect:listar-item");
        }else {
            id = current.getVinculo().getId();
            mv.setViewName("redirect:listar-vinculo");
        }
        mv.addObject("id", id);
        return mv;
    }
    
    @RequestMapping(value = { "/excluir" }, method = RequestMethod.GET)
    public ModelAndView excluir(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Anotacao current = anotacaoRepository.getOne(id);
        anotacaoRepository.deleteById(id);
        Long idLink;
        if (current.getItem() != null){
            idLink = current.getItem().getId();
            mv.setViewName("redirect:listar-item");
        }else {
            idLink = current.getVinculo().getId();
            mv.setViewName("redirect:listar-vinculo");
        }
        mv.addObject("id", idLink);
        return mv;
    }     


}