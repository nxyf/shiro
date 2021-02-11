package com.nxyf;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @description:
 * @author: myj
 * @create: 2020-12-22 19:57
 * @Version 1.0
 */
public class HashMD5 {

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123", "nxyf", 1024);
        System.out.println("md5Hash.toHex() = " + md5Hash.toHex());

    }
}
