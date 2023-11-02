/*
 *
 * Mostrar na tela o hash SHA256 do arquivo CSV. Use a API Java para calcular o hash.
 *  
*/

package com.ufc.blog.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

// Sarah
public class HashCsv {
    public static void mostrarHashCsv() {
        try {
            File arquivo = new File("data.csv");
            FileInputStream fis = new FileInputStream(arquivo);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                digest.update(buffer, 0, len);
            }
            fis.close();

            byte[] hash = digest.digest();
            System.out.println("O hash do arquivo é o seguinte: " + new BigInteger(1, hash).toString(16).toUpperCase());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
