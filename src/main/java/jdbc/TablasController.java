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

        String[] rata;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/games.csv")));
            String linia;
            while ((linia = br.readLine()) != null) {
                rata = linia.split("\"");

                System.out.println(linia);
                int id_plataforma = 0;
                int id_genero = 0;

                try {

                    String plataforma = rata[1];

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
                        case "XBOX ONE":
                            id_plataforma = 4;
                            break;
                        case "SWITCH":
                            id_plataforma = 5;
                            break;
                    }

                    String sql = "SELECT COUNT(id_plataforma) as size FROM plataforma WHERE id_plataforma = ?";
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


                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println(rata[1]);

                try {

                    String genero = rata[15];

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

                    String sql = "SELECT COUNT(id_genero) as size FROM genero WHERE id_genero = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setInt(1, id_genero);


                    ResultSet rs = pst.executeQuery();

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


                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println(rata[15]);

                try {

                    String titulo = rata[3];
                    String imagen = rata[5];
                    String fecha = rata[7];
                    String plataforma = rata[9];
                    String descripcion = rata[11];
                    String genero = rata[13];

                    System.out.println(titulo);
                    System.out.println(imagen);
                    System.out.println(fecha);
                    System.out.println(plataforma);
                    System.out.println(descripcion);
                    System.out.println(genero);

                    String sql = "INSERT INTO videojuegos " + "(titulo, imagen, fecha, plataforma, descripcion, genero) VALUES (?,?,?,?,?,?)";

                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, titulo);
                    pst.setString(2, imagen);
                    pst.setDate(3, Date.valueOf(fecha));
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
            e.getStackTrace();
        }
    }

    /**
     * Este metodo sirve para crear las tablas.
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE plataformas\n" +
                    "(\n" +
                    "    plataforma character varying(25) NOT NULL,\n" +
                    "    CONSTRAINT pk_plataformas PRIMARY KEY (plataforma)");

            st.executeUpdate("CREATE TABLE generos\n" +
                    "(\n" +
                    "    genero character varying(25) NOT NULL,\n" +
                    "    CONSTRAINT pk_generos PRIMARY KEY (genero)");

            st.executeUpdate("CREATE TABLE videojuegos\n" +
                    "(\n" +
                    "    titulo character varying(30) NOT NULL UNIQUE ,\n" +
                    "    imagen character varying(1000),\n" +
                    "    fecha date,\n" +
                    "    plataforma character varying(25),\n" +
                    "    descripcion character varying(1000),\n" +
                    "    genero character varying(25),\n" +
                    "    CONSTRAINT pk_videojuegos PRIMARY KEY (titulo),\n" +
                    "    CONSTRAINT fk_plataformas FOREIGN KEY (plataforma)\n" +
                    "        REFERENCES plataformas (plataforma) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                    "    CONSTRAINT fk_generos FOREIGN KEY (genero)\n" +
                    "        REFERENCES generos (genero)  MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION ON DELETE NO ACTION,");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: Las tablas ya existen");
        }
    }

}
