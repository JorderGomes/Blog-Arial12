/*
 *
 * Mostrar na tela o hash SHA256 do arquivo CSV. Use a API Java para calcular o hash.
 *  
*/

package com.ufc.blog.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

// Sarah
public class HashCsv {
    public static void mostrarHashCsv() {
        try {
            byte[] buffer = new byte[8192];
            int count;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.csv"));
            while ((count = bis.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }
            bis.close();

            byte[] hash = digest.digest();
            System.out.println("O hash do arquivo é o seguinte: " + new BigInteger(1, hash).toString(16).toUpperCase());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
