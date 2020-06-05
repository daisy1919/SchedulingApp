/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp.Models;

/**
 *
 * @author daisy
 */
public class AppointmentType {
    private String type;
    private String typeOccurrence;
    
    public void setType(String type) { this.type = type; }
    public String getType() { return type; }
    public void setTypeOccurrence(int tOccurrence) { typeOccurrence = String.valueOf(tOccurrence); }
    public String getTypeOccurrence() { return typeOccurrence; }
}
