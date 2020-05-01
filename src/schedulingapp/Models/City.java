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
public class City {
    private String city;    
    private String cityId;
    private String countryId;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private Country custCountry;
    
    public void setCity(String city) { this.city = city; }
    public String getCity() { return city; }    
    public void setCityId(String cityId) { this.cityId = cityId; }
    public String getCityId() { return cityId; }
    public void setCountryId(String countryId) { this.countryId = countryId; }
    public String getCountryId() { return countryId; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
    public void setCountry(Country custCountry) { this.custCountry = custCountry;}
    public Country getCountry() { return custCountry; }
}
