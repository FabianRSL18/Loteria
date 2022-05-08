package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user   = "patron";
    private static String pwd    = "123456";
    private static String bd     = "taqueriadb";

    public static Connection conexion;
    public static void crearConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://"+server+"/taqueriadb?user=root&password=Tecno135790");
            System.out.println("Conexion a la bd establecida....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
