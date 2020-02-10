package com.softengunina.consigliaviaggibackoffice;

import com.softengunina.consigliaviaggibackoffice.controllers.GestioneRecensioniController;
import com.softengunina.consigliaviaggibackoffice.models.Amministratore;
import com.softengunina.consigliaviaggibackoffice.models.Recensione;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fontanella
 * @author Walter Galiano
 * @author Stefano Sivo
 */
public class GestioneRecensioni extends javax.swing.JFrame {
    
    private Amministratore admin;
    
    /**
     * Creates new form ValidaRecensioni
     * @param currAdmin
     */
    public GestioneRecensioni(Amministratore currAdmin) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        admin= currAdmin;
        
        OperatoreField.setText(String.valueOf(currAdmin.getID()));
        
        RiempiTabella();
    }
    
    public void RiempiTabella(){
        List<Recensione> recensioni= GestioneRecensioniController.mostraRecensioni();
        
        DefaultTableModel aModel = new DefaultTableModel() {
        //setting the jtable read only

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }};

        //setting the column name
        Object[] tableColumnNames = new Object[6];
        tableColumnNames[0]= "Autore";
        tableColumnNames[1]= "Commento";
        tableColumnNames[2]= "Struttura";
        tableColumnNames[3]= "Titolo";
        tableColumnNames[4]= "Username";
        tableColumnNames[5]= "Voto";

        aModel.setColumnIdentifiers(tableColumnNames);
        if (recensioni == null) {
            this.GestioneRecensioniTable.setModel(aModel);
            return;
        }

        Object[] objects = new Object[6];
        for(int i=0; i<recensioni.size(); i++){
            objects[0]= recensioni.get(i).getAutore();
            objects[1]= recensioni.get(i).getCommento();
            objects[2]= recensioni.get(i).getStruttura();
            objects[3]= recensioni.get(i).getTitolo();
            objects[4]= recensioni.get(i).getUsername();
            objects[5]= recensioni.get(i).getVoto();
            
            aModel.addRow(objects);
        }
        
        this.GestioneRecensioniTable.setModel(aModel);
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
        GestioneRecensioniTable = new javax.swing.JTable();
        ProjectLabel = new javax.swing.JLabel();
        CompanyLabel = new javax.swing.JLabel();
        GestioneRecensioniLabel = new javax.swing.JLabel();
        OperatoreLabel = new javax.swing.JLabel();
        OperatoreField = new javax.swing.JTextField();
        EliminaButton = new javax.swing.JToggleButton();
        IndietroLabel = new javax.swing.JLabel();
        NomeStrutturaField = new javax.swing.JTextField();
        NomeUtenteField = new javax.swing.JTextField();
        NomeStrutturaLabel = new javax.swing.JLabel();
        NomeUtenteLabel = new javax.swing.JLabel();
        CercaButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ValidazioneRecensioniPanel.setBackground(new java.awt.Color(255, 255, 255));

        GestioneRecensioniTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        GestioneRecensioniTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GestioneRecensioniTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(GestioneRecensioniTable);

        ProjectLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ProjectLabel.setForeground(new java.awt.Color(51, 51, 255));
        ProjectLabel.setText("Consiglia Viaggi 19");

        CompanyLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        CompanyLabel.setForeground(new java.awt.Color(51, 51, 255));
        CompanyLabel.setText("SoftEngUniNA");

        GestioneRecensioniLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        GestioneRecensioniLabel.setForeground(new java.awt.Color(51, 51, 255));
        GestioneRecensioniLabel.setText("GESTIONE RECENSIONI");

        OperatoreLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        OperatoreLabel.setForeground(new java.awt.Color(51, 51, 255));
        OperatoreLabel.setText("Operatore:");

        OperatoreField.setEditable(false);
        OperatoreField.setBackground(new java.awt.Color(255, 255, 255));
        OperatoreField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        OperatoreField.setForeground(new java.awt.Color(51, 51, 255));
        OperatoreField.setBorder(null);

        EliminaButton.setBackground(new java.awt.Color(255, 0, 0));
        EliminaButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        EliminaButton.setForeground(new java.awt.Color(255, 255, 255));
        EliminaButton.setText("ELIMINA");
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

        NomeStrutturaField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        NomeStrutturaField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 255)));
        NomeStrutturaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeStrutturaFieldActionPerformed(evt);
            }
        });

        NomeUtenteField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        NomeUtenteField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 255)));
        NomeUtenteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeUtenteFieldActionPerformed(evt);
            }
        });

        NomeStrutturaLabel.setBackground(new java.awt.Color(255, 255, 255));
        NomeStrutturaLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        NomeStrutturaLabel.setForeground(new java.awt.Color(51, 51, 255));
        NomeStrutturaLabel.setText("Nome struttura");

        NomeUtenteLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        NomeUtenteLabel.setForeground(new java.awt.Color(51, 51, 255));
        NomeUtenteLabel.setText("Username");

        CercaButton.setBackground(new java.awt.Color(51, 51, 255));
        CercaButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        CercaButton.setForeground(new java.awt.Color(255, 255, 255));
        CercaButton.setText("CERCA");
        CercaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CercaButtonActionPerformed(evt);
            }
        });

        ResetButton.setBackground(new java.awt.Color(51, 204, 0));
        ResetButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ResetButton.setForeground(new java.awt.Color(255, 255, 255));
        ResetButton.setText("RESET");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ValidazioneRecensioniPanelLayout = new javax.swing.GroupLayout(ValidazioneRecensioniPanel);
        ValidazioneRecensioniPanel.setLayout(ValidazioneRecensioniPanelLayout);
        ValidazioneRecensioniPanelLayout.setHorizontalGroup(
            ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                        .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                                .addComponent(IndietroLabel)
                                .addGap(258, 258, 258)
                                .addComponent(GestioneRecensioniLabel))
                            .addComponent(CompanyLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ValidazioneRecensioniPanelLayout.createSequentialGroup()
                        .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                                .addComponent(ProjectLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(OperatoreLabel)
                                .addGap(4, 4, 4)
                                .addComponent(OperatoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(EliminaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NomeUtenteLabel)
                                    .addComponent(NomeStrutturaLabel)
                                    .addComponent(CercaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(NomeUtenteField)
                                    .addComponent(NomeStrutturaField)
                                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
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
                    .addComponent(GestioneRecensioniLabel)
                    .addComponent(IndietroLabel))
                .addGap(56, 56, 56)
                .addGroup(ValidazioneRecensioniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(NomeStrutturaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NomeStrutturaField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(NomeUtenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NomeUtenteField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(CercaButton)
                        .addGap(18, 18, 18)
                        .addComponent(ResetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CompanyLabel)
                        .addGap(20, 20, 20))
                    .addGroup(ValidazioneRecensioniPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EliminaButton)
                        .addContainerGap(71, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ValidazioneRecensioniPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ValidazioneRecensioniPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GestioneRecensioniTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GestioneRecensioniTableMouseClicked
        GestioneRecensioniTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String selected= GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(),1).toString();
                    JTextArea textArea = new JTextArea(selected, 1, 20);
                    JScrollPane scrollpane = new JScrollPane(textArea);
                    textArea.setWrapStyleWord(true);
                    textArea.setLineWrap(true);
                    textArea.setEditable(false);
                    scrollpane.setPreferredSize(new Dimension(400, 200));
                    JOptionPane.showMessageDialog(null, scrollpane, "INFO COMMENTO", JOptionPane.INFORMATION_MESSAGE);
                    GestioneRecensioniTable.clearSelection();
                }
            }
        });
    }//GEN-LAST:event_GestioneRecensioniTableMouseClicked

    private void EliminaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminaButtonActionPerformed
        if(GestioneRecensioniTable.getSelectedRow()!=-1){
        
            Recensione recensione= new Recensione();

            recensione.setAutore(GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 0).toString());
            recensione.setCommento(GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 1).toString());
            recensione.setPubblicata((boolean) GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 2));
            recensione.setStruttura(GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 3).toString());
            recensione.setTitolo(GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 4).toString());
            recensione.setUsername(GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 5).toString());
            recensione.setVoto((int) GestioneRecensioniTable.getValueAt(GestioneRecensioniTable.getSelectedRow(), 6));
            
            GestioneRecensioniController.clickEliminaRecensione(recensione);
            
            UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null,"Recensione eliminata correttamente!","ATTENZIONE!", JOptionPane.INFORMATION_MESSAGE);
            
            RiempiTabella();
            
            NomeStrutturaField.setText("");
            NomeUtenteField.setText("");
        }else{
            UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null,"Nessuna riga selezionata","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EliminaButtonActionPerformed

    private void IndietroLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IndietroLabelMouseClicked
        GestioneRecensioniController.clickIndietro(admin);
        this.dispose();        
    }//GEN-LAST:event_IndietroLabelMouseClicked

    private void NomeStrutturaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeStrutturaFieldActionPerformed
        
    }//GEN-LAST:event_NomeStrutturaFieldActionPerformed

    private void NomeUtenteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeUtenteFieldActionPerformed
        
    }//GEN-LAST:event_NomeUtenteFieldActionPerformed

    private void CercaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CercaButtonActionPerformed
        if(NomeStrutturaField.getText().isEmpty() && NomeUtenteField.getText().isEmpty()){
            UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.ITALIC, 16));
            JOptionPane.showMessageDialog(null,"Compilare almeno un campo!","ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
            
        }else{
            
            Recensione recensione= new Recensione();

            recensione.setStruttura(NomeStrutturaField.getText());
            recensione.setUsername(NomeUtenteField.getText());
            
            List<Recensione> recensioni= GestioneRecensioniController.clickCercaRecensione(recensione);
        
            DefaultTableModel aModel = new DefaultTableModel() {
            //setting the jtable read only

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }};

            //setting the column name
            Object[] tableColumnNames = new Object[6];
            tableColumnNames[0]= "Autore";
            tableColumnNames[1]= "Commento";
            tableColumnNames[2]= "Struttura";
            tableColumnNames[3]= "Titolo";
            tableColumnNames[4]= "Username";
            tableColumnNames[5]= "Voto";

            aModel.setColumnIdentifiers(tableColumnNames);
            if (recensioni == null) {
                this.GestioneRecensioniTable.setModel(aModel);
                return;
            }

            Object[] objects = new Object[6];
            for(int i=0; i<recensioni.size(); i++){
                objects[0]= recensioni.get(i).getAutore();
                objects[1]= recensioni.get(i).getCommento();
                objects[2]= recensioni.get(i).getStruttura();
                objects[3]= recensioni.get(i).getTitolo();
                objects[4]= recensioni.get(i).getUsername();
                objects[5]= recensioni.get(i).getVoto();

                aModel.addRow(objects);
            }
            
            this.GestioneRecensioniTable.setModel(aModel);
            
        }
    }//GEN-LAST:event_CercaButtonActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        RiempiTabella();
        NomeStrutturaField.setText("");
        NomeUtenteField.setText("");
    }//GEN-LAST:event_ResetButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CercaButton;
    private javax.swing.JLabel CompanyLabel;
    private javax.swing.JToggleButton EliminaButton;
    private javax.swing.JLabel GestioneRecensioniLabel;
    private javax.swing.JTable GestioneRecensioniTable;
    private javax.swing.JLabel IndietroLabel;
    private javax.swing.JTextField NomeStrutturaField;
    private javax.swing.JLabel NomeStrutturaLabel;
    private javax.swing.JTextField NomeUtenteField;
    private javax.swing.JLabel NomeUtenteLabel;
    private javax.swing.JTextField OperatoreField;
    private javax.swing.JLabel OperatoreLabel;
    private javax.swing.JLabel ProjectLabel;
    private javax.swing.JButton ResetButton;
    private javax.swing.JPanel ValidazioneRecensioniPanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
