/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softengunina.consigliaviaggibackoffice;

import com.google.firebase.auth.FirebaseAuthException;
import com.softengunina.consigliaviaggibackoffice.controllers.GestioneUtentiController;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;
import com.softengunina.consigliaviaggibackoffice.models.Utente;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Walter Galiano
 */
public class GestioneUtenti extends javax.swing.JFrame {
    private Amministratore admin;
    private List<Utente> utenti= new ArrayList<>();
    
    /**
     * Creates new form ValidaRecensioni
     * @param currAdmin
     */
    public GestioneUtenti(Amministratore currAdmin) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        admin= currAdmin;
        
        OperatoreField.setText(String.valueOf(currAdmin.getID()));
        
        List<Utente> utenti= GestioneUtentiController.mostraUtenti();
        
        this.utenti= utenti;
        
        DefaultTableModel aModel = new DefaultTableModel() {
        //setting the jtable read only

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }};

        //setting the column name
        Object[] tableColumnNames = new Object[6];
        tableColumnNames[0]= "Nome";
        tableColumnNames[1]= "Cognome";
        tableColumnNames[2]= "Username";
        tableColumnNames[3]= "Email";
        tableColumnNames[4]= "Domanda segreta";
        tableColumnNames[5]= "Risposta";

        aModel.setColumnIdentifiers(tableColumnNames);
        if (utenti == null) {
            this.UtentiTable.setModel(aModel);
            return;
        }

        Object[] objects = new Object[7];
        for(int i=0; i<utenti.size(); i++){
            objects[0]= utenti.get(i).getNome();
            objects[1]= utenti.get(i).getCognome();
            objects[2]= utenti.get(i).getUsername();
            objects[3]= utenti.get(i).getEmail();
            objects[4]= utenti.get(i).getDomanda_segreta();
            objects[5]= utenti.get(i).getRisposta();
            
            aModel.addRow(objects);
        }
        
        this.UtentiTable.setModel(aModel);
    }
    
    public static boolean isValidEmail(String email){ 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 

        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
    
    public static boolean isInvalidText(String text){ 
        String textRegex = "^.*\\s$";
        Pattern pat = Pattern.compile(textRegex); 
        if (text == null) 
            return false; 
        return pat.matcher(text).matches(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ValidazioneRecensioniPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UtentiTable = new javax.swing.JTable();
        ProjectLabel = new javax.swing.JLabel();
        CompanyLabel = new javax.swing.JLabel();
        GestioneUtentiLabel = new javax.swing.JLabel();
        OperatoreLabel = new javax.swing.JLabel();
        OperatoreField = new javax.swing.JTextField();
        ConfermaButton = new javax.swing.JToggleButton();
        EliminaButton = new javax.swing.JToggleButton();
        IndietroLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ValidazioneRecensioniPanel.setBackground(new java.awt.Color(255, 255, 255));

        UtentiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        UtentiTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UtentiTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(UtentiTable);

        ProjectLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ProjectLabel.setForeground(new java.awt.Color(51, 51, 255));
        ProjectLabel.setText("Consiglia Viaggi 19");

        CompanyLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        CompanyLabel.setForeground(new java.awt.Color(51, 51, 255));
        CompanyLabel.setText("SoftEngUniNA");

        GestioneUtentiLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        GestioneUtentiLabel.setForeground(new java.awt.Color(51, 51, 255));
        GestioneUtentiLabel.setText("GESTIONE UTENTI");

        OperatoreLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        OperatoreLabel.setForeground(new java.awt.Color(51, 51, 255));
        OperatoreLabel.setText("Operatore:");

        OperatoreField.setEditable(false);
        OperatoreField.setBackground(new java.awt.Color(255, 255, 255));
        OperatoreField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        OperatoreField.setForeground(new java.awt.Color(51, 51, 255));
        OperatoreField.setBorder(null);

        ConfermaButton.setBackground(new java.awt.Color(51, 204, 0));
        ConfermaButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ConfermaButton.setForeground(new java.awt.Color(255, 255, 255));
        ConfermaButton.setText("CONFERMA");
        ConfermaButton.setMaximumSize(new java.awt.Dimension(107, 33));
        ConfermaButton.setPreferredSize(new java.awt.Dimension(107, 33));
        ConfermaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfermaButtonActionPerformed(evt);
            }
        });

        EliminaButton.setBackground(new java.awt.Color(255, 0, 0));
        EliminaButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        EliminaButton.setForeground(new java.awt.Color(255, 255, 255));
        EliminaButton.setText("ELIMINA");
        EliminaButton.setPreferredSize(new java.awt.Dimension(107, 33));
        EliminaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminaButtonActionPerformed(evt);
            }
        });

        IndietroLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        IndietroLabel.setForeground(new java.awt.Color(51, 51, 255));
        IndietroLabel.setText("< indietro");
        IndietroLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IndietroLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ValidazioneRecensioniPanelLayout = new javax.swing.GroupLayout(ValidazioneRecensioniPanel);
        ValidazioneRecensioniPanel.setLayout(ValidazioneRecensioniPanelLayout);
        ValidazioneRecensioniPanelLayout.setHorizontalGroup(
            ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                            .addComponent(ProjectLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(OperatoreLabel)
                            .addGap(2, 2, 2)
                            .addComponent(OperatoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(CompanyLabel)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                        .addComponent(IndietroLabel)
                        .addGap(210, 210, 210)
                        .addComponent(GestioneUtentiLabel)))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ValidazioneRecensioniPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ConfermaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EliminaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287))
        );
        ValidazioneRecensioniPanelLayout.setVerticalGroup(
            ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ValidazioneRecensioniPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProjectLabel)
                    .addComponent(OperatoreLabel)
                    .addComponent(OperatoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GestioneUtentiLabel)
                    .addComponent(IndietroLabel))
                .addGap(94, 94, 94)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfermaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EliminaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(CompanyLabel)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ValidazioneRecensioniPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ValidazioneRecensioniPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UtentiTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UtentiTableMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_UtentiTableMouseClicked

    private void ConfermaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfermaButtonActionPerformed
        // TODO add your handling code here:
        
        if(UtentiTable.getSelectedRow()!=-1){
        
            Utente utente= new Utente();

            utente.setNome(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 0).toString());
            utente.setCognome(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 1).toString());
            utente.setEmail(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 3).toString());
            utente.setUsername(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 2).toString());
            utente.setDomanda_segreta(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 4).toString());
            utente.setRisposta(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 5).toString());
            
            if(utente.getNome().isEmpty() || utente.getCognome().isEmpty() || utente.getEmail().isEmpty() || utente.getUsername().isEmpty() || utente.getDomanda_segreta().isEmpty()
                || utente.getRisposta().isEmpty()){
                
                UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
                JOptionPane.showMessageDialog(null,"Non lasciare campi vuoti 1 !","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if(isInvalidText(utente.getNome()) || isInvalidText(utente.getCognome()) || isInvalidText(utente.getEmail()) || isInvalidText(utente.getUsername()) || isInvalidText(utente.getDomanda_segreta())
                || isInvalidText(utente.getRisposta())){
                
                UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
                JOptionPane.showMessageDialog(null,"Non lasciare campi vuoti!","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if(!isValidEmail(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 3).toString())){
                UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
                JOptionPane.showMessageDialog(null,"Formato email non valido!","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            
            
            try {
                if(GestioneUtentiController.clickConfermaModificaUtente(utente, this.utenti.get(UtentiTable.getSelectedRow()))==-1){
                    UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
                    UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
                    JOptionPane.showMessageDialog(null,"Nessun campo modificato!","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FirebaseAuthException ex) {
                Logger.getLogger(GestioneUtenti.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.dispose();
            GestioneUtenti newFrame = new GestioneUtenti(admin);
            newFrame.setVisible(true);
        }else{
            UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
            JOptionPane.showMessageDialog(null,"Nessuna riga selezionata","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_ConfermaButtonActionPerformed

    private void EliminaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminaButtonActionPerformed
        // TODO add your handling code here:
        
        if(UtentiTable.getSelectedRow()!=-1){
        
            Utente utente= new Utente();

            utente.setNome(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 0).toString());
            utente.setCognome(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 1).toString());
            utente.setEmail(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 3).toString());
            utente.setUsername(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 2).toString());
            utente.setDomanda_segreta(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 4).toString());
            utente.setRisposta(UtentiTable.getValueAt(UtentiTable.getSelectedRow(), 5).toString());
            
            GestioneUtentiController.clickEliminaUtente(utente);
        }else{
            UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
            JOptionPane.showMessageDialog(null,"Nessuna riga selezionata","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
        GestioneUtenti newFrame = new GestioneUtenti(admin);
        newFrame.setVisible(true);
    }//GEN-LAST:event_EliminaButtonActionPerformed

    private void IndietroLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IndietroLabelMouseClicked
        // TODO add your handling code here:
        GestioneUtentiController.clickIndietro(admin);
        this.dispose();        
    }//GEN-LAST:event_IndietroLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompanyLabel;
    private javax.swing.JToggleButton ConfermaButton;
    private javax.swing.JToggleButton EliminaButton;
    private javax.swing.JLabel GestioneUtentiLabel;
    private javax.swing.JLabel IndietroLabel;
    private javax.swing.JTextField OperatoreField;
    private javax.swing.JLabel OperatoreLabel;
    private javax.swing.JLabel ProjectLabel;
    private javax.swing.JTable UtentiTable;
    private javax.swing.JPanel ValidazioneRecensioniPanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
