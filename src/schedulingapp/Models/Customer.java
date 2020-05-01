/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp.Models;

import java.sql.Timestamp;

/**
 *
 * @author daisy
 */
public class Customer {
    private String customerName;
    private String address1;
    private String address2;
    private String custCity;
    private String zipCode;
    private String custCountry;
    private String custPhone;
    
    private String custId;
    private String custAddId;
    private String custCityId;
    private String custCountryId;
    private Timestamp lastUpdate;
    private String lastUpdateBy;

    public void setName (String customerName) { this.customerName = customerName; }
    public String getName() { return customerName; }
    public void setAdd1(String address1) { this.address1 = address1; }
    public String getAdd1() { return address1; }
    public void setAdd2(String address2) { this.address2 = address2; }
    public String getAdd2() { return address2; }
    public void setCity(String custCity) { this.custCity = custCity; }
    public String getCity() { return custCity; }
    public void setZip(String zipCode) { this.zipCode = zipCode; }
    public String getZip() { return zipCode; }
    public void setCountry(String custCountry) { this.custCountry = custCountry; }
    public String getCountry() { return custCountry; }    
    public void setPhone(String custPhone) { this.custPhone = custPhone; }
    public String getPhone() { return custPhone; }
    
    public void setCustId(String custId) { this.custId = custId; }
    public String getCustId() { return custId; }
    public void setAddId(String custAddId) { this.custAddId = custAddId; }
    public String getAddId() { return custAddId; }    
    public void setCityId(String custCityId) { this.custCityId = custCityId; }
    public String getCityId() { return custCityId; }
    public void setCountryId(String custCountryId) { this.custCountryId = custCountryId; }
    public String getCountryId() { return custCountryId; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
}
