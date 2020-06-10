/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp.resources;

import java.time.ZoneId;

/**
 *
 * @author daisy
 */
public class TimeConverter {
    public static String timeConverter() {
        String n = "fix";
        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
        return n;
    }
    
}
