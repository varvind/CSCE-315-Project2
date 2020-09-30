/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.pkg315;

/**
 *
 * @author arvin
 */
public class BusinessAttributes {
    public static boolean takeout = false;
    public static boolean openTwentyFourHours = false;
    public static boolean goodForKids = false;
    public static boolean delivery = false;
    public static void resetFields() {
        takeout = false;
        openTwentyFourHours = false;
        goodForKids = false;
        delivery = false;
    }
}
