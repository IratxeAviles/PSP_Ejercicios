package Ejercicio8;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class Cliente8 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String host = "localhost";
        int puerto = 6000;
        System.out.println("Programa cliente iniciado..");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\9FDAM02\\UsuarioAlmacenSSL.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "890123");
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = null;
        try {
            cliente = (SSLSocket) sfact.createSocket(host, puerto);

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

            KeyGenerator keyGenerator = null;
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            System.out.println("miua");
            SecretKey claveSecreta = keyGenerator.generateKey();
            System.out.println("miua");




            System.out.print("Inserte mensaje a mandar: ");
            String mensaje = br.readLine();
            byte[] mensajeCifrado = cifrarTexto(mensaje, claveSecreta);
            salida.writeObject(mensajeCifrado);
            salida.writeObject(claveSecreta);

            System.out.println("Mensaje y clave enviados");

            //cerrar
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // es un poco sus, que yo me acerque a tu teclado y que tengas asi el portatil y tal xD con el teclado este seguramente fuera menos cantoso

    public static byte[] cifrarTexto(String textoOriginal, SecretKey claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);

        return cipher.doFinal(textoOriginal.getBytes());

        // String mensajecifrado = new String(textoCifrado);
        //return Base64.getEncoder().encodeToString(textoCifrado);
    }
}
