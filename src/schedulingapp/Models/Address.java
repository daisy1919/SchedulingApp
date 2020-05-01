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
public class Address {
    private String address1;
    private String address2;
    private String postalCode;
    private String phone;     
    private String addressId;
    private String cityId;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private City custCity;

    public void setAddress1(String address1) { this.address1 = address1; }
    public String getAddress1() { return address1; }
    public void setAddress2(String address2) { this.address2 = address2; }
    public String getAddress2() { return address2; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getPostalCode() { return postalCode; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPhone() { return phone; }    
    public void setAddressId(String addressId) { this.addressId = addressId; }
    public String getAddressId() { return addressId; }
    public void setCityId(String cityId) { this.cityId = cityId; }
    public String getCityId() { return cityId; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }    
    public void setCity(City custCity) { this.custCity = custCity; }
    public City getCity() { return custCity; }
}
