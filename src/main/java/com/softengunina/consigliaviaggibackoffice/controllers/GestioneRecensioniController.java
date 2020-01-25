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

public class GestioneRecensioniController {
    
    public static List<Recensione> mostraRecensioni() {
        
        try {
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", true).get();
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
    
    public static List<Recensione> clickCercaRecensione(Recensione recensione) {
        try{
            Firestore dbconn= Connessione.nuovaConnessione();
            
            ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", true).whereEqualTo("username", recensione.getUsername()).get();
            ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").whereEqualTo("nome", recensione.getStruttura()).get();
            ApiFuture<QuerySnapshot> qlist3= dbconn.collection("Strutture").get();
            ApiFuture<QuerySnapshot> qlist4= dbconn.collection("Recensioni").get();

            List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
            List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
            List<QueryDocumentSnapshot> docs3= qlist3.get().getDocuments();
            List<QueryDocumentSnapshot> docs4= qlist4.get().getDocuments();
            
            List<Recensione> recensioniList = new ArrayList<>();
            List<Struttura> struttureList = new ArrayList<>();
            List<Struttura> struttureDaConfrontare= new ArrayList<>();
            List<Recensione> recensioniDaConfrontare= new ArrayList<>();
            List<Recensione> recensioniFiltrate= new ArrayList<>();
            
            for(QueryDocumentSnapshot document: docs){
                Recensione recensioneFiltrata= document.toObject(Recensione.class);
                recensioniList.add(recensioneFiltrata);
            }

            for(QueryDocumentSnapshot document: docs2){
                Struttura struttura= document.toObject(Struttura.class);
                struttureList.add(struttura);
            }
            
            for(QueryDocumentSnapshot document: docs3){
                Struttura strutturaDaConfrontare= document.toObject(Struttura.class);
                struttureDaConfrontare.add(strutturaDaConfrontare);
            }
            
            for(QueryDocumentSnapshot document: docs4){
                Recensione recensioneDaConfrontare= document.toObject(Recensione.class);
                recensioniDaConfrontare.add(recensioneDaConfrontare);
            }
            
            
            if(!recensione.getUsername().isEmpty() && !recensione.getStruttura().isEmpty()){
                for(int i=0;i<recensioniList.size();i++){
                    for(int j=0;j<struttureList.size();j++){
                        if(recensioniList.get(i).getStruttura().equals(struttureList.get(j).getIndirizzo())){
                            Recensione newRecensione= new Recensione();
                            newRecensione.setAutore(recensioniList.get(i).getAutore());
                            newRecensione.setCommento(recensioniList.get(i).getCommento());
                            newRecensione.setPubblicata(recensioniList.get(i).getPubblicata());
                            newRecensione.setStruttura(struttureList.get(j).getNome());
                            newRecensione.setTitolo(recensioniList.get(i).getTitolo());
                            newRecensione.setUsername(recensioniList.get(i).getUsername());
                            newRecensione.setVoto(recensioniList.get(i).getVoto());

                            recensioniFiltrate.add(newRecensione);
                        }
                    }
                }
                return recensioniFiltrate;
            }else if(!recensione.getUsername().isEmpty()){
                for(int i=0;i<recensioniList.size();i++){
                    for(int j=0;j<struttureDaConfrontare.size();j++){
                        if(recensioniList.get(i).getStruttura().equals(struttureDaConfrontare.get(j).getIndirizzo())){
                            recensioniList.get(i).setStruttura(struttureDaConfrontare.get(j).getNome());
                        }
                    }
                }
                return recensioniList;
            }else{
                for(int i=0;i<struttureList.size();i++){
                    for(int j=0;j<recensioniDaConfrontare.size();j++){
                        if(recensioniDaConfrontare.get(j).getStruttura().equals(struttureList.get(i).getIndirizzo())){
                            Recensione newRecensione= new Recensione();
                            newRecensione.setAutore(recensioniDaConfrontare.get(j).getAutore());
                            newRecensione.setCommento(recensioniDaConfrontare.get(j).getCommento());
                            newRecensione.setPubblicata(recensioniDaConfrontare.get(j).getPubblicata());
                            newRecensione.setStruttura(struttureList.get(i).getNome());
                            newRecensione.setTitolo(recensioniDaConfrontare.get(j).getTitolo());
                            newRecensione.setUsername(recensioniDaConfrontare.get(j).getUsername());
                            newRecensione.setVoto(recensioniDaConfrontare.get(j).getVoto());

                            recensioniFiltrate.add(newRecensione);
                        }
                    }
                }
                return recensioniFiltrate;
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void clickEliminaRecensione(Recensione recensione){
        try{
           Firestore dbconn= Connessione.nuovaConnessione();
           WriteBatch batch = dbconn.batch();
           
           ApiFuture<QuerySnapshot> qlist2= dbconn.collection("Strutture").whereEqualTo("nome", recensione.getStruttura()).get();           
           
           List<QueryDocumentSnapshot> docs2= qlist2.get().getDocuments();
           
           String indirizzo= null;
           String docStruttura= null;
           
           for(QueryDocumentSnapshot document: docs2){
                docStruttura= document.getId();
                indirizzo= document.get("indirizzo").toString();
            }
           
           ApiFuture<QuerySnapshot> qlist= dbconn.collection("Recensioni").whereEqualTo("pubblicata", true).whereEqualTo("struttura", indirizzo).whereEqualTo("username", recensione.getUsername()).get();
           List<QueryDocumentSnapshot> docs= qlist.get().getDocuments();
           
           String daEliminare= null;
           
           for(QueryDocumentSnapshot document: docs){
               daEliminare= document.getId();
           }
           
            DocumentReference docRef = dbconn.collection("Recensioni").document(daEliminare);
            batch.delete(docRef);
            batch.commit();
            
            //aggiornamento valutazione media
            WriteBatch batch2 = dbconn.batch();
            ApiFuture<QuerySnapshot> qlist3= dbconn.collection("Recensioni").whereEqualTo("struttura", indirizzo).whereEqualTo("pubblicata", true).get();
            List<QueryDocumentSnapshot> docs3= qlist3.get().getDocuments();
            
            List<Long> valutazioni= new ArrayList<>();
            
            double valutazione_tot=0;
            
            for(QueryDocumentSnapshot document: docs3){
                valutazioni.add((Long) document.get("voto"));
            }
            
            if(valutazioni.isEmpty()){
                docRef = dbconn.collection("Strutture").document(docStruttura);
                docRef.update("valutazione_media", 0);
                return;
            }
            
            for(int i=0;i<valutazioni.size();i++){
                valutazione_tot+= valutazioni.get(i);
            }
            
            valutazione_tot= valutazione_tot/valutazioni.size();
            valutazione_tot= valutazione_tot;
            
            docRef = dbconn.collection("Strutture").document(docStruttura);
            batch2.update(docRef, "valutazione_media", valutazione_tot);
            batch2.commit();
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clickIndietro(Amministratore admin){
        AdminHome newHome= new AdminHome(admin);
        newHome.setVisible(true);
    }
}