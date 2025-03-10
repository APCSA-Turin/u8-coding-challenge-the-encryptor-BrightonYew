package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        if (messageLen % rows != 0) {
            return messageLen / rows + 1;
        } else {
            return messageLen / rows;
        }
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] arr = new String[rows][determineColumns(message.length(), rows)];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (message.length() > 0) { //if the message is gone due to cutting...
                    String currentChar = message.substring(0, 1);
                    arr[i][j] = currentChar;
                    message = message.substring(1); //each time, cut trhe first character in the message
                } else {
                    arr[i][j] = "="; //... add "=" instead
                }
            }
        }
        return arr;
    }

    public static String encryptMessage(String message, int rows){
        String[][] arr = generateEncryptArray(message, rows);
        String finalstr = "";
        for (int i = arr[0].length - 1; i >= 0; i--) { //traverse the 2d array, assembling the string
            for (int j = 0; j < arr.length; j++) {
                finalstr += arr[j][i];
            }
        }
        return finalstr;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] arr = new String[rows][encryptedMessage.length() / rows];
        String finalstr = "";

        for (int i = arr[0].length - 1; i >= 0; i--) { //reassemble the 2d array
            for (int j = 0; j < arr.length; j++) {
                arr[j][i] = encryptedMessage.substring(0, 1);
                encryptedMessage = encryptedMessage.substring(1);
            }
        }

        for (int i = 0; i < arr.length; i++) { //traverse thje 2d array in the correct order
            for (int j = 0; j < arr[0].length; j++) {
                if (!arr[i][j].equals("=")) {
                    finalstr += arr[i][j];
                }
            }
        }
        return finalstr;
    }
}