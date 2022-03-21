package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar lo relacionado con la tabla Genero
 */
public class GeneroController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la conexion hacia postgres
     */
    public GeneroController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para mostrar las plataformas de los generos
     */
    public void showCategoria(){
        System.out.println("\n" + "Genero: ");
        ResultSet rs = null;
        String sql = "SELECT * FROM generos";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("genero"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla generos no existe");
        }
    }

    /**
     * Este metodo sirve para borrar la tabla generos
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table generos");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla generos no existe");
        }
    }
}
