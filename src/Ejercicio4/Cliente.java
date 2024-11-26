package Ejercicio4;

import javax.crypto.*;
import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket peticion = new Socket("localhost", 5000);
        ObjectInputStream entrada = new ObjectInputStream(peticion.getInputStream());
        ObjectOutputStream salida = new ObjectOutputStream(peticion.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        KeyGenerator keyGenerator = null;
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey claveSecreta = keyGenerator.generateKey();



        System.out.print("Inserte mensaje a mandar: ");
        String mensaje = br.readLine();
        byte[] mensajeCifrado = cifrarTexto(mensaje, claveSecreta);
        salida.writeObject(mensajeCifrado);
        salida.writeObject(claveSecreta);

        System.out.println("Mensaje y clave enviados");

        entrada.close();
        salida.close();
        peticion.close();
    }

    public static byte[] cifrarTexto (String textoOriginal, SecretKey claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);

        return cipher.doFinal(textoOriginal.getBytes());

        // String mensajecifrado = new String(textoCifrado);
        //return Base64.getEncoder().encodeToString(textoCifrado);
    }
}
