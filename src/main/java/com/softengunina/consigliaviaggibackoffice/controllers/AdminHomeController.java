/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice.controllers;

import com.softengunina.consigliaviaggibackoffice.GestioneRecensioni;
import com.softengunina.consigliaviaggibackoffice.GestioneUtenti;
import com.softengunina.consigliaviaggibackoffice.LoginAdmin;
import com.softengunina.consigliaviaggibackoffice.ValidazioneRecensioni;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;

/**
 *
 * @author Walter Galiano
 */
public class AdminHomeController {
    
    public static void clickLogout(){
        new LoginAdmin().setVisible(true);
    }
    
    public static void clickGestisciRecensioni(Amministratore admin){
        new GestioneRecensioni(admin).setVisible(true);
    }
    
    public static void clickGestisciUtenti(Amministratore admin){
        new GestioneUtenti(admin).setVisible(true);
    }
    
    public static void clickValidaRecensioni(Amministratore admin){
        new ValidazioneRecensioni(admin).setVisible(true);
    }
}
