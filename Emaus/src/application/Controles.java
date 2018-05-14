package application;

import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.SQLException;
import java.util.Timer;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
 
import java.io.File;
import java.io.IOException;

public class Controles{
	@FXML Button bnt1;
	@FXML Button bnt2;
	@FXML Pane fileInput;
	@FXML WebView iframe;
	@FXML ProgressBar loading;
	@FXML TableView<Doacoes> lista;
	@FXML TableColumn<Doacoes,String> nome;
	@FXML TableColumn<Doacoes,String> instituicao;
	@FXML TableColumn<Doacoes,String> email;
	@FXML TableColumn<Doacoes,String> telefone;
	@FXML TableColumn<Doacoes,String> endereco;
	@FXML TableColumn<Doacoes,String> referencia;
	@FXML TableColumn<Doacoes,String> bairro;
	@FXML TableColumn<Doacoes,String> conteudo;
	@FXML TableColumn<Doacoes,String> conhece;
	@FXML TableColumn<Doacoes,String> complementares;
	@FXML TableColumn<Doacoes,String> contato;
	@FXML TableColumn<Doacoes,String> coleta;
	
	Timer timer;
    
	//eventos dos botoes
	public void changePage(Event e) throws Exception{
		System.out.println("okay");
		Parent home = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
		Scene scene = new Scene(home); 

		if(e.getSource() == bnt1) {  
			Parent page = FXMLLoader.load(getClass().getClassLoader().getResource("Lista.fxml"));
			scene = new Scene(page);
		}
		if(e.getSource() == bnt2) { 
			Parent page = FXMLLoader.load(getClass().getClassLoader().getResource("Envio.fxml"));
			
			File file = new File("hello_world.pdf");
	        //Initialize PDF writer
	        PdfWriter writer = new PdfWriter("hello_world.pdf");
//	        //Initialize PDF document
	        PdfDocument pdf = new PdfDocument(writer);
//	        // Initialize document
	        Document document = new Document(pdf);
//	        //Add paragraph to the document
	        try {
	        	Paragraph p = new Paragraph(); 
		        p.add("Hello");
		        document.add(p);
	        }
	        catch(Exception a) {
				a.printStackTrace();
	        }
	        //Close document
	        document.close();
////	        
	        PDDocument documento = PDDocument.load(file);
	        PrintService servico = PrintServiceLookup.lookupDefaultPrintService();

	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPageable(new PDFPageable(documento));
	        job.setPrintService(servico);
	        job.print();
	        documento.close();
	        
			scene = new Scene(page);
		}
		
        Main.scene.setScene(scene);
	}

	//codigo --> http://www.java2s.com/Code/Java/JavaFX/DraganddropfiletoScene.htm
	//quando estiver sobre o input do arquivo
//	public void dragFile(DragEvent event){
//		System.out.println("over");
//    }
	
	//recebe o arquivo
	public void uploadFile(DragEvent event) throws SQLException {
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
//                Doacoes doacao = ReadXMLFile.getFile(filePath);
                
//                Sender envio = new Sender();
//                envio.send(doacao);
            }
        }
       // doador = new Doacoes("Alguem Pereira", "Equipe Prisma", "timeprisma@gmail.com","9XXXX-XXXX","Av. Mister Hull","UFC",
	    //		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018"));
        
        event.setDropCompleted(success);
        event.consume();
	}

	public void lis() throws SQLException {
//		https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//		https://stackoverflow.com/questions/40323824/javafx-table-columns-scenebuilder-not-populating
		nome.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("nome"));
		instituicao.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("instituicao"));
		email.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("email"));
		telefone.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("telefone"));
		endereco.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("endereco"));
		referencia.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("referencia"));
		bairro.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("bairro"));
		conteudo.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("conteudo"));
		conhece.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("conhece"));
		complementares.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("complementares"));
		contato.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("contato"));
		coleta.setCellValueFactory(new PropertyValueFactory<Doacoes, String>("coleta"));
		
//		ObservableList<Doacoes> data = new Recebedor().receber();
//		
//		lista.setItems(data);
	}
	@FXML
    public void initialize() throws Exception {
		if(lista != null) lis();
	}

}