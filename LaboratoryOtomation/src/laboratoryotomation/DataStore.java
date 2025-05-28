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
public class DataStore {

    public static ArrayList<User> allUsers = new ArrayList<>();
    public static ArrayList<Sample> allSamples = new ArrayList<>();
    public static User loggedInUser; // Logged in user is stored here

    //here add the list that will hold the test types:
    public static ArrayList<String> testTypes = new ArrayList<>();

    public static int currentDay = 1;

    public static void initializeUsers() {
        allUsers.add(new Doctor("dr1", "123"));
        allUsers.add(new LabTechnician("lab1", "123"));
        allUsers.add(new Patient("pt1", "123"));
    }

    public static void initializeTestTypes() {
        testTypes.add("Kan Testi");
        testTypes.add("İdrar Testi");
        testTypes.add("Röntgen");
    }

}
