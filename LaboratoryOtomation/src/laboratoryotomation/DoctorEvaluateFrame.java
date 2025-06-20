package laboratoryotomation;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author safiyekantar
 */

/*Doctor's test evaluation interface for reviewing and assessing lab results.
Displays patient's tests in a table and allows marking parameters as Normal/Abnormal.*/

public class DoctorEvaluateFrame extends javax.swing.JFrame {

    private Patient selectedPatient;
    
    private DefaultTableModel model;

    /**
     * Creates new form DoctorPanelEvaluateResults
     */
    public DoctorEvaluateFrame() {
        initComponents();
        model = (DefaultTableModel) tblEvaluate.getModel();
    }

    public void loadTestData(Patient patient) {
        try {
            DefaultTableModel model = (DefaultTableModel) tblEvaluate.getModel();
            model.setRowCount(0);

            for (Sample s : DataStore.allSamples) {
                if (!s.getPatient().equals(patient)) {
                    continue;
                }

                for (MedicalTest t : s.getAssignedTests()) {
                    for (TestParameter p : t.getParameters()) {
                        String result = p.getResult();
                        
                        if (result == null || result.trim().isEmpty()) {
                            result = "Result not entered";
                        }

                        String evaluation = (p.getEvaluation() == null || p.getEvaluation().isEmpty()) ? "Not evaluated" : p.getEvaluation();

                        model.addRow(new Object[]{
                            t.getTestID(),
                            t.getTestName(),
                            p.getParameterName(),
                            result,
                            evaluation
                        });
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while loading data: " + e.getMessage());
        }
    }

    public void setSelectedPatient(Patient p) {
        this.selectedPatient = p;
    }

    private void evaluateSelected(String evaluation) {
        try {
            int selectedRow = tblEvaluate.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row.");
                return;
            }

            String result = model.getValueAt(selectedRow, 3).toString().trim();

            if (result.isEmpty() || result.equalsIgnoreCase("Result not entered")) {
                JOptionPane.showMessageDialog(this, "No results have been entered for this parameter yet. Laboratory results must be entered first.");
                return;
            }

            model.setValueAt(evaluation, selectedRow, 4);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred during evaluation: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvaluate = new javax.swing.JTable();
        btnNormal = new javax.swing.JButton();
        btnAbnormal = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEvaluate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Test ID", "Test Name", "Parameters", "Results", "Evaluate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEvaluate);

        btnNormal.setText("Normal");
        btnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNormalActionPerformed(evt);
            }
        });

        btnAbnormal.setText("Abnormal");
        btnAbnormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbnormalActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setText("back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNormal)
                .addGap(18, 18, 18)
                .addComponent(btnAbnormal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNormal)
                    .addComponent(btnAbnormal)
                    .addComponent(btnSave))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnBack)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* Saves all evaluations to the DataStore.
     * Validates that all tests with results have evaluations.*/
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            boolean anyResultEntered = false;
            boolean anyEvaluationDone = false;

            for (int i = 0; i < model.getRowCount(); i++) {
                String result = model.getValueAt(i, 3).toString().trim();
                String evaluation = model.getValueAt(i, 4).toString().trim();

                if (!result.equalsIgnoreCase("Result not entered")) {
                    anyResultEntered = true;
                }
                if (!evaluation.equalsIgnoreCase("Not evaluated")) {
                    anyEvaluationDone = true;
                }
            }

            if (!anyResultEntered && !anyEvaluationDone) {
                JOptionPane.showMessageDialog(this, "No test results have been entered yet and no evaluation has been made.");
                return;
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String result = model.getValueAt(i, 3).toString().trim();
                String evaluation = model.getValueAt(i, 4).toString().trim();

                if (!result.equalsIgnoreCase("Result not entered") && evaluation.equalsIgnoreCase("Not evaluated")) {
                    JOptionPane.showMessageDialog(this, "All tests with entered results must be evaluated. Missing line: " + (i + 1));
                    return;
                }
            }
            
            // Save to DataStore 
            for (int i = 0; i < model.getRowCount(); i++) {
                String testID = model.getValueAt(i, 0).toString();
                String paramName = model.getValueAt(i, 2).toString();
                String evaluation = model.getValueAt(i, 4).toString();

                for (Sample s : DataStore.allSamples) {
                    for (MedicalTest t : s.getAssignedTests()) {
                        if (t.getTestID().equals(testID)) {
                            for (TestParameter p : t.getParameters()) {
                                if (p.getParameterName().equals(paramName)) {
                                    p.setEvaluation(evaluation);
                                }
                            }
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Evaluations recorded successfully.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the assessment: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNormalActionPerformed
        evaluateSelected("Normal");
    }//GEN-LAST:event_btnNormalActionPerformed

    private void btnAbnormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbnormalActionPerformed
        evaluateSelected("Abnormal");
    }//GEN-LAST:event_btnAbnormalActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        DoctorPanel dc = new DoctorPanel();
        dc.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoctorEvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorEvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorEvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorEvaluateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorEvaluateFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbnormal;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNormal;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEvaluate;
    // End of variables declaration//GEN-END:variables
}
