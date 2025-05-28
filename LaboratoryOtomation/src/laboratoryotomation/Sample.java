/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoryotomation;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author safiyekantar
 */
public class Sample {
    // started the sample number from 1 and increase it to the doctor or laboratory who will assign the system 
    private static int sampleCounter = 1;

    private String sampleID;
    private Patient patient;
    private ArrayList<MedicalTest> assignedTests;
    private int sampleDay;

    public Sample(Patient patient) {
        this.sampleID = null; //Do not assign sampleID at startup!
        this.patient = patient;
        this.assignedTests = new ArrayList<>();
        this.sampleDay = DataStore.currentDay;
    }

    public int getSampleDay() {
        return sampleDay;
    }

    public static void setSampleCounter(int sampleCounter) {
        Sample.sampleCounter = sampleCounter;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAssignedTests(ArrayList<MedicalTest> assignedTests) {
        this.assignedTests = assignedTests;
    }

    private String generateSampleID() {
        return String.format("SMP%04d", sampleCounter++);
    }

    public void assignSampleIDIfNeeded() {
        if (this.sampleID == null) {
            this.sampleID = generateSampleID();
        }
    }

    public void addTest(MedicalTest test) {
        assignedTests.add(test);
    }

    // Getter ve Setterlar
    public String getSampleID() {
        return sampleID;
    }

    public Patient getPatient() {
        return patient;
    }

    public ArrayList<MedicalTest> getAssignedTests() {
        return assignedTests;
    }

    @Override
    public String toString() {
        return sampleID + " - " + patient.getUsername();
    }
}

