package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Sender {
	public void send(Doacoes doador) throws SQLException {
		//codigo e tutorial --> https://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  
		String id = getId(doador);
		System.out.println("ID: "+id);
		if(id == null) { 
			setDoador(doador);
			id = getId(doador);
			setDoacao(doador,id);
		}
		else {
			setDoacao(doador,id);
		}
    }
	
	private String getId(Doacoes doador) throws SQLException {
		String id = null;
		System.out.println("Até aqui foi");
		Connection con = (Connection) new ConnectionFactory().getConnection();
        // cria um preparedStatement
	    String sql = "SELECT * FROM doadores WHERE " +
	    		"nome = '"                 + doador.getNome() + 
	    		"' AND instituicao = '"    + doador.getInstituicao() +
	    		"' AND email = '"          + doador.getEmail() +
	    		"' AND telefone = '"       + doador.getTelefone() +
	    		"' AND endereco = '"       + doador.getEndereco() +
	    		"' AND referencia = '"     + doador.getReferencia() +
	    		"' AND bairro = '"         + doador.getBairro() +
	    		"' AND conteudo = '"       + doador.getConteudo() +
	    		"' AND conhece = '"        + doador.getConhece() +
	    		"' AND complementares = '" + doador.getComplementares() + "'"; 	
	    System.out.println(sql);
	    Statement stmt = (Statement) con.createStatement();
	    ResultSet resultado = stmt.executeQuery(sql);
	    
		while(resultado.next()) {
	    	//pega o valor da coluna nome, de cada linha:
	    	id = resultado.getString("id");
	    	//imprime no console:
	    	System.out.println(id);
	    }
	    
		stmt.close();
        resultado.close();
        
        return id;
	}
	
	private void setDoador(Doacoes doador) throws SQLException {
        // conectando
        Connection con = (Connection) new ConnectionFactory().getConnection();
        
        // cria um preparedStatement
        String sql = "insert into doadores" +
                " (nome,instituicao,email,telefone,endereco,referencia,bairro,conteudo,conhece,complementares)"+ //,contato,coleta)" +
                " values (?,?,?,?,?,?,?,?,?,?)"; //?,?)";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        // preenche os valores
        stmt.setString(1, doador.getNome());
        stmt.setString(2, doador.getInstituicao());
        stmt.setString(3, doador.getEmail());
        stmt.setString(4, doador.getTelefone());
        stmt.setString(5, doador.getEndereco());
        stmt.setString(6, doador.getReferencia());
        stmt.setString(7, doador.getBairro());
        stmt.setString(8, doador.getConteudo());
        stmt.setString(9, doador.getConhece());
        stmt.setString(10, doador.getComplementares());
        
        // executa
        stmt.execute();
        stmt.close();

        System.out.println("Doador Gravado!");
	}
	
	private void setDoacao(Doacoes doador, String id) throws SQLException {
        // conectando
        Connection con = (Connection) new ConnectionFactory().getConnection();
        
        // cria um preparedStatement
        String sql = "insert into doacoes" +
                " (iddoador,contato,coleta)" +
                " values (?,?,?)"; //?,?)";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        // preenche os valores
        stmt.setString(1, id);
        stmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setString(3, doador.getColeta());
        
        // executa
        stmt.execute();
        stmt.close();

        System.out.println("Doacao Gravado!");
	}
}
