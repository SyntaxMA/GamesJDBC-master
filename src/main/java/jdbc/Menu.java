package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;
    Scanner sc = new Scanner(System.in);

    /**
     * Este es un constructor y llama a la clase
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar un menu
     * @return devuelte la opcion que elegiste en numero
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("**********************");
            System.out.println("*   MENU PRINCIPAL   *");
            System.out.println("********************** \n");

            System.out.println("1. Mostrar toda la tabla videojuegos");
            System.out.println("2. Mostrar toda la tabla plataformas");
            System.out.println("3. Mostrar toda la tabla generos");
            System.out.println("4. Mostrar videojuegos que salgan el dia X");
            System.out.println("5. Mostrar los videojuegos de una plataforma");
            System.out.println("6. Mostrar los videojuegos de un genero");
            System.out.println("7. Crea un videojuego");
            System.out.println("8. Borrar un videojuego");
            System.out.println("9. Crear Tablas");
            System.out.println("10. Rellenar Tablas");
            System.out.println("11. Borrar Tablas");
            System.out.println("12. Salir \n");
            System.out.println("Escoge una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlido");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10 && option != 11 && option != 12);
        return option;
    }

    /**
     * Este metodo sirve para autenticar
     * @return devuelve unos datos de tipo Identity
     * @throws IOException es un tipo de excepciones
     */
    public Identity authenticate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("************************************************************");
        System.out.println("*                  Videogames-Magazines                    *");
        System.out.println("************************************************************");
        System.out.println("Inserta nom del usuari: ");
        String user = br.readLine();
        System.out.println("Inserta contrasenya: ");
        String password = br.readLine();

        Identity identity = new Identity(user, password);
        return identity;
    }

    public int elegirOpcion(String[] opciones) {
        boolean seguirPidiendo = true;
        int opcion = 0;

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while(seguirPidiendo) {
            System.out.println("\nOpcion:");
            opcion = sc.nextInt();

            try {
                if (opcion > opciones.length) {
                    System.out.println("Esa opción no existe usa un numero");
                } else {
                    seguirPidiendo = false;
                }
            } catch (Exception e) {
                System.out.println("¡Introduzca una opcion usa un numero!");
                seguirPidiendo = true;
            }
        }
        return opcion;
    }
}