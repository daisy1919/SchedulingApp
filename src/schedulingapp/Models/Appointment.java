/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp.Models;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 *
 * @author daisy
 */
public class Appointment {
    private String appointmentId;
    private String customerId;
    private Customer customer;
    private String userId;
    private User user;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Timestamp lastUpdate;
    private String lastUpdateBy; 
    
    private Timestamp gmtStartTime;
    private Timestamp gmtEndTime;
    
    private Instant zonedStartTime;
    private Instant zonedEndTime;
    private String sZLocal;
    private String eZLocal;
    
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public String getAppointmentId() { return appointmentId; }    
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getCustomerId() { return customerId; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Customer getCustomer() { return customer; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return userId; }
    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }
    public void setTitle(String title) { this.title = title; };
    public String getTitle() { return title; }    
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }    
    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }    
    public void setContact(String contact) { this.contact = contact; }
    public String getContact() { return contact; }    
    public void setType(String type) { this.type = type; }
    public String getType() { return type; }    
    public void setUrl(String url) { this.url = url; }
    public String getUrl() { return url; }
    public void setStartTime(ZonedDateTime startTime) { this.startTime = startTime; }
    public ZonedDateTime getStartTime() { return startTime; }    
    public void setEndTime(ZonedDateTime endTime) { this.endTime = endTime; }
    public ZonedDateTime getEndTime() { return endTime; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
    public void setZonedStartTime(Instant zonedStartTime) { this.zonedStartTime = zonedStartTime; }
    public Instant getZonedStartTime() { return zonedStartTime; }
    public void setZonedEndTime(Instant zonedEndTime) { this.zonedEndTime = zonedEndTime; }
    public Instant getZonedEndTime() { return zonedEndTime; }
    public void setSZLocal ( String sZLocal ) { this.sZLocal = sZLocal; }
    public String getSZLocal () { return sZLocal; }
    public void setEZLocal ( String eZLocal ) { this.eZLocal = eZLocal; }
    public String getEZLocal () { return eZLocal; }
    
    public void setGMTStartTime(Timestamp gmtStartTime) { this.gmtStartTime = gmtStartTime; }
    public Timestamp getGMTStartTime() { return gmtStartTime; }
    public void setGMTEndTime(Timestamp gmtEndTime) { this.gmtEndTime = gmtEndTime; }
    public Timestamp getGMTEndTime() { return gmtEndTime; }
}
