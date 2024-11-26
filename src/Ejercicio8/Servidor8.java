package Ejercicio8;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class Servidor8 {
    public static void main(String[] args) throws Exception {
        int puerto = 6000;
        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\9FDAM02\\AlmacenSSL.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL = null;
        try {
            servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SSLSocket clienteConectado = null;

        for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al cliente" + i);
//            try {
//                clienteConectado = (SSLSocket) servidorSSL.accept();
//                flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
//                System.out.println("Recibien del cliente" + i + "\n\t" + flujoEntrada.readUTF());
//                flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            clienteConectado = (SSLSocket) servidorSSL.accept();
            try {
                ObjectOutputStream salida = new ObjectOutputStream(clienteConectado.getOutputStream()); // Primero output, sino da error
                ObjectInputStream entrada = new ObjectInputStream(clienteConectado.getInputStream());

                byte[] mensaje = (byte[]) entrada.readObject();
                SecretKey claveSecreta = (SecretKey) entrada.readObject();

                System.out.println("El mensaje es: " + descifrarTexto(mensaje, claveSecreta));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        clienteConectado.close();
        servidorSSL.close();

    }

    public static String descifrarTexto(byte[] textoCifrado, SecretKey claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDecifrado = cipher.doFinal(textoCifrado);
        return new String(textoDecifrado);
    }
}
