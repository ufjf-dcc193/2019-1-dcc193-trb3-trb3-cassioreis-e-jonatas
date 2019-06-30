package br.ufjf.dcc193.tbr3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping({"", "/", "/index" })
    public void index ()
    {
       
    }

}