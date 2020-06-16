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
    
    public static void userLoginTimes(String username, String loginDateTime, String userTZ) throws IOException {
        String filename = "userLoginTimes.txt", usernameAndLocalTime;
        FileWriter fWriter = new FileWriter(filename, true);
        PrintWriter logFile = new PrintWriter(fWriter);
        usernameAndLocalTime = "A user with the username of " + username + " logged in at " + loginDateTime + " in their local timezone, which is " + userTZ + ".\n";
        logFile.println(usernameAndLocalTime);
        logFile.close();
    }
    
}
