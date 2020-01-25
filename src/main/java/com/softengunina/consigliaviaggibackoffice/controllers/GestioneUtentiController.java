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
import com.google.cloud.firestore.WriteBatch;
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
            WriteBatch batch = dbconn.batch();           

            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Utenti").whereEqualTo("email", utente.getEmail()).get();
            ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Recensioni").whereEqualTo("username", utente.getUsername()).get();

            List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
            List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();

            String docUtente= null;

            for(QueryDocumentSnapshot document: docs){
                 docUtente= document.getId();
            }

            DocumentReference docRef= dbconn.collection("Utenti").document(docUtente);
            
            batch.update(docRef, "nome", utente.getNome());
            batch.update(docRef, "cognome", utente.getCognome());
            batch.update(docRef, "email", utente.getEmail());
            batch.update(docRef, "username", utente.getUsername());
            batch.update(docRef, "domanda_segreta", utente.getDomanda_segreta());
            batch.update(docRef, "risposta", utente.getRisposta());
            
            batch.commit();
            
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
           WriteBatch batch = dbconn.batch();
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Utenti").whereEqualTo("email", utente.getEmail()).get();    
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Recensioni").whereEqualTo("username", utente.getUsername()).get();
           ApiFuture<QuerySnapshot> qlist3= dbconn.collection("Strutture").get();
           
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           List<QueryDocumentSnapshot> docs3= qlist3.get().getDocuments();
           
           String docUtente= null;
           
           for(QueryDocumentSnapshot document: docs){
                docUtente= document.getId();
            }
           
            DocumentReference docRef = dbconn.collection("Utenti").document(docUtente);
            batch.delete(docRef);
            batch.commit();
            
            //cancellazione di tutte le recensioni scritte dall'utente eliminato
            WriteBatch batch2 = dbconn.batch();
            
            List<String> docList = new ArrayList<>();
            List<String> idStrutture= new ArrayList<>();
            List<String> struttureDaAggiornare= new ArrayList<>();
            List<String> indirizzi= new ArrayList<>();
            List<String> indirizzi2= new ArrayList<>();
            List<Struttura> struttureDaConfrontare= new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs2){
                docList.add(document.getId());
                String struttura= document.get("struttura").toString();
                indirizzi.add(struttura);
            }
            
            for(int i=0;i<docList.size();i++){
                docRef= dbconn.collection("Recensioni").document(docList.get(i));
                batch2.delete(docRef);
            }
            
            batch2.commit();
            
            DocumentReference docRef2;
            
            for(QueryDocumentSnapshot document: docs3){
                idStrutture.add(document.getId());
                Struttura daConfrontare= document.toObject(Struttura.class);
                struttureDaConfrontare.add(daConfrontare);
            }
            
            for(int i=0;i<indirizzi.size();i++){
                for(int j=0;j<struttureDaConfrontare.size();j++){
                    if(indirizzi.get(i).equals(struttureDaConfrontare.get(j).getIndirizzo())){
                        struttureDaAggiornare.add(idStrutture.get(j));
                        indirizzi2.add(indirizzi.get(i));
                    }
                }
            }
            
            //aggiornamento valutazione media
            WriteBatch batch3 = dbconn.batch();
            
            for(int i=0;i<struttureDaAggiornare.size();i++){
                ApiFuture<QuerySnapshot> qlist4= dbconn.collection("Recensioni").whereEqualTo("struttura", indirizzi2.get(i)).whereEqualTo("pubblicata", true).get();
                List<QueryDocumentSnapshot> docs4= qlist4.get().getDocuments();

                List<Long> valutazioni= new ArrayList<>();

                double valutazione_tot=0;

                for(QueryDocumentSnapshot document: docs4){
                    valutazioni.add((Long) document.get("voto"));
                }

                if(valutazioni.isEmpty()){
                    docRef2 = dbconn.collection("Strutture").document(struttureDaAggiornare.get(i));
                    docRef2.update("valutazione_media", 0);
                    return;
                }

                for(int j=0;j<valutazioni.size();j++){
                    valutazione_tot+= valutazioni.get(j);
                    System.out.println(valutazione_tot);
                }

                System.out.println(valutazioni.size());

                valutazione_tot= valutazione_tot/valutazioni.size();
                valutazione_tot= valutazione_tot;

                docRef2 = dbconn.collection("Strutture").document(struttureDaAggiornare.get(i));
                batch3.update(docRef2,"valutazione_media", valutazione_tot);
                batch3.commit();
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