/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoryotomation;

/**
 *
 * @author safiyekantar
 */
public class Patient extends User {

    public Patient(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Patient";

    }

    // I override because the patient name was not written properly on the label.
    @Override
    public String toString() {
        return this.getUsername(); // This will show the correct name in lblWelcome
    }

}
