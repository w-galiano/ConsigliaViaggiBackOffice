package com.softengunina.consigliaviaggibackoffice.models;

/**
 *
 * @author Alessandro Fontanella
 * @author Walter Galiano
 * @author Stefano Sivo
 */
public class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String domanda_segreta;
    private String risposta;
    
    public String getNome(){
        return nome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getDomanda_segreta(){
        return domanda_segreta;
    }
    
    public String getRisposta(){
        return risposta;
    }
    
    public void setNome(String nome){
        this.nome= nome;
    }
    
    public void setCognome(String cognome){
        this.cognome= cognome;
    }
    
    public void setEmail(String email){
        this.email= email;
    }
    
    public void setUsername(String username){
        this.username= username;
    }
    
    public void setDomanda_segreta(String domanda_segreta){
        this.domanda_segreta= domanda_segreta;
    }
    
    public void setRisposta(String risposta){
        this.risposta= risposta;
    }
}
