package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Esta clase es la principal donde inicializas tu programa y muestra el menu
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Menu menu = new Menu();

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        VideogamesController videogamesController = new VideogamesController(c);
        PlataformaController plataformaController = new PlataformaController(c);
        GeneroController generoController = new GeneroController(c);
        TablasController tablasController = new TablasController(c);


        int option = menu.mainMenu();
        while (option > 0 && option < 16) {
            switch (option) {
                case 1:
                    videogamesController.showAllGames();
                    break;

                case 2:
                    plataformaController.showPlataforma();
                    break;

                case 3:
                    generoController.showCategoria();
                    break;
                case 4:
                    videogamesController.showVideojuegoPorFecha();
                    break;
                case 5:
                    videogamesController.showVideojuegoPorPlataforma();
                    break;

                case 6:
                    videogamesController.showVideojuegoPorGenero();
                    break;

                case 7:
                    videogamesController.createVideojuego();
                    break;

                case 8:
                    videogamesController.borrarVideogame();
                    break;

                case 9:
                    tablasController.crearTabla();
                    break;
                case 10:
                    tablasController.rellenar();
                    break;
                case 11:
                    videogamesController.borrarTablaVideojuegos();
                    plataformaController.borrarTabla();
                    generoController.borrarTabla();
                    break;
                case 12:
                    connectionFactory.disconnect();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Introduce una de las opciones");
                    break;
            }
            option = menu.mainMenu();
        }
    }
}
