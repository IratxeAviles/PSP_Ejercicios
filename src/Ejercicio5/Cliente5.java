package Ejercicio5;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;

public class Cliente5 {
    public static void main(String[] args) throws Exception {
        Socket peticion = new Socket("localhost", 5000);
        ObjectInputStream entrada = new ObjectInputStream(peticion.getInputStream());
        ObjectOutputStream salida = new ObjectOutputStream(peticion.getOutputStream());
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));

        // Generación del par de claves (miclavepública y miclaveprivada)
        KeyPair parDeClaves = generarParClaves();
        PublicKey miclavePublica = parDeClaves.getPublic(); // Clave que envio para que cifren sus mensajes con el
        PrivateKey miclavePrivada = parDeClaves.getPrivate(); // Calve que uso para descifrar los mensajes cifrados con mi calve publica

        PublicKey clavePublica = (PublicKey) entrada.readObject();
        salida.writeObject(miclavePublica);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserte mensaje a mandar: ");
        String mensaje = br.readLine();
        byte[] mensajeCifrado = cifrarConClavePublica(mensaje, clavePublica);
        salida.writeObject(mensajeCifrado);

        System.out.println("Mensaje y clave enviados");

        byte[] respuesta = (byte[]) entrada.readObject();

        System.out.println("La respuesta es: " + descifrarConClavePrivada(respuesta, miclavePrivada));

        entrada.close();
        salida.close();
        peticion.close();
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
