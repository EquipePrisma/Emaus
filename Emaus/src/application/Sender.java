package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

public class Sender {
	public void send(Doacoes doador) throws SQLException {
        // conectando
        Connection con = (Connection) new ConnectionFactory().getConnection();
        
        // cria um preparedStatement
        String sql = "insert into doacoes" +
                " (nome,instituicao,email,telefone,endereco,referencia,bairro,conteudo,conhece,complementares,contato,coleta)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";
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
        stmt.setString(11, doador.getContato()); //new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setString(12, doador.getColeta());
        
        // executa
        stmt.execute();
        stmt.close();

        System.out.println("Gravado!");
	}
}
