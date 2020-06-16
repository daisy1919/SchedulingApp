/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.time.LocalDateTime;

/**
 *
 * @author daisy
 */
public class UserActivity {
    
    private static PrintWriter createLogFile() throws IOException {
        String filename = "userLoginTimes.txt";
        FileWriter fWriter = new FileWriter(filename, true);
        PrintWriter logFile = new PrintWriter(fWriter);
        return logFile;
    }
    
    private static PrintWriter getLogFile() throws IOException {
        return createLogFile();
    }
    
    public static void addStringToFile(String username, LocalDateTime loginDateTime, String userTZ) throws IOException {
        String usernameAndLocalTime = "User " + username + " logged in at " + loginDateTime.toString() + " in their local timezone, which is " + userTZ + ".\n";
        getLogFile().println(usernameAndLocalTime);
        getLogFile().close();
    }

    /*
    public static void addUserLogin(String username, LocalDateTime loginDateTime) throws IOException {
        
        if(getLogFile().equals(null)) {
            createLogFile();
            addStringToFile(username, loginDateTime);
            
        }
        else {
            addStringToFile(username, loginDateTime);
        }
        
    }
    */
    
}
