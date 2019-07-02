package br.ufjf.dcc193.tbr3.helper;

import java.text.SimpleDateFormat;

public class Helper {

    public static final String getDataAtualFormatada(){

        String formato = "dd-MM-YY";
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.format(agora);
    }
}
