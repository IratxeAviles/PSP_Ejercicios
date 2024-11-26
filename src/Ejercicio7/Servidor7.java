package Ejercicio7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;

public class Servidor7 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Esperando al cliente...");

        Socket cliente = servidor.accept();
        System.out.println("Cliente encontrado");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //SE CREA EL PAR DE CLAVES PRIVADA Y PÚBLICA
        KeyPair par = keyGen.generateKeyPair();
        PrivateKey clavepriv = par.getPrivate();
        PublicKey clavepub = par.getPublic();

        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream()); // Primero output, sino da error
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

        salida.writeObject(clavepub);
        //FIRMA CON CLAVE PRIVADA EL MENSAJE AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A FIRMAR
        Signature dsa = Signature.getInstance("SHA1withRSA");
        dsa.initSign(clavepriv);

        String mensaje = "Este mensaje va a ser firmado";
        dsa.update(mensaje.getBytes());
        byte[] firma = dsa.sign(); //MENSAJE FIRMADO

        salida.writeObject(mensaje);
        salida.writeObject(firma);
    }
}
// 00000000000000000000000000000000000000000000000000000000000000000000