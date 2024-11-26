import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.*;

public class Ejercicio2 {
    static Logger logger = Logger.getLogger("ejer2");
    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler("ejer2.log", true);
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);
        // para que sea en formato txt en vez de xml
        SimpleFormatter formato = new SimpleFormatter();
        fh.setFormatter(formato);
        // para sacar los mensajes por consola
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.setUseParentHandlers(true); //si es false no muestra nada

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Introduce un usuario valido en formato email: ");
        String usuario = br.readLine();

        if (usuario.matches("[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-z]{2,3}")){
            logger.log(Level.INFO,"\t Se ha registrado el usuario: " + usuario);
            System.out.print("Introduce el nombre del fichero a visualizar: ");
            String fichero = br.readLine(); // máximo de 8 caracteres y tiene una extensión de 3 caracteres

            if (fichero.matches("[a-zA-Z0-9]{1,8}.[a-z]{3}")){
                System.out.println(fichero);
            } else {
                logger.log(Level.SEVERE,"El nombre del fichero no es correcto");
            }
        } else {
            logger.log(Level.SEVERE,"El usuario no es correcto");
        }
    }
}
