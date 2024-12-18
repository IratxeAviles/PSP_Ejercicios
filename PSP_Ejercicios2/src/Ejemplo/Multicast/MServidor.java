package Ejemplo.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MServidor {
    public static void main(String[] args) throws IOException {
        MulticastSocket servidor = new MulticastSocket();
        // El dato que queramos enviar en el mensaje, como array de bytes.
        byte[] dato = new byte[]{1, 2, 3, 4};

        // Usamos la direccion Multicast 230.0.0.1, por poner alguna dentro del rango
        // y el puerto 55557, uno cualquiera que esté libre.
        DatagramPacket dgp = new DatagramPacket(dato, dato.length, InetAddress.getByName("230.0.0.1"), 55557);

        // Envío
        servidor.send(dgp);
    }
}
