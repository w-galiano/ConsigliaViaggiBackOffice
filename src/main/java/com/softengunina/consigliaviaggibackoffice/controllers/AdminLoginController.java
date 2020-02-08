package com.softengunina.consigliaviaggibackoffice.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.softengunina.consigliaviaggibackoffice.AdminHome;
import com.softengunina.consigliaviaggibackoffice.Connessione;
import com.softengunina.consigliaviaggibackoffice.AdminLogin;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro Fontanella
 * @author Walter Galiano
 * @author Stefano Sivo
 */
public class AdminLoginController {
    
    public static boolean clickLogin(int id, String password) {
        
        try {
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Amministratori")
                    .whereEqualTo("ID", id).whereEqualTo("password", password).get();

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
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}