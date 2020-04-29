/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp.Models;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author daisy
 */
public class User {
    
    private int userId;
    private String userName;
    private String password;
    private int active;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    
    public void setUserId(int userId) { this.userId = userId; };
    public int getUserId() { return userId; }
    public void setUserName(String userName) {  this.userName = userName; }
    public String getUserName() { return userName; }
    public void setPassword(String password) {  this.password = password; }
    public String getPassword() { return password; }
    public void setActive(int active) { this.active = active; };
    public int getActive() { return active; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }
    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getCreatedBy() { return createdBy; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy;}
    public String getLastUpdateBy() { return lastUpdateBy; }
     
}
