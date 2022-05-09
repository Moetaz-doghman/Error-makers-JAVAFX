/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Badwords {
    private static final ArrayList<String> badwords= new ArrayList<>();

    static {
        badwords.add("shit");
        badwords.add("fuck");
        badwords.add("nigger");
    }

    public static String filter(String input) {
        for(String badword:badwords){
        
            input = input.replaceAll(badword, "****" );
        
        }
        return input;
    }
    
}
