package org.example;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Encryption {
    Scanner scanner = new Scanner(System.in);
    private final String word = scanner.nextLine();


    public void encryptor() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {


        Cipher cipher = Cipher.getInstance("DESede");

        KeyGenerator kgen = KeyGenerator.getInstance("DESede");
        kgen.init(168);
        SecretKey key = kgen.generateKey();

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] bytes = cipher.doFinal(word.getBytes(StandardCharsets.UTF_8));
        for (byte b : bytes) {
            System.out.print(b);
            String string = new String(bytes, StandardCharsets.UTF_8);
            //   System.out.print(str);

            BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\текстовые файлы\\cipher.txt", true));
            File file = new File("E:\\текстовые файлы\\cipher.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();

            //writer.append(' ');

            //writer.write(b);

            //writer.close();
        }
        Cipher decryptCipher = Cipher.getInstance("DESede");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

        System.out.print(" ");

        BufferedReader reader = new BufferedReader(new FileReader("E:\\текстовые файлы\\cipher.txt"));
        String currentLine = reader.readLine();

        byte[] decryptedBytes = decryptCipher.doFinal(bytes);
        for (byte b : decryptedBytes) {


            System.out.print((char) b);

            System.out.print(currentLine);
            reader.close();
        }
    }
}