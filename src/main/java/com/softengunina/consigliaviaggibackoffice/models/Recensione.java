/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice.models;

/**
 *
 * @author Walter Galiano
 */
public class Recensione {
    private String autore;
    private String commento;
    private boolean pubblicata;
    private String struttura;
    private String titolo;
    private String username;
    private int voto;
    
    public String getAutore(){
        return autore;
    }
    
    public String getCommento(){
        return commento;
    }
    
    public boolean getPubblicata(){
        return pubblicata;
    }
    
    public String getStruttura(){
        return struttura;
    }
    
    public String getTitolo(){
        return titolo;
    }
    
    public String getUsername(){
        return username;
    }
    
    public int getVoto(){
        return voto;
    }
    
    public void setAutore(String autore){
        this.autore= autore;
    }
    
    public void setCommento(String commento){
        this.commento= commento;
    }
    
    public void setPubblicata(boolean pubblicata){
        this.pubblicata= pubblicata;
    }
    
    public void setStruttura(String struttura){
        this.struttura= struttura;
    }
    
    public void setTitolo(String titolo){
        this.titolo= titolo;
    }
    
    public void setUsername(String username){
        this.username= username;
    }
    
    public void setVoto(int voto){
        this.voto= voto;
    }
}
