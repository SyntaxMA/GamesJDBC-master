package jdbc;

/**
 * Esta clase sirve para hacer una verificacion de usuario y proteger un poco mas el acceso.
 */
public class Identity {
    private String user;
    private String password;

    /**
     * Este es el constructor de la clase
     * @param user recibe el nombre del usuario
     * @param password recibe la contraseña del usuario
     */

    public Identity(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    /**
     * Este metodo sirve para coger el nombre
     * @return devuelve el nombre
     */
    public String getUser() {
        return user;
    }

    /**
     * Este metodo sirve para asignar el nombre
     * @param user recibe el nombre que le vas a poner
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Este metodo sirve para coger la contraseña
     * @return devuelve la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Este metodo sirve para asignar la contraseña
     * @param password recibe la contraseña que le vas a poner
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Este metodo sirve para modificar el formato de imprimir
     * @return devuelve el formato
     */
    @Override
    public String toString() {
        return "Identity [user=" + user + ", password=" + password + "]";
    }
}
