import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.HashMap;

public class Ejercicio3 { // Sin corregir
    /*
    private static HashMap<String, String> usuarios = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Sistema de gestión de contraseñas");

        System.out.println("1.Registrarse 2.Iniciar sesión 3.Salir");
        System.out.print("Seleccionar una opción: ");
        int opcion = Integer.parseInt(br.readLine());

        String usuarioReg = "";
        String contraReg = "";
        byte[] textoCifrado = new byte[0];

        if (opcion == 1) {
            System.out.println("Nombre de usuario: ");
            usuarioReg = br.readLine();
            System.out.println("Contraseña: ");
            contraReg = br.readLine();

            // Cifrado del texto
            textoCifrado = cifrarTexto(contraReg, claveSecreta);
            System.out.println("La cuenta de " + usuarioReg + " se ha registrado correctamente");
        } else if (opcion == 2) {
            System.out.println("Nombre de usuario: ");
            String usuario = br.readLine();
            System.out.println("Contraseña: ");
            String contra = br.readLine();

            if (usuarioReg.equals(usuario)) {
                if (textoDescifrado.equals(contra)) {
                    System.out.println("Inicio de sesion correcto");
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                System.out.println("Usuario incorrecto");
            }
        }
    }

    public static StringBuilder hashTexto(String texto) throws Exception {
        MessageDigest md =MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(texto.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb;
    }

     */
}
