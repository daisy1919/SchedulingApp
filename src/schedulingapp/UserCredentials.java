/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

/**
 *
 * @author daisy
 */
public class UserCredentials {
    public static String usernameEntered;
    public static void setUsername(String usernameToSet) {
        usernameEntered = usernameToSet;
    }
    public static String getUsername() { return usernameEntered; }
}
