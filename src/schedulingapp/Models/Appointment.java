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
public class Appointment {
    private String appointmentId;
    private String customerId;
    private String userId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private String startTime;
    private String endTime;
    private Timestamp lastUpdate;
    private String lastUpdateBy;    
    //createDate, createdBy can just be input in the DBConnection functions similar to customer information
    
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public String getAppointmentId() { return appointmentId; }    
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getCustomerId() { return customerId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return userId; }    
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
    //start and end times need to be converted to the proper format in the DBConnection functions; see customer functions for reference
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getStartTime() { return startTime; }    
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public String getEndTime() { return endTime; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
    public String getLastUpdateBy() { return lastUpdateBy; }
}
