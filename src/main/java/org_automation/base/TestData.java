package org_automation.base;

import java.util.Random;

public class TestData {
    /**
     * Initiator Email
     */
    public static String initiatorEmail = "jeff@te.com";

    /**
     * Initiator Password
     */
    public static String initiatorPassword = "123456789";

    /**
     * Item Name
     */

    public static String name = generateRandomStringName(6);

    public static String generateRandomStringName(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            name.append(randomChar);
        }

        return name.toString();
    }


    /**
     * Item Description
     */

    public static String desc = generateRandomStringDesc(10);

    public static String generateRandomStringDesc(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder desc = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            desc.append(randomChar);
        }

        return desc.toString();
    }


    /**
     * Item Number
     */

    public static String num = generateRandomStringNum(7);

    public static String generateRandomStringNum(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder num = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            num.append(randomChar);
        }

        return num.toString();
    }

    /**
     * Item Revision
     */

    public static String rev = generateRandomStringRev(3);

    public static String generateRandomStringRev(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder rev = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            rev.append(randomChar);
        }

        return rev.toString();
    }
}
