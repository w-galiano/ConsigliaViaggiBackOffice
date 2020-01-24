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