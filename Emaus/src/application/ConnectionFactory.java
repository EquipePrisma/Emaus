package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//conecta ao banco de dados
public class ConnectionFactory{
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "PrismaMenos");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
