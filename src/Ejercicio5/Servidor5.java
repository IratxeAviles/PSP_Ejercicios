package Ejercicio5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Servidor5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket servidor = new ServerSocket(5000);

        System.out.println("Esperando al cliente...");

        KeyPair parDeClaves = generarParClaves();
        PrivateKey miclavePrivada = parDeClaves.getPrivate();
        PublicKey miclavePublica = parDeClaves.getPublic();

        Socket cliente = servidor.accept();
        System.out.println("Cliente encontrado");

        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream()); // Primero output, sino da error
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

        salida.writeObject(miclavePublica);
        PublicKey clavePublica = (PublicKey) entrada.readObject();

        byte[] mensaje = (byte[]) entrada.readObject();

        System.out.println("El mensaje es: " + descifrarConClavePrivada(mensaje, miclavePrivada));


        System.out.print("Inserte respuesta a mandar: ");
        String respuesta = br.readLine();
        byte[] respuestaCifrada = cifrarConClavePublica(respuesta, clavePublica);
        salida.writeObject(respuestaCifrada);

        System.out.println("Mensaje y clave enviados");
    }

    public static KeyPair generarParClaves() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Tamaño de la clave de 2048 bits
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] cifrarConClavePublica(String mensaje, PublicKey miclavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, miclavePublica);
        return cipher.doFinal(mensaje.getBytes()); // Devuelve los bytes cifrados
    }

    public static String descifrarConClavePrivada(byte[] mensajeCifrado, PrivateKey miclavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, miclavePrivada);
        byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado); // Devuelve los bytes descifrados
        return new String(mensajeDescifrado); // Convertimos los bytes de vuelta a una cadena
    }
}
