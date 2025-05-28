/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoryotomation;

import java.util.ArrayList;

/**
 *
 * @author safiyekantar
 */
public class LoginManager {

    public static User authenticate(String username, String password, String role, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getRole().equals(role)) {
                System.out.println("Rol: " + user.getRole()); // Polymorphic
                return user;
            }
        }
        return null;
    }

}
