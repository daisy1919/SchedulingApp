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
public class Country {
    private String country;    
    private String countryId;
    private Timestamp lastUpdate;
    private String lastUpdateBy;

    public void setCountry(String country) { this.country = country; }
    public String getCountry() { return country; }    
    public void setCountryId(String countryId) { this.countryId = countryId; }
    public String getCountryId() { return countryId; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
}
