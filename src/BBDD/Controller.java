package BBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    Connection connect = null;
    Statement stmt;
    ResultSet rs;
    PreparedStatement preparedStatement;

    private static Controller instance = null;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Ciudad> ciudadesBBDD() {
        this.conectar();

        List<Ciudad> listaCiudades = new ArrayList<>();
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ciudades");

            if (stmt.execute("SELECT * FROM ciudades ")) {

                rs = stmt.getResultSet();
                while (rs.next()) {
                    Ciudad ci = new Ciudad(rs.getString("nombre"),rs.getInt("distancia_km") , rs.getInt("tiempo_mins"));
                    listaCiudades.add(ci);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.cerrarConexion();
        return listaCiudades;

    }

    public List<Comida> listaProductosBBDD() {
        this.conectar();
        List<Comida> listaProductos = new ArrayList<>();
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM comidas");

            if (stmt.execute("SELECT * FROM comidas ")) {

                rs = stmt.getResultSet();
                while (rs.next()) {
                    Comida c = new Comida(rs.getString("producto"), rs.getInt("precio"), rs.getInt("unidades"));
                    listaProductos.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.cerrarConexion();
        return listaProductos;
    }

    public void actulizaBBDD(int id , int unidades) {
        this.conectar();
        try {
            preparedStatement = connect
                    .prepareStatement("update comidas set unidades = ? where id = ?");
            preparedStatement.setInt(1, unidades);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            if (preparedStatement != null) {
                preparedStatement.close();
            }


        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        this.cerrarConexion();
    }

    public void conectar() {
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/mrjeff?"
                    + "&user=root&password=root");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error SQL");
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
    }

}
