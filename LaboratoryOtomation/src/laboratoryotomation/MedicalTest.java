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

/* sample number and test ID are different things, for example test1 is a blood test, 
test2 is a urine test. Here I am assigning random IDs to the tests. */

public class MedicalTest {

    private String testName;                    
    private ArrayList<TestParameter> parameters; 
    private String testID; // Unique ID of the test
    private String status;
    private LocalDate assignedDate;

    public MedicalTest(String testName) {
        this.testName = testName;
        this.parameters = new ArrayList<>();
        this.testID = generateTestID();
        this.status = "Pending"; // pending at startup.
        this.assignedDate = Login.simulatedDate;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public boolean isFullyCompleted() {
        for (TestParameter p : parameters) {
            if (p.getResult() == null || p.getResult().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Create a test ID (example: "TEST001")
    private String generateTestID() {
        return "TEST" + String.format("%03d", (int) (Math.random() * 1000));
    }

    // Add parameters
    public void addParameter(TestParameter param) {
        parameters.add(param);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public ArrayList<TestParameter> getParameters() {
        return parameters;
    }

    public String getTestID() {
        return testID;
    }

    @Override
    public String toString() {
        return testName + " - " + testID + " (" + status + ")";
    }
}
