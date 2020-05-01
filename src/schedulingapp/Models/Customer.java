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
    private String custId;
    private String custAddId;    
    private Address custAddress;
    private Timestamp lastUpdate;
    private String lastUpdateBy;

    public void setName (String customerName) { this.customerName = customerName; }
    public String getName() { return customerName; }   
    public void setCustId(String custId) { this.custId = custId; }
    public String getCustId() { return custId; }
    public void setAddId(String custAddId) { this.custAddId = custAddId; }
    public String getAddId() { return custAddId; }  
    public void setAddress(Address custAddress) { this.custAddress = custAddress; }
    public Address getAddress() { return custAddress;}
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
}
