/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;

import java.io.IOException;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Walter Galiano
 */
public class Connessione {
    /**
     * @param args the command line arguments
     */
    public static Firestore nuovaConnessione() throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
        // TODO code application logic here

        FileInputStream serviceAccount
                = new FileInputStream("C:\\Users\\walte\\Documents\\NetBeansProjects\\consigliaviaggi1920-38-firebase-adminsdk-ag5wd-2e5eb75f7a.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://consigliaviaggi1920-38.firebaseio.com")
            .build();
        
        FirebaseApp app;
        
        if(FirebaseApp.getApps().isEmpty()){
            app= FirebaseApp.initializeApp(options);
        }else{
            app= FirebaseApp.getInstance();
        }              
        
        Firestore db= FirestoreClient.getFirestore(app);
        
        /*QuerySnapshot qs= db.collection("Strutture").get().get();

        ApiFuture<QuerySnapshot> qlist = db.collection("Strutture").get();

        List<QueryDocumentSnapshot> documents = qlist.get().getDocuments();
        documents.forEach((document) -> {
            System.out.println(document.getId() + " => " + document.getData());
        });*/
        return db;
    }
}