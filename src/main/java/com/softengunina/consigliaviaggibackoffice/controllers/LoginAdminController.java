/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.softengunina.consigliaviaggibackoffice.AdminHome;
import com.softengunina.consigliaviaggibackoffice.Connessione;
import com.softengunina.consigliaviaggibackoffice.LoginAdmin;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Walter Galiano
 */
public class LoginAdminController {
    
    public static boolean clickLogin(int id, String password) {
        
        try {
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Amministratori").whereEqualTo("ID", id).whereEqualTo("password", password).get();

            List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
            
            List<Amministratore> amministratoriList= new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs){
                Amministratore amministratore= document.toObject(Amministratore.class);
                amministratoriList.add(amministratore);
            }

            if(!amministratoriList.isEmpty()){
                new AdminHome(amministratoriList.get(0)).setVisible(true);
                
                return true;
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}