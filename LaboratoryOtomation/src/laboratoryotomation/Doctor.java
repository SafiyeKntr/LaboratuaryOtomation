/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoryotomation;

/**
 *
 * @author safiyekantar
 */
public class Doctor extends User {

    public Doctor(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

}
