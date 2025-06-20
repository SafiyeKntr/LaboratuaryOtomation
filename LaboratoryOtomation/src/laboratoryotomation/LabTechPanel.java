/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package laboratoryotomation;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author safiyekantar
 */
public class LabTechPanel extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private Patient currentPatient;

    /**
     * Creates new form LabTechPanel
     */
    public LabTechPanel() {
        initComponents();
        initTableModel();
    }

    private void initTableModel() {
        tableModel = new DefaultTableModel(new Object[]{"Sample No", "Test ID", "Test Name", "Parameters", "Results", "Status"}, 0) {

        };
        tblResult.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPtnId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        btnGetTests = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAssignSampleNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PatientID:");

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sample No", "Test ID", "Test Name", "Parameters", "Result", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblResult);

        btnGetTests.setText("Get Tests");
        btnGetTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetTestsActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnAssignSampleNo.setText("assign sample no");
        btnAssignSampleNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignSampleNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPtnId, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnGetTests, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSave))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(btnAssignSampleNo))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPtnId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnGetTests)
                    .addComponent(btnSave))
                .addGap(9, 9, 9)
                .addComponent(btnAssignSampleNo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getTests() {
        String id = txtPtnId.getText().trim();
        currentPatient = null;
        tableModel.setRowCount(0);

        try {
            for (User u : DataStore.allUsers) {
                if (u instanceof Patient && u.getUsername().equals(id)) {
                    currentPatient = (Patient) u;
                    break;
                }
            }

            if (currentPatient == null) {
                JOptionPane.showMessageDialog(this, "Patient not found.");
                return;
            }

            boolean hasTests = false;

            for (Sample s : DataStore.allSamples) {
                if (s.getPatient().getUsername().equals(id)) {
                    hasTests = true;
                    String sampleNo = s.getSampleID() == null ? "" : s.getSampleID();

                    for (MedicalTest t : s.getAssignedTests()) {
                        boolean allParamsFilled = true;
                        for (TestParameter p : t.getParameters()) {
                            if (p.getResult().trim().isEmpty()) {
                                allParamsFilled = false;
                                break;
                            }
                        }

                        for (TestParameter p : t.getParameters()) {
                            String result = p.getResult();
                            String status;

                            if (s.getSampleID() == null) {
                                status = "Pending";
                            } else {
                                if (allParamsFilled) {
                                    status = "Completed";
                                } else {
                                    status = "In Progress";
                                }
                            }

                            tableModel.addRow(new Object[]{
                                sampleNo,
                                t.getTestID(),
                                t.getTestName(),
                                p.getParameterName(),
                                result,
                                status
                            });
                        }
                    }
                }
            }

            if (!hasTests) {
                JOptionPane.showMessageDialog(this, "No tests assigned to this patient.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while retrieving tests: " + e.getMessage());
        }
    }
    private void btnGetTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetTestsActionPerformed
        getTests();

    }//GEN-LAST:event_btnGetTestsActionPerformed

    private void btnAssignSampleNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignSampleNoActionPerformed
        int[] selectedRows = tblResult.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one line to assign a sample number.");
            return;
        }

        boolean sampleAssignedWarningShown = false;

        for (int row : selectedRows) {
            String sampleNo = tableModel.getValueAt(row, 0).toString();

            // If sample no already exists skip this line and warn the user.
            if (!sampleNo.trim().isEmpty()) {
                sampleAssignedWarningShown = true;
                continue;
            }

            String testID = tableModel.getValueAt(row, 1).toString();

            // Find the relevant test
            for (Sample s : DataStore.allSamples) {
                if (s.getPatient().getUsername().equals(currentPatient.getUsername())) {
                    for (MedicalTest t : s.getAssignedTests()) {
                        if (t.getTestID().equals(testID)) {
                            // create sampleID
                            s.assignSampleIDIfNeeded();
                            // Update Status
                            t.setStatus("In Progress");
                        }
                    }
                }
            }
        }

        if (sampleAssignedWarningShown) {
            JOptionPane.showMessageDialog(this, "Some selected tests have already been assigned samples. No assignment was made.");
        } else {
            JOptionPane.showMessageDialog(this, "Sample number assigned and test status updated.");
        }

        getTests(); // update table

    }//GEN-LAST:event_btnAssignSampleNoActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean hasError = false;

        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String sampleNo = tableModel.getValueAt(i, 0).toString().trim();
                String testID = tableModel.getValueAt(i, 1).toString();
                String paramName = tableModel.getValueAt(i, 3).toString();
                String result = tableModel.getValueAt(i, 4).toString().trim();

                if (sampleNo.isEmpty() && !result.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Results cannot be entered without assigning a Sample No. Please assign a sample number first.");
                    return;
                }

                for (Sample s : DataStore.allSamples) {
                    for (MedicalTest t : s.getAssignedTests()) {
                        if (t.getTestID().equals(testID)) {
                            for (TestParameter p : t.getParameters()) {
                                if (p.getParameterName().equals(paramName)) {
                                    p.setResult(result);
                                }
                            }
                        }
                    }
                }
            }

            for (Sample s : DataStore.allSamples) {
                for (MedicalTest t : s.getAssignedTests()) {
                    boolean atLeastOneResultEntered = false;
                    boolean allParamsFilled = true;

                    for (TestParameter p : t.getParameters()) {
                        if (!p.getResult().trim().isEmpty()) {
                            atLeastOneResultEntered = true;
                        } else {
                            allParamsFilled = false;
                        }
                    }

                    if (atLeastOneResultEntered && !allParamsFilled) {
                        hasError = true;
                        JOptionPane.showMessageDialog(this, "Test ID " + t.getTestID() + " All parameters must be entered for.");
                    }
                }
            }

            if (!hasError) {
                for (Sample s : DataStore.allSamples) {
                    for (MedicalTest t : s.getAssignedTests()) {
                        if (t.isFullyCompleted()) {
                            t.setStatus("Completed");
                        } else if (t.getStatus().equals("Pending")) {
                            t.setStatus("In Progress");
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Results saved.");
            }

            getTests();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the results: " + e.getMessage());
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        Login loginScreen = new Login();
        loginScreen.setVisible(true);

        // Close current screen
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(LabTechPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LabTechPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LabTechPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LabTechPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LabTechPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignSampleNo;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGetTests;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtPtnId;
    // End of variables declaration//GEN-END:variables
}
