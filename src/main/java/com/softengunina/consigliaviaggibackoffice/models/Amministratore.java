package com.softengunina.consigliaviaggibackoffice.models;

/**
 *
 * @author Alessandro Fontanella
 * @author Walter Galiano
 * @author Stefano Sivo
 */
public class Amministratore {
    private int ID;
    private String password;
    
    public int getID(){
        return ID;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setID(int ID){
        this.ID= ID;
    }
    
    public void setPassword(String password){
        this.password= password;
    }
}