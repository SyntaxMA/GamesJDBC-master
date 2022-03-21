package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar lo relacionado con la tabla Plataforma
 */
public class PlataformaController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la conexion hacia postgres
     */
    public PlataformaController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para mostrar las plataformas de los videojuegos
     */
    public void showPlataforma(){
        System.out.println("\n" + "Plataforma: ");
        ResultSet rs = null;
        String sql = "SELECT * FROM plataformas";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("plataforma"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla plataformas no existe");
        }
    }

    /**
     * Este metodo sirve para borrar la tabla plataformas
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table plataformas");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla plataformas no existe");
        }
    }
}
