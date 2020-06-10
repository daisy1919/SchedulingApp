/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.ZoneId;

/**
 *
 * @author daisy
 */
public class TimeZone {
    public static String timeChange() {
        String n = "fix";        
        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("Europe")).forEach(System.out::println);
        return n;
    }
}
