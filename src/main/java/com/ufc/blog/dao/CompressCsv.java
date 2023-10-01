
/*
 * 
 *
 * Compactar o arquivo CSV e gerar um novo arquivo de mesmo nome, mas com a extensão ZIP. Deve-se usar a API Java para realizar a compressão.
*/
package com.ufc.blog.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// Sarah
public class CompressCsv {
    /**
     * 
     * Esse método comprime um arquivo
     * 
     * @param arquivo   é o arquivo .csv com os dados de usuário
     * @param nomeDoZip é o nome do arquivo comprimido .zip que será criado
     */
    public static void ziparArquivoCsvDeUsuarios() {
        try {
            File arquivo = new File("data.csv");
            String nomeDoZip = "data.zip";
            FileOutputStream fos = new FileOutputStream(nomeDoZip);
            ZipOutputStream zos = new ZipOutputStream(fos);

            ZipEntry ze = new ZipEntry(arquivo.getName());
            zos.putNextEntry(ze);
            FileInputStream fis = new FileInputStream(arquivo);
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            zos.close();
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
