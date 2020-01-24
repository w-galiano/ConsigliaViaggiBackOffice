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
public class Struttura {
    private String citta;
    private String descrizione;
    private String indirizzo;
    private double lat;
    private double lon;
    private String nome;
    private int prezzo_max;
    private int prezzo_min;
    private String tipo;
    private float valutazione_media;
    
    public String getCitta(){
        return citta;
    }
    
    public String getDescrizione(){
        return descrizione;
    }
    
    public String getIndirizzo(){
        return indirizzo;
    }
    
    public double getLat(){
        return lat;
    }
    
    public double getLon(){
        return lon;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getPrezzo_max(){
        return prezzo_max;
    }
    
    public int getPrezzo_min(){
        return prezzo_min;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public float getValutazione_media(){
        return valutazione_media;
    }
    
    public void setCitta(String citta){
        this.citta= citta;
    }
    
    public void setDescrizione(String descrizione){
        this.descrizione= descrizione;
    }
    
    public void setIndirizzo(String indirizzo){
        this.indirizzo= indirizzo;
    }
    
    public void setLat(double lat){
        this.lat= lat;
    }
    
    public void setLon(double lon){
        this.lon= lon;
    }
    
    public void setNome(String nome){
        this.nome= nome;
    }
    
    public void setPrezzo_max(int prezzo_max){
        this.prezzo_max= prezzo_max;
    }
    
    public void setPrezzo_min(int prezzo_min){
        this.prezzo_min= prezzo_min;
    }
    
    public void setTipo(String tipo){
        this.tipo= tipo;
    }
    
    public void setValutazione_media(float valutazione_media){
        this.valutazione_media= valutazione_media;
    }
}
