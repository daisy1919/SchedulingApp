/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import schedulingapp.Models.Customer;
import schedulingapp.Models.User;

/**
 *
 * @author daisy
 */
public class DBConnection {
    
    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U05mZ3";
    
    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;
    
    //Driver and Connection Interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    //Username and Password
    private static final String dbUsername = "U05mZ3";
    private static final String dbPassword = "53688549123";
    
    public static Connection startConnection() {        
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }        
        return conn;        
    }
    
    public static void closeConnection() throws SQLException {        
        try {
            conn.close();
            System.out.println("Connection closed.");            
        }
        catch(SQLException e) {            
            System.out.println("Error: " + e.getMessage());            
        }        
    }
        
    public static Iterable<User> getUsers() throws SQLException {                
        Statement sqlStmt = null;
        ResultSet sqlRs = null;

        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM user");
            List<User> allUsers = new LinkedList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");            
            while(sqlRs.next()) {            
                User userToAdd = new User();                
                userToAdd.setUserId(Integer.parseInt(sqlRs.getString("userId")));
                userToAdd.setUserName(sqlRs.getString("userName"));
                userToAdd.setPassword(sqlRs.getString("password"));
                userToAdd.setActive(Integer.parseInt(sqlRs.getString("active")));
                userToAdd.setCreateDate(LocalDateTime.parse(sqlRs.getString("createDate"), formatter));
                userToAdd.setCreatedBy(sqlRs.getString("createdBy"));
                userToAdd.setLastUpdate(Timestamp.valueOf(LocalDateTime.parse(sqlRs.getString("lastUpdate"), formatter)));
                userToAdd.setLastUpdateBy(sqlRs.getString("lastUpdateBy"));
                allUsers.add(userToAdd);                
            }            
            return allUsers;            
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }        
        finally {            
            if(sqlRs != null || sqlStmt != null) {                
                try {                    
                    sqlRs.close();
                    sqlStmt.close();                    
                }                 
                catch(SQLException sqlEx) {}                
                sqlRs = null;
                sqlStmt = null;        
            }            
        }             
        return null;        
    }
        
    public static Iterable<Customer> getCustTableInfo() throws SQLException{
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM customer");
            List<Customer> customerTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                Customer customerToAdd = new Customer();
                customerToAdd.setName(sqlRs.getString("customerName"));
                customerToAdd.setCustId(sqlRs.getString("customerId"));
                customerToAdd.setAddId(sqlRs.getString("addressId"));
                customerTableInfo.add(customerToAdd);
            }            
            return customerTableInfo;            
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }        
        finally {            
            if(sqlRs != null || sqlStmt != null) {                
                try {                    
                    sqlRs.close();
                    sqlStmt.close();                    
                }                 
                catch(SQLException sqlEx) {}                
                sqlRs = null;
                sqlStmt = null;        
            }            
        }             
        return null;   
    }
    
    public static Iterable<Customer> getCustomers() throws SQLException {                
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        Statement sqlStmt2 = null;
        ResultSet sqlRs2 = null;
        
        Statement sqlStmt3 = null;
        ResultSet sqlRs3 = null;
        
        Statement sqlStmt4 = null;
        ResultSet sqlRs4 = null;

        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM customer");
            
            sqlStmt2 = conn.createStatement();
            sqlRs2 = sqlStmt2.executeQuery("SELECT * FROM address");
            
            sqlStmt3 = conn.createStatement();
            sqlRs3 = sqlStmt3.executeQuery("SELECT * FROM city");
            
            sqlStmt4 = conn.createStatement();
            sqlRs4 = sqlStmt4.executeQuery("SELECT * FROM country");            
            
            List<Customer> allCustomers = new LinkedList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            
            while(sqlRs.next()) {
                Customer customerToAdd = new Customer();
                customerToAdd.setName(sqlRs.getString("customerName")); //customerName is first and last, will need to split for forms
                customerToAdd.setAddId(sqlRs.getString("addressId"));                
                /*while(sqlRs2.next()) {
                    customerToAdd.setAdd1(sqlRs2.getString(""));
                    customerToAdd.setAdd2();
                    customerToAdd.setCityId();
                    customerToAdd.setZip();
                    customerToAdd.setPhone();
                    
                    while(sqlRs3.next()) {
                        customerToAdd.setCity();
                        customerToAdd.setCountryId();
                        
                        while(sqlRs4.next()) {
                            customerToAdd.setCountry();
                        }
                    }
                }    
                allCustomers.add(customerToAdd);*/
            }            
            return allCustomers;            
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }        
        finally {            
            if(sqlRs != null || sqlStmt != null) {                
                try {                    
                    sqlRs.close();
                    sqlStmt.close();                    
                }                 
                catch(SQLException sqlEx) {}                
                sqlRs = null;
                sqlStmt = null;        
            }            
        }             
        return null;        
    }

    public static int generateNewCustId() {                         
        Statement sqlStmtCId = null;
        ResultSet sqlRsCId = null;

        try {            
            sqlStmtCId = conn.createStatement();
            sqlRsCId = sqlStmtCId.executeQuery("SELECT * FROM customer");
            List<Integer> customerIdList = new LinkedList<>();          
            while(sqlRsCId.next()) {            
                int custIdToAdd = Integer.parseInt(sqlRsCId.getString("customerId"));
                customerIdList.add(custIdToAdd);
            }
            int largestId = customerIdList.get(0);
            for (int i = 0; i < customerIdList.size(); i++) {
                if (customerIdList.get(i) > largestId) {
                    largestId = customerIdList.get(i);
                }
            }
            return (largestId + 1);
        }
        catch(SQLException sqEx) { System.out.println("Exception " + sqEx.getMessage()); }
        return -1;
    }
    
    public static int generateNewAddId() {                         
        Statement sqlStmtAId = null;
        ResultSet sqlRsAId = null;

        try {            
            sqlStmtAId = conn.createStatement();
            sqlRsAId = sqlStmtAId.executeQuery("SELECT * FROM address");
            List<Integer> addressIdList = new LinkedList<>();          
            while(sqlRsAId.next()) {            
                int addIdToAdd = Integer.parseInt(sqlRsAId.getString("addressId"));
                addressIdList.add(addIdToAdd);
            }
            int largestId = addressIdList.get(0);
            for (int i = 0; i < addressIdList.size(); i++) {
                if (addressIdList.get(i) > largestId) {
                    largestId = addressIdList.get(i);
                }
            }
            return (largestId + 1);
        }
        catch(SQLException sqEx) { System.out.println("Exception " + sqEx.getMessage()); }
        return -1;
    }
    
    public static int generateNewCityId() {                         
        Statement sqlStmtCId = null;
        ResultSet sqlRsCId = null;

        try {            
            sqlStmtCId = conn.createStatement();
            sqlRsCId = sqlStmtCId.executeQuery("SELECT * FROM city");
            List<Integer> cityIdList = new LinkedList<>();          
            while(sqlRsCId.next()) {            
                int cityIdToAdd = Integer.parseInt(sqlRsCId.getString("cityId"));
                cityIdList.add(cityIdToAdd);
            }
            int largestId = cityIdList.get(0);
            for (int i = 0; i < cityIdList.size(); i++) {
                if (cityIdList.get(i) > largestId) {
                    largestId = cityIdList.get(i);
                }
            }
            return (largestId + 1);
        }
        catch(SQLException sqEx) { System.out.println("Exception " + sqEx.getMessage()); }
        return -1;
    }
    
    public static int generateNewCountryId() {                         
        Statement sqlStmtCId = null;
        ResultSet sqlRsCId = null;

        try {            
            sqlStmtCId = conn.createStatement();
            sqlRsCId = sqlStmtCId.executeQuery("SELECT * FROM country");
            List<Integer> countryIdList = new LinkedList<>();          
            while(sqlRsCId.next()) {            
                int countryIdToAdd = Integer.parseInt(sqlRsCId.getString("countryId"));
                countryIdList.add(countryIdToAdd);
            }
            int largestId = countryIdList.get(0);
            for (int i = 0; i < countryIdList.size(); i++) {
                if (countryIdList.get(i) > largestId) {
                    largestId = countryIdList.get(i);
                }
            }
            return (largestId + 1);
        }
        catch(SQLException sqEx) { System.out.println("Exception " + sqEx.getMessage()); }
        return -1;
    }
    
    public static void addCustomer(String custName, String custAddress, String custAddress2, String custCity, String custZip, String custCountry, String custPhone, String unameEntered) {                          
        PreparedStatement sqlStmtCo = null;
        PreparedStatement sqlStmtCi = null;
        PreparedStatement sqlStmtA = null;
        PreparedStatement sqlStmtC = null;
              
        String sqlCountryToEx = "INSERT INTO country (countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?)";
        String sqlCityToEx = "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?)";
        String sqlAddToEx = "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String sqlCustToEx = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?,?)";
        
        String currDateTime = java.time.LocalDateTime.now().toString();
        
        try {                               
            String newCountryId = String.valueOf(generateNewCountryId());
            String newCityId = String.valueOf(generateNewCityId());
            String newAddId = String.valueOf(generateNewAddId());
            String newCustId = String.valueOf(generateNewCustId());
            
            sqlStmtCo = conn.prepareStatement(sqlCountryToEx);
            sqlStmtCo.setString(1, newCountryId);
            sqlStmtCo.setString(2, custCountry);
            sqlStmtCo.setString(3, currDateTime);
            sqlStmtCo.setString(4, unameEntered);
            sqlStmtCo.setString(5, currDateTime);
            sqlStmtCo.setString(6, unameEntered);
            sqlStmtCo.executeUpdate();
            
            sqlStmtCi = conn.prepareStatement(sqlCityToEx);
            sqlStmtCi.setString(1, newCityId);
            sqlStmtCi.setString(2, custCity);
            sqlStmtCi.setString(3, newCountryId);
            sqlStmtCi.setString(4, currDateTime);
            sqlStmtCi.setString(5, unameEntered);
            sqlStmtCi.setString(6, currDateTime);
            sqlStmtCi.setString(7, unameEntered);
            sqlStmtCi.executeUpdate();
            
            sqlStmtA = conn.prepareStatement(sqlAddToEx);
            sqlStmtA.setString(1, newAddId);
            sqlStmtA.setString(2, custAddress);
            sqlStmtA.setString(3, custAddress2);
            sqlStmtA.setString(4, newCityId);
            sqlStmtA.setString(5, custZip);
            sqlStmtA.setString(6, custPhone);
            sqlStmtA.setString(7, currDateTime);
            sqlStmtA.setString(8, unameEntered);
            sqlStmtA.setString(9, currDateTime);
            sqlStmtA.setString(10, unameEntered);
            sqlStmtA.executeUpdate();
            
            sqlStmtC = conn.prepareStatement(sqlCustToEx);
            sqlStmtC.setString(1, newCustId);
            sqlStmtC.setString(2, custName);
            sqlStmtC.setString(3, newAddId);
            sqlStmtC.setString(4, "1");
            sqlStmtC.setString(5, currDateTime);
            sqlStmtC.setString(6, unameEntered);
            sqlStmtC.setString(7, currDateTime);
            sqlStmtC.setString(8, unameEntered);
            sqlStmtC.executeUpdate();
        }
        catch(SQLException sqEx) {
            System.out.println("Exception " + sqEx.getMessage());
        }        
    }
}
