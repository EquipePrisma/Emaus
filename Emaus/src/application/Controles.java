package application;

//import java.awt.print.PrinterJob;
//import java.io.File;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Timer;
//
//import javax.print.PrintService;
//import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controles{
	@FXML SVGPath svgCompactar;
	@FXML Label labelCompactar;
	
	@FXML SVGPath svgLimpar;
	@FXML Label labelLimpar;

	@FXML SVGPath svgUpload;
	@FXML ImageView imgUpload;
	
	@FXML SVGPath svgDoar;
	@FXML Label labelDoar;
	
	@FXML SplitMenuButton tipoDoacao;
	@FXML Pane inputConteudo;
	@FXML MenuItem tipoValor;
	@FXML MenuItem tipoObjeto;
	@FXML GridPane gridInput;
	
	@FXML DatePicker dataColeta;
	
	@FXML Group autoNome;
	@FXML TextField nome;
	@FXML TextField bairro;
	@FXML TextField rua;
	
	@FXML TextField telefone;
	@FXML TextField celular;
	@FXML TextField referencia;
	@FXML TextField complemento;
	
	@FXML TextField STREET;
	@FXML VBox completador;
	
//	Timer timer;
	static StringProperty valor = new SimpleStringProperty();
    
	//eventos dos botoes
	public void Feedback(Event e) throws Exception{
		System.out.println("okay");
//		Parent home = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
//		Scene scene = new Scene(home); 

		if(e.getSource() == svgCompactar || e.getSource() == labelCompactar)        new PopUp("telas/feedbackconfirmarCompactar.fxml");
		if(e.getSource() == svgLimpar    || e.getSource() == labelLimpar)           new PopUp("telas/feedbackConfirmarExcluir.fxml");
		if(e.getSource() == svgUpload    || e.getSource() == imgUpload)             new PopUp("telas/FeedbackLoadingArchive.fxml");
		if(e.getSource() == svgDoar      || e.getSource() == labelDoar)             new PopUp("telas/FeedbackRegistrou.fxml");

		if(e.getSource() == tipoDoacao) {
			if(inputConteudo.getChildren().get(0)  instanceof TextArea) {
				createValor();
			}
			else if(inputConteudo.getChildren().get(0)  instanceof TextField) {
				createObjeto();
			}
		}
		
		if(e.getSource() == tipoValor) createValor();
		if(e.getSource() == tipoObjeto) createObjeto();
//		if(e.getSource() == bnt2) { 
////			Parent page = FXMLLoader.load(getClass().getClassLoader().getResource("Envio.fxml"));
//			
//			File file = new File("hello_world.pdf");
//	        //Initialize PDF writer
//	        PdfWriter writer = new PdfWriter("hello_world.pdf");
////	        //Initialize PDF document
//	        PdfDocument pdf = new PdfDocument(writer);
////	        // Initialize document
//	        Document document = new Document(pdf);
////	        //Add paragraph to the document
//	        try {
//	        	Paragraph p = new Paragraph(); 
//		        p.add("Hello");
//		        document.add(p);
//	        }
//	        catch(Exception a) {
//				a.printStackTrace();
//	        }
//	        //Close document
//	        document.close();
//////	        
//	        PDDocument documento = PDDocument.load(file);
//	        PrintService servico = PrintServiceLookup.lookupDefaultPrintService();
//
//	        PrinterJob job = PrinterJob.getPrinterJob();
//	        job.setPageable(new PDFPageable(documento));
//	        job.setPrintService(servico);
//	        job.print();
//	        documento.close();
//	        
////	        Scene scene = new Scene(page);
//		}
		
//        Main.scene.setScene(scene);
	}
	
	private void createValor() {
		inputConteudo.getChildren().clear();
		gridInput.getRowConstraints().get(1).setPrefHeight(42);
		
		TextField input = new TextField();
		input.setPromptText("Valor");
		input.setTranslateY(10);
		input.getStyleClass().add("inputForm");
		inputConteudo.getChildren().add(input);
	}
	
	private void createObjeto() {
		inputConteudo.getChildren().clear();
		gridInput.getRowConstraints().get(1).setPrefHeight(120);
		
		TextArea input = new TextArea();
//		input.setText("Objetos");
		input.setPromptText("Objetos");
		input.setPrefSize(573, 102);
		input.setTranslateY(10);
		input.getStyleClass().add("inputForm");
		inputConteudo.getChildren().add(input);
	}
	//codigo --> http://www.java2s.com/Code/Java/JavaFX/DraganddropfiletoScene.htm
	//quando estiver sobre o input do arquivo
//	public void dragFile(DragEvent event){
//		System.out.println("over");
//    }
	
	//recebe o arquivo
	public void uploadFile(DragEvent event) throws SQLException{
		System.out.println("upload");
		Dragboard db = event.getDragboard();
        boolean success = false;
        
        if (db.hasFiles()) {
            success = true;
            String filePath = null;
            for (File file:db.getFiles()) {
                filePath = file.getAbsolutePath();
                System.out.println(filePath);
                
                //lê arquivo de entrada
                Doacoes doacao = ReadXMLFile.getFile(filePath);
                System.out.println(doacao);
                
                Sender envio = new Sender();
                envio.send(doacao);
            }
        }
        
        event.setDropCompleted(success);
        event.consume();
	}

	public void lis() throws SQLException {
//		https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//		https://stackoverflow.com/questions/40323824/javafx-table-columns-scenebuilder-not-populating		
		ObservableList<Doacoes> data = new Recebedor().getDoacoes();
		
//		lista.setItems(data);
	}
	
//	public void searchPerson(Event e) throws SQLException{
//		System.out.println(valor.get());
//		completarEvent(e);
//	}
	
	public void newWindow() {
		new CreateMap().create();
	}
	
	@FXML
    public void initialize() throws Exception {

	} 
	
	public void AutoConplete(Event a) throws SQLException{
            try{
            	String[] info = new String[] {"",""};
        		if(a.getSource() == bairro) info = new String[] {"bairros","NOME"};
        		if(a.getSource() == nome)   info = new String[] {"enderecos","STREET"};
            	
            	Connection con = (Connection) new ConnectionFactory().getConnection();
            	// cria um preparedStatement
            	String sql = "SELECT * FROM " + info[0] + " WHERE " + info[1] + " LIKE " + "'%" + valor.get() + "%'";
//            	System.out.println(sql);
            	
            	Statement stmt = (Statement) con.createStatement();
            	ResultSet resultado = stmt.executeQuery(sql);
            	
//            	if(a.getSource() == bairro) bairro.getParent().setContent(label);
            	autoNome.getChildren().clear();
//            	autoNome.setSpacing(0); 
            	autoNome.getChildren().add(STREET);
            	
            	int cont = 0;
         		while(resultado.next() && cont<5 ) {
         			Label label = new Label(resultado.getString("NOME"));
//         			label.setOnMouseClicked(new EventHandler<MouseEvent>()
//         	        {
//         	            @Override
//         	            public void handle(MouseEvent t) {
//         	                System.out.println("clicked");
//         	                STREET.setText(label.textProperty().getValue());
//         	                completador.getChildren().clear();
//         	               	completador.setSpacing(0); 
//         	            	completador.getChildren().add(STREET);
//         	            }
//         	        });
//         			System.out.println(
         			autoNome.getChildren().add(label);
         	    	cont++;
         	    }
         		
//         		label.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
         	    
         		stmt.close();
         		resultado.close();
            } catch (Exception e){}
      }
}