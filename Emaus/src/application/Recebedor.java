package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Recebedor {
	public Doacoes getDoador(String query) throws SQLException {
		Doacoes data = null;
		Connection con = (Connection) new ConnectionFactory().getConnection();
	    
	    // cria um preparedStatement
	    String sql = "SELECT * FROM doadores " + query;
	    
	    Statement stmt = (Statement) con.createStatement();
	    ResultSet resultado = stmt.executeQuery(sql);
	    
//	    nome,instituicao,email,telefone,endereco,referencia,bairro,conteudo,conhece,complementares,contato,coleta
	    if(resultado.next()){
	    	data = new Doacoes(
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
		    	null, 
		    	null
		    );
	    }
	    
        stmt.close();
        resultado.close();
        
		return data;
	}
	
	public ObservableList<Doacoes> getDoacoes() throws SQLException {
		ObservableList<Doacoes> data = FXCollections.observableArrayList();
		Connection con = (Connection) new ConnectionFactory().getConnection();
	    
	    // cria um preparedStatement
	    String sql = "SELECT * FROM doacoes";
	    
	    Statement stmt = (Statement) con.createStatement();
	    ResultSet resultado = stmt.executeQuery(sql);
	    
		while(resultado.next()) {
	    	//imprime no console:
	    	System.out.println("ID do Cliente: " +  resultado.getString("iddoador"));
	    	
	    	Doacoes doacao = getDoador("WHERE id= " +  resultado.getString("iddoador"));
	    	doacao.setContato(resultado.getString("contato"));
	    	doacao.setColeta(resultado.getString("coleta"));

	    	data.add(doacao);
	    }
	    
        stmt.close();
        resultado.close();
        
		return data;
	}
}
