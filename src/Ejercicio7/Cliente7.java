package Ejercicio7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;

public class Cliente7 {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, SignatureException {
        Socket peticion = new Socket("localhost", 5000);
        ObjectInputStream entrada = new ObjectInputStream(peticion.getInputStream());
        ObjectOutputStream salida = new ObjectOutputStream(peticion.getOutputStream());

        PublicKey clavepub = (PublicKey) entrada.readObject();
        String mensaje = (String) entrada.readObject();
        byte[] firma = (byte[]) entrada.readObject();

        //EL RECEPTOR DEL MENSAJE VERIFICA CON LA CLAVE PUBLICA EL MENSAJE FIRMADO AL OBJETO signature sE LE suministralos datos a verificar
        Signature verificadsa = Signature.getInstance("SHA1withRSA");
        verificadsa.initVerify(clavepub);
        verificadsa.update(mensaje.getBytes());
        boolean check = verificadsa.verify(firma);
        if (check) {
            System.out.println("FiRMA VERIFICADA CON CLAVE PÚBLICA.");
        } else {
            System.out.println("FiRMA NO VERIFICADA");
        }

        entrada.close();
        salida.close();
        peticion.close();
    }
}

//