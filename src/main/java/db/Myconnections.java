package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnections {

    public static Connection connection;

    public static Connection getConnection() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?allowPublicKeyRetrieval=true&useSSL=false","root","krishpatel13579");
        }catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
//        System.out.println("connection ho gaya bhai....");
        return connection;
    }

    public static void closeConnection()
    {
        if(connection != null)
        {
            try {
                connection.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Myconnections con=new Myconnections();
        con.getConnection();
    }
}
