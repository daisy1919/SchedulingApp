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
import schedulingapp.Models.Address;
import schedulingapp.Models.City;
import schedulingapp.Models.Country;

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

    //-->deleteCustomer also needs to delete the appointments that the customer has
    public static void deleteCustomer(String custId, String addressId, String cityId, String countryId) {
        PreparedStatement stmt = null;
        String sqlToEx = "DELETE FROM customer WHERE customerId=?";
        
        PreparedStatement stmt2 = null;
        String sql2ToEx = "DELETE FROM address WHERE addressId=?";
        
        PreparedStatement stmt3 = null;
        String sql3ToEx = "DELETE FROM city WHERE cityId=?";
        
        PreparedStatement stmt4 = null;
        String sql4ToEx = "DELETE FROM country WHERE countryId=?";
        
        try {
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
    
    //getAppointments (generate Appointment model)
    //getAppointmentTableData (get data from SQL to list of appointments)
    //generatenewappointmentId
    //addAppointment
    //attachCustomer called from within addAppointment and updatedAppointment
    //updateAppointment
    //deleteAppointment
    //searchAppointments (maybe make 1 by name, 1 by date of appt)
}
