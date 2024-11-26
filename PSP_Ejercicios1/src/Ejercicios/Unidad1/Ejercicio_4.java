package Ejercicios.Unidad1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio_4 {
    /* Ejercicio4.Ejercicio4: Vamos a redireccionar el resultado de la ejecución del comando tree de la
       aplicación Powershell. Queremos que la información se muestre en una ventana creada por
       nosotros para este fin. */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Ejercicio 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        JTextArea areatxt = new JTextArea();
        areatxt.setEditable(true);
        frame.setVisible(true);
        Process p = new ProcessBuilder("powershell","tree").start();

        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try (BufferedReader br2 = new BufferedReader(new InputStreamReader(is))) {
            Stream<String> allLines = br2.lines();
            areatxt.append(allLines.collect(Collectors.joining()));
        }

        // NO FUNCIONA
    }
}
