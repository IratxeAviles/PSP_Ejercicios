package Ejercicio4;

import javax.crypto.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();
        System.out.println("Cliente encontrado");

        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream()); // Primero output, sino da error
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

        byte[] mensaje = (byte[]) entrada.readObject();
        SecretKey claveSecreta = (SecretKey) entrada.readObject();

        System.out.println("El mensaje es: " + descifrarTexto(mensaje,claveSecreta));

    }
    public static String descifrarTexto ( byte[] textoCifrado, SecretKey claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDecifrado = cipher.doFinal(textoCifrado);
        return new String(textoDecifrado);
    }
}