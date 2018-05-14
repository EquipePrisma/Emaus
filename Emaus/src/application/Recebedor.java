package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Recebedor {
	public ObservableList<Doacoes> receber() throws SQLException {
		ObservableList<Doacoes> data = FXCollections.observableArrayList();
		Connection con = (Connection) new ConnectionFactory().getConnection();
	    
	    // cria um preparedStatement
	    String sql = "SELECT * FROM doacoes";
	    
	    Statement stmt = (Statement) con.createStatement();
	    ResultSet resultado = stmt.executeQuery(sql);
	    
		while(resultado.next()) {
	    	//pega o valor da coluna nome, de cada linha:
	    	String nome = resultado.getString("nome");
	    	//imprime no console:
	    	System.out.println("Nome do Cliente: " + nome);
	    	
//	    	nome,instituicao,email,telefone,endereco,referencia,bairro,conteudo,conhece,complementares,contato,coleta
	    	data.add(new Doacoes(
	    		resultado.getString("nome"), 
	    		resultado.getString("instituicao"), 
	    		resultado.getString("email"),
	    		resultado.getString("telefone"), 
	    		resultado.getString("endereco"), 
	    		resultado.getString("referencia"),
	    		resultado.getString("bairro"), 
	    		resultado.getString("conteudo"), 
	    		resultado.getString("conhece"),
	    		resultado.getString("complementares"), 
	    		resultado.getString("contato"), 
	    		resultado.getString("coleta")
	    	));
	    }
	    
        stmt.close();
        resultado.close();
        
		return data;
	}
}
