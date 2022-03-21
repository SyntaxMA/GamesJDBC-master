package jdbc;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase es la encargada de leer nuestro archivo csv y meterlo en una base de datos.
 */
public class TablasController {
    private Connection connection;
    Scanner sc;

    /**
     * Esto es el constructor de la clase
     *
     * @param connection recibe la conexión hacia postgres.
     */
    public TablasController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para rellenar las tablas de la base de datos con el archivo CSV que tenemos.
     */
    public void rellenar() {
        String[] datos;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/games.csv")));
            String linia;

            while ((linia = br.readLine()) != null) {
                datos = linia.split(";");

                try {

                    String titulo = datos[0];
                    String imagen = datos[1];
                    String fecha = datos[2];
                    String plataforma = datos[3];
                    String descripcion = datos[4];
                    String genero = datos[5];

                    int id_plataforma = 0;

                    switch (plataforma) {
                        case "PC":
                            id_plataforma = 1;
                            break;
                        case "PS5":
                            id_plataforma = 2;
                            break;
                        case "PS4":
                            id_plataforma = 3;
                            break;
                        case "Xbox One":
                            id_plataforma = 4;
                            break;
                        case "SWITCH":
                            id_plataforma = 5;
                            break;
                    }

                    String sql = "SELECT COUNT(id_plataforma) as size FROM plataformas WHERE id_plataforma = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setInt(1, id_plataforma);

                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        int rsSize = rs.getInt("size");

                        // La categoría aún no está insertada
                        if ((rsSize == 0)) {

                            sql = "INSERT INTO plataformas " + "(id_plataforma,plataforma) VALUES (?,?)";


                            pst = connection.prepareStatement(sql);
                            pst.setInt(1, id_plataforma);
                            pst.setString(2, plataforma);

                            pst.executeUpdate();

                            pst.close();
                        }else{
                            System.out.println("Esa plataforma ya esta en la base de datos");
                        }
                    }

                    int id_genero = 0;

                    switch (genero) {
                        case "Aventura de acción":
                            id_genero = 1;
                            break;
                        case "Puzle":
                            id_genero = 2;
                            break;
                        case "Action-RPG":
                            id_genero = 3;
                            break;
                        case "Mundo abierto":
                            id_genero = 4;
                            break;
                        case "RPG Occidental":
                            id_genero = 5;
                            break;
                        case "Acción":
                            id_genero = 6;
                            break;
                        case "JRPG":
                            id_genero = 7;
                            break;
                        case "Lucha":
                            id_genero = 8;
                            break;
                        case "Carreras simulación":
                            id_genero = 9;
                            break;
                        case "Tactical RPG":
                            id_genero = 10;
                            break;
                        case "Aventura narrativa":
                            id_genero = 11;
                            break;
                        case "Plataformas":
                            id_genero = 12;
                            break;
                        case "Granjas":
                            id_genero = 13;
                            break;
                        case "Deportes":
                            id_genero = 14;
                            break;
                        case "Camiones":
                            id_genero = 15;
                            break;
                    }

                    sql = "SELECT COUNT(id_genero) as size FROM generos WHERE id_genero = ?";
                    pst = connection.prepareStatement(sql);
                    pst.setInt(1, id_genero);

                    rs = pst.executeQuery();

                    while (rs.next()) {
                        int rsSize = rs.getInt("size");

                        // La categoría aún no está insertada
                        if ((rsSize == 0)) {

                            sql = "INSERT INTO generos " + "(id_genero,genero) VALUES (?,?)";


                            pst = connection.prepareStatement(sql);
                            pst.setInt(1, id_genero);
                            pst.setString(2, genero);

                            pst.executeUpdate();

                            pst.close();
                        }else{
                            System.out.println("Esa plataforma ya esta en la base de datos");
                        }
                    }

                    sql = "INSERT INTO videojuegos " + "(titulo, imagen, fecha, plataforma, descripcion, genero) VALUES (?,?,?,?,?,?)";

                    pst = connection.prepareStatement(sql);
                    pst.setString(1, titulo);
                    pst.setString(2, imagen);
                    pst.setString(3, fecha);
                    pst.setString(4, plataforma);
                    pst.setString(5, descripcion);
                    pst.setString(6, genero);

                    pst.executeUpdate();
                    pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: No se puede rellenar");
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para crear las tablas.
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE plataformas (" +
                    "id_plataforma smallint NOT NULL UNIQUE," +
                    "plataforma character varying(25) NOT NULL UNIQUE," +
                    "CONSTRAINT pk_plataformas PRIMARY KEY (id_plataforma));");

            st.executeUpdate("CREATE TABLE generos (" +
                    "id_genero smallint NOT NULL UNIQUE," +
                    "genero character varying(25) NOT NULL UNIQUE," +
                    "CONSTRAINT pk_generos PRIMARY KEY (id_genero));");

            st.executeUpdate("CREATE TABLE videojuegos (" +
                    "titulo character varying(1000) NOT NULL UNIQUE," +
                    "imagen character varying(1000)," +
                    "fecha character varying(25)," +
                    "plataforma character varying(25)," +
                    "descripcion character varying(1000)," +
                    "genero character varying(25)," +
                    "CONSTRAINT pk_videojuegos PRIMARY KEY (titulo)," +
                    "CONSTRAINT fk_plataformas FOREIGN KEY (plataforma)" +
                    "    REFERENCES plataformas (plataforma) MATCH SIMPLE" +
                    "    ON UPDATE NO ACTION ON DELETE NO ACTION," +
                    "CONSTRAINT fk_generos FOREIGN KEY (genero)" +
                    "    REFERENCES generos (genero)  MATCH SIMPLE" +
                    "    ON UPDATE NO ACTION ON DELETE NO ACTION);");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: No se pueden crear las tablas");
        }
    }

}
