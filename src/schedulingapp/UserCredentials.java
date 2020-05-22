package schedulingapp;

import java.sql.SQLException;
import java.util.LinkedList;
import schedulingapp.Models.User;
import utils.DBConnection;


public class UserCredentials {
    public static String usernameEntered;
    public static void setUsername(String usernameToSet) {
        usernameEntered = usernameToSet;
    }
    public static String getUsername() { return usernameEntered; }
    public static User getCurrentUser() throws SQLException {
        try {
            String unameE = UserCredentials.getUsername();
            LinkedList<User> allUsers = (LinkedList<User>) DBConnection.getUsers();
            User currentUser = null;        
            for(int i = 0; i < allUsers.size(); i++) {         
                if (unameE.equals(allUsers.get(i).getUserName())) {                
                    currentUser = allUsers.get(i);                
                }
            }
            return currentUser;
            }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
        return null;
    }
    public static int getCurrentUserId() throws SQLException {
        try {
            String unameE = UserCredentials.getUsername();
            LinkedList<User> allUsers = (LinkedList<User>) DBConnection.getUsers();
            User currentUser = null;        
            for(int i = 0; i < allUsers.size(); i++) {            
                if (unameE.equals(allUsers.get(i).getUserName())) {                
                    currentUser = allUsers.get(i);                
                }            
            }
            return currentUser.getUserId();
        }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
        return -1;
    }
}
