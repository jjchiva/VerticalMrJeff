package BBDD;

import java.sql.*;
import java.util.ArrayList;

public class Controller {

    private static Controller instance = null;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public ArrayList<Ciudad> ciudadesBBDD() {
        Connection connect = null;
        Statement stmt;
        ResultSet rs;
        ArrayList<Ciudad> listaCiudad = new ArrayList<>();

        try {

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/mrjeff?"
                    + "&user=root");

            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ciudades");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute("SELECT * FROM ciudades ")) {

                rs = stmt.getResultSet();
                while (rs.next()) {
                    Ciudad ci = new Ciudad(rs.getString("nombre"),rs.getInt("distancia_km") , rs.getInt("tiempo_mins"));
                    listaCiudad.add(ci);
                }

            }

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connect != null) {
                connect.close();
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return listaCiudad;

    }

    public ArrayList<Comida> listaProductosBBDD() {


        Connection connect = null;
        Statement stmt;
        ResultSet rs;
        ArrayList<Comida> productos = new ArrayList<Comida>();

        try {

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/mrjeff?"
                    + "&user=root");

            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT * FROM comidas");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute("SELECT * FROM comidas ")) {

                rs = stmt.getResultSet();
                while (rs.next()) {
                    Comida c = new Comida(rs.getString("producto"), rs.getInt("precio"), rs.getInt("unidades"));
                    productos.add(c);
                }

            }

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connect != null) {
                connect.close();
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }

    public void actulizaBBDD(  int id , int unidades){

        Connection connect = null;
        Statement stmt;
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/mrjeff?"
                    + "&user=root");

            preparedStatement = connect
                    .prepareStatement("update comidas set unidades = ? where id = ?");
            preparedStatement.setInt(1, unidades);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect != null) {
                connect.close();
            }

        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
