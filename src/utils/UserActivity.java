/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author daisy
 */
public class UserActivity {
    
    private static PrintWriter createLogFile() throws IOException {
        return new PrintWriter("userLoginTimes.txt");
    }
    
    private static PrintWriter getLogFile() throws IOException {
        return createLogFile();
    }
            
    //addLogin inside this function: check if file does not exist, then call private createfile function
            //else call loadfile to addrecord
    
    public static void addUserLogin() throws IOException {
        String filename = "groceries.txt", item;
	Scanner keyboard = new Scanner(System.in);
	System.out.println("How many items do you have?");
	int numItems = keyboard.nextInt();
	keyboard.nextLine();
	PrintWriter outputFile = new PrintWriter(filename);
	for (int i = 0; i < numItems; i++) {
            System.out.print("Enter item " + (i+1) + ": ");
            item = keyboard.nextLine();
            outputFile.println();
	}
	outputFile.close();
	System.out.println("File written");
	//create it / don't create if exists
	//write to end of file
    }
    
}
