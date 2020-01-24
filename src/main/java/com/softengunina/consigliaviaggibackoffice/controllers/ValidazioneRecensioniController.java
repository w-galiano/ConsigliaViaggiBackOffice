/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.softengunina.consigliaviaggibackoffice.AdminHome;
import com.softengunina.consigliaviaggibackoffice.Connessione;
import com.softengunina.consigliaviaggibackoffice.LoginAdmin;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;
import com.softengunina.consigliaviaggibackoffice.models.Recensione;
import com.softengunina.consigliaviaggibackoffice.models.Struttura;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walter Galiano
 */

public class ValidazioneRecensioniController {
    
    public static List<Recensione> mostraRecensioni() {
        
        try {
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", false).get();
            ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").get();

            List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
            List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
            
            List<Recensione> recensioniList = new ArrayList<>();
            List<Struttura> struttureList = new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs){
                Recensione recensione= document.toObject(Recensione.class);
                recensioniList.add(recensione);
            }
            
            for(QueryDocumentSnapshot document: docs2){
                Struttura struttura= document.toObject(Struttura.class);
                struttureList.add(struttura);
            }
            
            if(!recensioniList.isEmpty()){
                boolean found = false;
                int j=0;
                for(int i=0;i<recensioniList.size();i++){
                    while(j<struttureList.size() & found!=true){
                        if(recensioniList.get(i).getStruttura().equals(struttureList.get(j).getIndirizzo())){
                            recensioniList.get(i).setStruttura(struttureList.get(j).getNome());
                            found = true;
                        }
                        else{ j++; }
                    }
                    found = false;
                    j = 0;
                }
            
                return recensioniList;
            }   
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void clickApprovaRecensione(Recensione recensione) {
        try{
           Firestore dbconn= Connessione.nuovaConnessione();           
           
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").whereEqualTo("nome", recensione.getStruttura()).get();           
           
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           
           String indirizzo= null;
           String docStruttura= null;
           
           for(QueryDocumentSnapshot document: docs2){
                docStruttura= document.getId();
                indirizzo= document.get("indirizzo").toString();
            }
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", false).whereEqualTo("struttura", indirizzo).whereEqualTo("username", recensione.getUsername()).get();
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           
           String daApprovare= null;
           
           for(QueryDocumentSnapshot document: docs){
               daApprovare= document.getId();
           }
           
            DocumentReference docRef = dbconn.collection("Recensioni").document(daApprovare);
            docRef.update("pubblicata", true);
            
            //aggiornamento valutazione media
            ApiFuture<QuerySnapshot> qlist3= dbconn.collection("Recensioni").whereEqualTo("struttura", indirizzo).whereEqualTo("pubblicata", true).get();
            List<QueryDocumentSnapshot> docs3= qlist3.get().getDocuments();
            
            List<Long> valutazioni= new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs3){
                valutazioni.add((Long) document.get("voto"));
            }
            
            double valutazione_tot=0;
            
            for(int i=0;i<valutazioni.size();i++){
                valutazione_tot+= valutazioni.get(i);
            }
            
            valutazione_tot= valutazione_tot/valutazioni.size();
            valutazione_tot= valutazione_tot;
            
            docRef = dbconn.collection("Strutture").document(docStruttura);
            docRef.update("valutazione_media", valutazione_tot);  
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clickEliminaRecensione(Recensione recensione){
        try{
           Firestore dbconn= Connessione.nuovaConnessione();           
           
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").whereEqualTo("nome", recensione.getStruttura()).get();           
           
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           
           String indirizzo= null;
           
           for(QueryDocumentSnapshot document: docs2){
                indirizzo= document.get("indirizzo").toString();
            }
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", false).whereEqualTo("struttura", indirizzo).whereEqualTo("username", recensione.getUsername()).get();
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           
           String daEliminare= null;
           
           for(QueryDocumentSnapshot document: docs){
               daEliminare= document.getId();
           }
           
            DocumentReference docRef = dbconn.collection("Recensioni").document(daEliminare);
            docRef.delete(); 
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clickIndietro(Amministratore admin){
        AdminHome newHome= new AdminHome(admin);
        newHome.setVisible(true);
    }
}