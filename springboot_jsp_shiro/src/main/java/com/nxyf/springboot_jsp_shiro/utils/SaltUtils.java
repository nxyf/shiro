package com.nxyf.springboot_jsp_shiro.utils;

import java.util.Random;

public class SaltUtils {

    public static String getSalt(int n) {
        char[] chars = "ADSFS5657643=-2*00".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}