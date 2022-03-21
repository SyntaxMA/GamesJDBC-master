package jdbc;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla Videojuegos, la principal
 */
public class VideogamesController {
    private Connection connection;
    Scanner scanner;
    Menu menu = new Menu();

    String dia;
    String mes;
    String any;

    /**
     * Aqui tenemos el constructor de la clase
     * @param connection recibe la conexión hacia la base de datos postgres
     */
    public VideogamesController(Connection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Este método sirve para crear un videojuego
     */
    public void createVideojuego() {
        try {
            System.out.println("*********************");
            System.out.println("*  Crear Videojuego *");
            System.out.println("*********************");

            System.out.println("Titulo:");
            String titulo = scanner.nextLine();

            System.out.println("Inserta una imagen:");
            String imagen = scanner.nextLine();

            System.out.println("Inserta una fecha (dia/mes/año):");
            String fecha = scanner.nextLine();

            System.out.println("Escribe una de estas plataformas:");
            System.out.println("PS5");
            System.out.println("PS4");
            System.out.println("SWITCH");
            System.out.println("Xbox One");
            System.out.println("PC");
            String plataforma = scanner.nextLine();

            System.out.println("Inserta una descripcion:");
            String descripcion = scanner.nextLine();

            System.out.println("Escribe uno de estos generos:");
            System.out.println("Aventura de acción");
            System.out.println("Puzle");
            System.out.println("Action-RPG");
            System.out.println("Mundo abierto");
            System.out.println("RPG Occidental");
            System.out.println("Acción");
            System.out.println("JRPG");
            System.out.println("Lucha");
            System.out.println("Carreras simulación");
            System.out.println("Tactical RPG");
            System.out.println("Aventura narrativa");
            System.out.println("Plataformas");
            System.out.println("Granjas");
            System.out.println("Deportes");
            System.out.println("Camiones");
            String genero = scanner.nextLine();


            String sql = "INSERT INTO videojuegos" + "(titulo, imagen, fecha, plataforma, descripcion, genero) VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, titulo);
            pst.setString(2, imagen);
            pst.setString(3, fecha);
            pst.setString(4, plataforma);
            pst.setString(5, descripcion);
            pst.setString(6, genero);


            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Este metodo sirve para borrar la tabla videojuegos
     */
    public void borrarTablaVideojuegos() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table videojuegos");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla videojuegos no existe");
        }
    }

    /**
     * Este metodo sirve para mostrar toda la tabla videojuegos
     */
    public void showAllGames(){
        ResultSet rs = null;
        String sql = "SELECT * FROM videojuegos";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Titulo: " + rs.getString("titulo") + "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
                        "Fecha: " + rs.getString("fecha")+ "\n" +
                        "Plataforma: " + rs.getString("plataforma")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion")+ "\n" +
                        "Genero: " + rs.getString("genero"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por su plataforma
     */
    public void showVideojuegoPorFecha(){
        ResultSet rs = null;
        System.out.println("Escribe el dia:");
        dia = scanner.nextLine();
        System.out.println("Escribe el mes:");
        mes = scanner.nextLine();
        System.out.println("Escribe el año:");
        any = scanner.nextLine();
        String sql = "select * from videojuegos where extract(day from fecha)="+ dia + " && extract(month from fecha)="+ mes + " && extract(year from fecha)="+ any +";";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Titulo: " + rs.getString("titulo") + "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
                        "Fecha: " + rs.getString("fecha")+ "\n" +
                        "Plataforma: " + rs.getString("plataforma")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion")+ "\n" +
                        "Genero: " + rs.getString("genero"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por su plataforma
     */
    public void showVideojuegoPorPlataforma(){
        ResultSet rs = null;
        System.out.println("Escribe una de estas plataformas:");
        System.out.println("PS5");
        System.out.println("PS4");
        System.out.println("SWITCH");
        System.out.println("XBOX ONE");
        System.out.println("PC");
        String platform = scanner.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from videojuegos where plataforma='" + platform + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Titulo: " + rs.getString("titulo") + "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
                        "Fecha: " + rs.getString("fecha")+ "\n" +
                        "Plataforma: " + rs.getString("plataforma")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion")+ "\n" +
                        "Genero: " + rs.getString("genero"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por su genero
     */
    public void showVideojuegoPorGenero(){
        ResultSet rs = null;
        System.out.println("Escribe uno de estos generos:");
        System.out.println("Aventura de acción");
        System.out.println("Puzle");
        System.out.println("Action-RPG");
        System.out.println("Mundo abierto");
        System.out.println("RPG Occidental");
        System.out.println("Acción");
        System.out.println("JRPG");
        System.out.println("Lucha");
        System.out.println("Carreras simulación");
        System.out.println("Tactical RPG");
        System.out.println("Aventura narrativa");
        System.out.println("Plataformas");
        System.out.println("Granjas");
        System.out.println("Deportes");
        System.out.println("Camiones");

        String gener = scanner.nextLine();
        String sql = "select * from videojuegos where genero ='" + gener + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Titulo: " + rs.getString("titulo") + "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
                        "Fecha: " + rs.getString("fecha")+ "\n" +
                        "Plataforma: " + rs.getString("plataforma")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion")+ "\n" +
                        "Genero: " + rs.getString("genero"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar un videojuego por su nombre
     */
    public void borrarVideogame(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Que juego quieres eliminar?: ");
            String titulo = scanner.nextLine();
            st.executeUpdate("delete from videojuego where titulo='" + titulo + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}