package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Doacoes {
     private StringProperty nome;
     private StringProperty instituicao;
     private StringProperty email;
     private StringProperty telefone; 
     private StringProperty endereco;
     private StringProperty referencia; 
     private StringProperty bairro; 
     private StringProperty conteudo; 
     private StringProperty conhece; 
     private StringProperty complementares;
     private StringProperty contato;
     private StringProperty coleta;

     public Doacoes(String nome,String instituicao,String email, String telefone,String endereco,String referencia,
    		 String bairro, String conteudo,String conhece,String complementares,String contato,String coleta) {
    	 this.nome = new SimpleStringProperty(nome);
    	 this.instituicao = new SimpleStringProperty(instituicao);
    	 this.email = new SimpleStringProperty(email);
    	 this.telefone = new SimpleStringProperty(telefone);
    	 this.endereco = new SimpleStringProperty(endereco);
    	 this.referencia = new SimpleStringProperty(referencia);
    	 this.bairro = new SimpleStringProperty(bairro);
    	 this.conteudo = new SimpleStringProperty(conteudo);
    	 this.conhece = new SimpleStringProperty(conhece);
    	 this.complementares = new SimpleStringProperty(complementares);
    	 this.contato = new SimpleStringProperty(contato);
         this.coleta = new SimpleStringProperty(coleta);
     }
     
     public String getNome() {
    	 return this.nome.getValue();
     }
     
     public String getInstituicao() {
    	 return this.instituicao.getValue();
     }
     
     public String getEmail() {
    	 return this.email.getValue();
     }
     
     public String getTelefone() {
    	 return this.telefone.getValue();
     }
     
     public String getEndereco() {
    	 return this.endereco.getValue();
     }
     
     public String getReferencia() {
    	 return this.referencia.getValue();
     }
     
     public String getBairro() {
    	 return this.bairro.getValue();
     }
     
     public String getConteudo() {
    	 return this.conteudo.getValue();
     }
     
     public String getConhece() {
    	 return this.conhece.getValue();
     }
     
     public String getComplementares() {
    	 return this.complementares.getValue();
     }
     
     public String getContato() {
    	 return this.contato.getValue();
     }
     
     public String getColeta() {
    	 return this.coleta.getValue();
     }
 }
