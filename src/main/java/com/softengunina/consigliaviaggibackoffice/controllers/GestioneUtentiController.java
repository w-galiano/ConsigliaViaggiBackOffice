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
import com.softengunina.consigliaviaggibackoffice.models.Utente;
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

public class GestioneUtentiController {
    
    public static List<Utente> mostraUtenti() {
        
        try {
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Utenti").get();
            //ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").get();

            List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
            //List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
            
            List<Utente> utentiList = new ArrayList<>();
            //List<Struttura> struttureList = new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs){
                Utente utente= document.toObject(Utente.class);
                utentiList.add(utente);
            }
            
            if(!utentiList.isEmpty()){
                return utentiList;
            }   
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void clickConfermaModificaUtente(Utente utente) {
        try{
           Firestore dbconn= Connessione.nuovaConnessione();           
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Utenti").whereEqualTo("email", utente.getEmail()).get();
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Recensioni").whereEqualTo("username", utente.getUsername()).get();
           
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           
           String docUtente= null;
           
           for(QueryDocumentSnapshot document: docs){
                docUtente= document.getId();
            }
           
            DocumentReference docRef= dbconn.collection("Utenti").document(docUtente);
            
            docRef.update("nome", utente.getNome());
            docRef.update("cognome", utente.getCognome());
            docRef.update("email", utente.getEmail());
            docRef.update("username", utente.getUsername());
            docRef.update("domanda_segreta", utente.getDomanda_segreta());
            docRef.update("risposta", utente.getRisposta());
            
            //aggiornamento dell'username all'interno delle recensioni
            List<String> docList = new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs2){
                docList.add(document.getId());
            }
            
            for(int i=0;i<docList.size();i++){
                docRef= dbconn.collection("Recensioni").document(docList.get(i));
                docRef.update("username", utente.getUsername());
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clickEliminaUtente(Utente utente){
        try{
           Firestore dbconn= Connessione.nuovaConnessione();           
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Utenti").whereEqualTo("email", utente.getEmail()).get();    
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Recensioni").whereEqualTo("username", utente.getUsername()).get();
           
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           
           String docUtente= null;
           
           for(QueryDocumentSnapshot document: docs){
                docUtente= document.getId();
            }
           
            DocumentReference docRef = dbconn.collection("Utenti").document(docUtente);
            docRef.delete(); 
            
            //cancellazione di tutte le recensioni scritte dall'utente eliminato
            
            List<String> docList = new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs2){
                docList.add(document.getId());
            }
            
            for(int i=0;i<docList.size();i++){
                docRef= dbconn.collection("Recensioni").document(docList.get(i));
                docRef.delete();
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clickIndietro(Amministratore admin){
        AdminHome newHome= new AdminHome(admin);
        newHome.setVisible(true);
    }
}