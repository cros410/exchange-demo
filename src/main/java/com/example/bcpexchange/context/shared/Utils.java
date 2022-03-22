package com.example.bcpexchange.context.shared;

public class Utils {
    public static Double round(Double number, int digits) {
        return  Math.round(number*Math.pow(10,digits))/Math.pow(10,digits);
    }
}
