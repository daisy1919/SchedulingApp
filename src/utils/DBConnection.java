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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableList;
import schedulingapp.Models.Customer;
import schedulingapp.Models.User;
import schedulingapp.Models.Address;
import schedulingapp.Models.Appointment;
import schedulingapp.Models.City;
import schedulingapp.Models.Country;
import schedulingapp.UserCredentials;

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
    //end of db connection functions//
    /*****/
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
        
    public static Iterable<Customer> getCustomerTableInfo() throws SQLException{
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM customer");
            List<Customer> customerTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                Customer customerToAdd = new Customer();
                customerToAdd.setCustomerName(sqlRs.getString("customerName"));
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

    public static Iterable<Address> getAddressTableInfo() throws SQLException {
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM address");
            List<Address> addressTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                Address addressToAdd = new Address();
                addressToAdd.setAddressId(sqlRs.getString("addressId"));
                addressToAdd.setAddress1(sqlRs.getString("address"));
                addressToAdd.setAddress2(sqlRs.getString("address2"));
                addressToAdd.setCityId(sqlRs.getString("cityId"));                
                addressToAdd.setPostalCode(sqlRs.getString("postalCode"));
                addressToAdd.setPhone(sqlRs.getString("phone"));
                addressTableInfo.add(addressToAdd);
            }            
            return addressTableInfo;            
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
    
    public static Iterable<City> getCityTableInfo() throws SQLException {
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM city");
            List<City> cityTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                City cityToAdd = new City();
                cityToAdd.setCityId(sqlRs.getString("cityId"));
                cityToAdd.setCity(sqlRs.getString("city"));
                cityToAdd.setCountryId(sqlRs.getString("countryId"));
                cityTableInfo.add(cityToAdd);
            }            
            return cityTableInfo;            
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
        
    public static Iterable<Country> getCountryTableInfo() throws SQLException {
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM country");
            List<Country> countryTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                Country countryToAdd = new Country();
                countryToAdd.setCountryId(sqlRs.getString("countryId"));
                countryToAdd.setCountry(sqlRs.getString("country"));
                countryTableInfo.add(countryToAdd);
            }            
            return countryTableInfo;            
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
        try {            
            Iterable<Customer> customers = getCustomerTableInfo();
            Iterable<Address> addresses = getAddresses();
            for(Customer customer : customers) {
                customer.getAddId();
                for(Address address : addresses) {
                    if(customer.getAddId().equals(address.getAddressId())) {
                        customer.setAddress(address);
                    }
                }
            }
            return customers;
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }            
        return null;  
    }
    
    public static Iterable<Address> getAddresses() throws SQLException {
        try {
            Iterable<Address> addresses = getAddressTableInfo();
            addresses = DBConnection.attachCity(addresses);
            return addresses;
        }
        catch(SQLException sqEx) { System.out.println("Error " + sqEx.getMessage()); }
        return null;
    }
    
    public static Iterable<Address> attachCity(Iterable<Address> addresses) throws SQLException {
        try {
            Iterable<City> cities = getCities();
            for(Address address : addresses) {
                address.getCityId();
                for(City city : cities) {
                    if(address.getCityId().equals(city.getCityId())) {
                        address.setCity(city);
                    }
                }
            }
            return addresses;
        }        
        catch(SQLException ex) { System.out.println("Exception " + ex.getMessage()); }            
        return null;
    }
    
    public static Iterable<City> getCities() throws SQLException {
        try {
            Iterable<City> cities = getCityTableInfo();
            cities = DBConnection.attachCountry(cities);
            return cities;
        }
        catch(SQLException sqEx) { System.out.println("Error " + sqEx.getMessage()); }
        return null;
    }

    public static Iterable<City> attachCountry(Iterable<City> cities) throws SQLException {
        try {
            Iterable<Country> countries = getCountryTableInfo();
            for(City city : cities) {
                city.getCountryId();
                for(Country country : countries) {
                    if(city.getCountryId().equals(country.getCountryId())) {
                        city.setCountry(country);
                    }
                }
            }
            return cities;
        }
        catch(SQLException sqEx) { System.out.println("Error " + sqEx.getMessage()); }
        return null;
    }
    
    public static Iterable<Country> getCountries() throws SQLException {
        try {            
            Iterable<City> cities = getCityTableInfo();
            Iterable<Country> countries = getCountryTableInfo();
            for(City city : cities) {
                city.getCountryId();
                for(Country country : countries) {
                    if(city.getCountryId().equals(country.getCountryId())) {
                        city.setCountry(country);
                    }
                }
            }
            return countries;
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }
        return null;
    }
    
    public static Iterable<Customer> searchByName(String customerName) throws SQLException {
        try {
            Iterable<Customer> customers = getCustomers();
            List<Customer> foundCustomers = new LinkedList<>();
            for(Customer customer : customers) {
                if(customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                    foundCustomers.add(customer);
                }
            }
            return foundCustomers;
        }
        catch(SQLException sqEx) {  System.out.println("Error " + sqEx.getMessage()); }
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
        
    public static void addCustomer(String custName, String custAddress, String custAddress2, 
                                   String custCity, String custZip, String custCountry, 
                                   String custPhone, String unameEntered) {                          

        PreparedStatement sqlStmtCo = null;
        PreparedStatement sqlStmtCi = null;
        PreparedStatement sqlStmtA = null;
        PreparedStatement sqlStmtC = null;
              
        String sqlCountryToEx = "INSERT INTO country (countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?)";
        String sqlCityToEx = "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?)";
        String sqlAddToEx = "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        String sqlCustToEx = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
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
    
    public static void updateCustomer(Customer selectedCustomer, String custName, String lastUpdate, String lastUpdateBy, String custId, 
                                      String add1, String add2, String postCode, String phone, String addId,
                                      String city, String cityId,
                                      String country, String countryId) throws SQLException{
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        String sql = "UPDATE customer SET customerName = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?";
        String sql2 = "UPDATE address SET address = ?, address2 = ?, postalCode = ?, phone = ?, lastUpdate = ?, lastUpdateBy = ? WHERE addressId = ?";
        String sql3 = "UPDATE city SET city = ?, lastUpdate = ?, lastUpdateBy = ? WHERE cityId = ?";
        String sql4 = "UPDATE country SET country = ?, lastUpdate = ?, lastUpdateBy = ? WHERE countryId = ?";
        try {
            stmt = conn.prepareStatement(sql);            
            stmt.setString(1, custName);
            stmt.setString(2, lastUpdate);
            stmt.setString(3, lastUpdateBy);
            stmt.setString(4, custId);
            stmt.executeUpdate();
            
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1, add1);
            stmt2.setString(2, add2);
            stmt2.setString(3, postCode);
            stmt2.setString(4, phone);
            stmt2.setString(5, lastUpdate);
            stmt2.setString(6, lastUpdateBy);
            stmt2.setString(7, addId);
            stmt2.executeUpdate();
            
            stmt3 = conn.prepareStatement(sql3);
            stmt3.setString(1, city);
            stmt3.setString(2, lastUpdate);
            stmt3.setString(3, lastUpdateBy);
            stmt3.setString(4, cityId);
            stmt3.executeUpdate();
            
            stmt4 = conn.prepareStatement(sql4);
            stmt4.setString(1, country);
            stmt4.setString(2, lastUpdate);
            stmt4.setString(3, lastUpdateBy);
            stmt4.setString(4, countryId);
            stmt4.executeUpdate();
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }

    //deletes all data related to the selected appointment from 5 tables: appointment, appointment, city, country, appointment
    public static void deleteCustomer(String custId, String addressId, String cityId, String countryId) {
        PreparedStatement stmt = null;
        String sqlToEx = "DELETE FROM customer WHERE customerId=?";
        
        PreparedStatement stmt2 = null;
        String sql2ToEx = "DELETE FROM address WHERE addressId=?";
        
        PreparedStatement stmt3 = null;
        String sql3ToEx = "DELETE FROM city WHERE cityId=?";
        
        PreparedStatement stmt4 = null;
        String sql4ToEx = "DELETE FROM country WHERE countryId=?";
        
        PreparedStatement stmt5 = null;
        String sql5ToEx = "DELETE FROM appointment WHERE customerId = ?";
        
        try {
            stmt5 = conn.prepareStatement(sql5ToEx);
            stmt5.setString(1, custId);            
            stmt5.executeUpdate();
            
            stmt = conn.prepareStatement(sqlToEx);
            stmt.setString(1, custId);            
            stmt.executeUpdate();
            
            stmt2 = conn.prepareStatement(sql2ToEx);
            stmt2.setString(1, addressId);            
            stmt2.executeUpdate();
            
            stmt3 = conn.prepareStatement(sql3ToEx);
            stmt3.setString(1, cityId);            
            stmt3.executeUpdate();
            
            stmt4 = conn.prepareStatement(sql4ToEx);
            stmt4.setString(1, countryId);            
            stmt4.executeUpdate();
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }

    //The following function gets the data from SQL and adds it to a list of appointments
    public static Iterable<Appointment> getAppointmentTableInfo() throws SQLException{
        Statement sqlStmt = null;
        ResultSet sqlRs = null;
        
        try {            
            sqlStmt = conn.createStatement();
            sqlRs = sqlStmt.executeQuery("SELECT * FROM appointment");
            List<Appointment> appointmentTableInfo = new LinkedList<>();            
            while(sqlRs.next()) {
                Appointment appointmentToAdd = new Appointment();
                appointmentToAdd.setAppointmentId(sqlRs.getString("appointmentId"));
                appointmentToAdd.setCustomerId(sqlRs.getString("customerId"));
                appointmentToAdd.setUserId(sqlRs.getString("userId"));
                appointmentToAdd.setContact(sqlRs.getString("contact"));
                appointmentToAdd.setDescription(sqlRs.getString("description"));
                appointmentToAdd.setStartTime(sqlRs.getString("start"));
                appointmentToAdd.setEndTime(sqlRs.getString("end"));
                appointmentToAdd.setLocation(sqlRs.getString("location"));
                appointmentToAdd.setTitle(sqlRs.getString("title"));
                appointmentToAdd.setType(sqlRs.getString("type"));
                appointmentToAdd.setUrl(sqlRs.getString("url"));
                appointmentTableInfo.add(appointmentToAdd);
            }            
            return appointmentTableInfo;            
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
    
    //The following function generates the Appointment object/model including attaching the customer and user 
    public static Iterable<Appointment> getAppointments() throws SQLException {                
        try {            
            Iterable<Appointment> appointments = getAppointmentTableInfo();
            Iterable<Customer> customers = getCustomers();
            Iterable<User> users = getUsers();
            for(Appointment appointment : appointments) {
                appointment.getCustomerId();
                for(Customer customer : customers) {
                    if(appointment.getCustomerId().equals(customer.getCustId())) {
                        appointment.setCustomer(customer);
                    }
                }
                for(User user : users) {
                    if(appointment.getUserId().equals(user.getUserId())) {
                        appointment.setUser(user);
                    }
                }
            }
            return appointments;
        }        
        catch(SQLException ex) {            
            System.out.println("Exception " + ex.getMessage());            
        }            
        return null;  
    }
    
    public static int generateNewApptId() {                         
        Statement stmt = null;
        ResultSet rs = null;

        try {            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM appointment");
            List<Integer> appointmentIdList = new LinkedList<>();          
            while(rs.next()) {            
                int apptIdToAdd = Integer.parseInt(rs.getString("appointmentId"));
                appointmentIdList.add(apptIdToAdd);
            }
            int largestId = appointmentIdList.get(0);
            for (int i = 0; i < appointmentIdList.size(); i++) {
                if (appointmentIdList.get(i) > largestId) {
                    largestId = appointmentIdList.get(i);
                }
            }
            return (largestId + 1);
        }
        catch(SQLException sqEx) { System.out.println("Exception " + sqEx.getMessage()); }
        return -1;
    }

    //The following function adds a new appointment to the database
    public static void addAppointment(String customerId, int userId, String title, String description, String location, String contact, String type, String url,
                                      String start, String end, String unameEntered) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO appointment "
                + "(appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String currDateTime = java.time.LocalDateTime.now().toString();
        
        try {
            String newApptId = String.valueOf(generateNewApptId());
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newApptId);
            stmt.setString(2, customerId);
            stmt.setString(3, String.valueOf(userId));
            stmt.setString(4, title);
            stmt.setString(5, description);
            stmt.setString(6, location);
            stmt.setString(7, contact);
            stmt.setString(8, type);
            stmt.setString(9, url);
            stmt.setString(10, start);
            stmt.setString(11, end);
            stmt.setString(12, currDateTime);
            stmt.setString(13, unameEntered);
            stmt.setString(14, currDateTime);
            stmt.setString(15, unameEntered);
            stmt.executeUpdate();
        }
        catch(SQLException ex) { System.out.println("Error " + ex.getMessage()); }
    }
    
    //The following function generates all possible appointment times (3 appointments per hour for each hour of the business day)
    public static Iterable<Appointment> generateAllApptTimes(final int startOfDay, final int endOfDay, LocalDate desiredDate) {
        ArrayList<Appointment> allPossibleAppts = new ArrayList<>();
        final int numberOfAppts = 3 * (endOfDay - startOfDay);
        int currHour = startOfDay;
        int currMin = 0;
        String dateToAppend = desiredDate.toString();
        for (int i = 0; i < numberOfAppts; i++) {
            Appointment appointment = new Appointment();
            String timeT;
            if(currHour < 10) {
                if (currMin == 0) { timeT = dateToAppend + " 0" + currHour + ":" + currMin + "0:00"; }
                else { timeT = dateToAppend + " 0" + currHour + ":" + currMin + ":00"; }
                currMin = currMin + 20;
                if (currMin%60 == 0 && currMin != 0) {
                    currMin = 0;
                    currHour = currHour + 1;
                }
            }
            else {
                if (currMin == 0) { timeT = dateToAppend + " " + currHour + ":" + currMin + "0:00"; }
                else { timeT = dateToAppend + " " + currHour + ":" + currMin + ":00"; }
                currMin = currMin + 20;
                if (currMin%60 == 0 && currMin != 0) {
                    currMin = 0;
                    currHour = currHour + 1;
                }
            }
            appointment.setStartTime(timeT);            
            if (!allPossibleAppts.isEmpty()) { allPossibleAppts.get(allPossibleAppts.size() - 1).setEndTime(timeT); }  
            allPossibleAppts.add(appointment);
        }
        allPossibleAppts.get(numberOfAppts - 1).setEndTime(dateToAppend + " " + endOfDay + ":00:00");
        return allPossibleAppts;
    }
    
    //The following function retrieves available appointment times by date for the user currently logged in
    public static Iterable<Appointment> getAvailableApptTimes(LocalDate desiredDate) throws SQLException {
        final int significantDigits = 16;        
        ArrayList<Appointment> availableAppts = (ArrayList<Appointment>) generateAllApptTimes(8,17,desiredDate);        
        int uId = UserCredentials.getCurrentUserId();
        Iterable<Appointment> apptsToRemove = getApptsByDate(desiredDate.toString(), uId);
        for(Appointment appointment : apptsToRemove)
            availableAppts.removeIf(e -> (e.getStartTime().substring(0, significantDigits)
                    .contains(appointment.getStartTime().substring(0, significantDigits))));
        return availableAppts;
    }
    
    //The following function retrieves appointments by date for a specific userId
    public static Iterable<Appointment> getApptsByDate(String desiredDate, int userId) {
        try {
            Iterable<Appointment> appointments = getAppointments();
            List<Appointment> foundAppointments = new ArrayList<>();
            for(Appointment appointment : appointments) {
                if(appointment.getStartTime().contains(desiredDate) && appointment.getUserId().equals(String.valueOf(userId))) {
                    foundAppointments.add(appointment);
                }
            }
            return foundAppointments;
        }
        catch(SQLException sqEx) {  System.out.println("Error " + sqEx.getMessage()); }
        return null;
    }
    
    public static void deleteAppointment(String appointmentId) throws SQLException {
        PreparedStatement stmt = null;
        String sqlStr = "DELETE FROM appointment WHERE appointmentId=?";        
        try {
            stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, appointmentId);
            stmt.executeUpdate();
        }
        catch (SQLException e) { System.out.println("Error " + e.getMessage()); }
    }
    
    //updateAppointment
    //searchAppointments (make 1 by appointment name, 1 by date of appt or maybe allow filter to week/month views)
}