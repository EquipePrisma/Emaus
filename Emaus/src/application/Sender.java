package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

public class Sender {
	public void send(Doacoes doador) throws SQLException {
		//codigo e tutorial --> https://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/
        
        //baixar arquivo zip --> https://dev.mysql.com/downloads/connector/j/5.1.html
        //colocar pasta interna do arquivo zip em C:\Program Files\Java
        
        //necessario criar um banco de dados com o nome contatos, 
        //abra o wamp, aparecerá um simbolo verde, vá em http://localhost/phpmyadmin/
        //pedirá um usuário, o usuario é "root" e não tem senha 
        //quando abrir vá na aba "Contas de usuario"
        //click em "Edit privileges" na linha do usuário "root" 
        //click na aba "Alterar a palavra-passe"
        //coloque a senha como "PrismaMenos" e click em execute
        //agora click em "New" no lado esquerdo 
        //coloque o nome do banco de dados "mydb" e click em criar
        //depois de criado, click em "mydb" no lado esquerdo e click em "New" para criar uma tabela
        //coloque o nome da lista "contatos" e preencha com 
        //nome:nome tipo:varchar tamanho:30 
        //nome:endereco tipo:varchar tamanho:30 
        //nome:dataNascimento tipo:varchar tamanho:30 
        //click em guardar 
       	         
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
