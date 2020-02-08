package com.softengunina.consigliaviaggibackoffice.controllers;

import com.softengunina.consigliaviaggibackoffice.GestioneRecensioni;
import com.softengunina.consigliaviaggibackoffice.GestioneUtenti;
import com.softengunina.consigliaviaggibackoffice.AdminLogin;
import com.softengunina.consigliaviaggibackoffice.ValidazioneRecensioni;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;

/**
 *
 * @author Alessandro Fontanella
 * @author Walter Galiano
 * @author Stefano Sivo
 */
public class AdminHomeController {
    
    public static void clickLogout(){
        new AdminLogin().setVisible(true);
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