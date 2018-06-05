package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//import java.io.InputStreamReader;
//import java.net.InetAddress;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

//import application.Main.ColorRectCell;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;

public class Main extends Application {  
	public static Stage scene;
	public static Parent root;
	ObservableList<String> data = FXCollections.observableArrayList();
	ListView<String> listView = new ListView<String>();
	//sincronizar
	//INSERT INTO `sincronizador` SELECT * FROM `doacoes` UNION SELECT * FROM `doacoes2`
	//DELETE FROM `doacoes` WHERE 1
	//INSERT INTO `doacoes` SELECT * FROM `sincronizador`
	//DELETE FROM `sincronizador`
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Main.scene = primaryStage;
			Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("telas/Main.fxml"));
			Main.scene.setTitle("Sistema Administrativo Emaus");
	        Main.scene.setScene(new Scene(Main.root)); 
	        Main.scene.show();  
	        Main.scene.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	            	Platform.exit();
	            }
	        });       
	        AnchorPane tabela = (AnchorPane) Main.root.lookup("#tabela");
			
			for(int i=0;i<30;i++) {
				data.add("");
			}
	        
	        listView.setItems(data);
	        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        listView.setPrefSize(983, 487);
	        listView.setEditable(true);
	        
	        listView.setCellFactory(new Callback<ListView<String>, 
	                ListCell<String>>() {
	                    @Override 
	                    public ListCell<String> call(ListView<String> listView) {
	                        return new ColorRectCell();
	                    }
	                }
	            );
	        tabela.getChildren().add(listView);
	        
			System.out.println(tabela);
	
//            Thread t = new Thread(load);
//   		 	t.start();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {		
		launch(args);
	}

//	https://www.devmedia.com.br/trabalhando-com-threads-em-java/28780
//	https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html
//	private static Runnable load = new Runnable(){
//        public void run() {
//            try{
//            	ProgressBar loading = (ProgressBar) Main.root.lookup("#loading");
////        		byte[] ip = InetAddress.getLocalHost().getAddress();
////        	    int timeout = 1000;
////        	    for(int i=1;i<2;i++){
////        	    	ip[3] = (byte)i;
////        	    	InetAddress addr = InetAddress.getByAddress(ip);
////        	    	if (addr.isReachable(timeout)){
//////        	    		System.out.println(host + " is reachable");
////        	    		String name = addr.getHostName();
////        	    		System.out.println("name: " + name);
////        	    	}
////        	    	loading.setProgress(1./254 * i);
////        	    	
////        	    	
////        	    }
//                // preenche os valores
//            	
//            	//String path = new File(".").getCanonicalPath();
//        		Runtime runtime = Runtime.getRuntime();
//        		
//        		File wamp = new File("C:\\wamp64\\wampmanager.exe");
//        		File osrm = new File("mapa\\osrm\\osrm-routed.exe");
//            	File fortaleza = new File("mapa\\osrm\\fortaleza\\fortaleza.osrm");
//        		
//        		if(wamp.exists()){
//        			String pidInfo = TaskList.lista(); 
//        			
//        			if(!pidInfo.contains("mysqld.exe")) 
//        				try{
//        					runtime.exec("cmd /c " + wamp.getCanonicalPath());
//        				}
//        			catch (Exception e) {
//        				// TODO Auto-generated catch block
//        	            e.printStackTrace();
//        			}
//                }
//        		
//        		loading.setProgress(0.14);
//        		
//        		try {
//        			String pidInfo = TaskList.lista(); 
//
//        			if(!pidInfo.contains("osrm-routed.exe")) 
//        				try{
//        					runtime.exec("cmd /c " + osrm.getCanonicalPath() + " --algorithm=MLD " + fortaleza.getCanonicalPath());
//        				}
//        			catch (Exception e) {
//        				// TODO Auto-generated catch block
//        	            e.printStackTrace();
//        			}
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//        		
//        		loading.setProgress(0.28);
//
//            	try(Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/", "root", "")){
//                	String db = "create database if not exists mydb";
//                	Statement acao = (Statement) con.createStatement();
//                	acao.executeUpdate(db);
//                	acao.close();
//            	} catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            	
//            	loading.setProgress(0.42);
//            	
//            	try (BufferedReader br = new BufferedReader(new FileReader("C:\\wamp64\\www\\Repositorio\\enderecos\\enderecos.csv"))) {
//                	Connection con = (Connection) new ConnectionFactory().getConnection();
//                	String delete = "drop table if exists enderecos";
//                	String db = "create table if not exists enderecos (" +
//                		"LON varchar(60) NOT NULL," +
//              			"LAT varchar(60) NOT NULL," +
//              			"STREET varchar(60) NOT NULL" +
//              			")";
//                	Statement acao = (Statement) con.createStatement();
//                	acao.executeUpdate(delete);
//                	acao.executeUpdate(db);
//                	acao.close();
//                    
//                	String line;
//                	while ((line = br.readLine()) != null) {
//                		String[] split = line.split(",");
//                		String sql = "insert into enderecos (LON,LAT,STREET) values (?,?,?)";
//                        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
//                        stmt.setString(1, split[0].replaceAll("'", ""));
//                        stmt.setString(2, split[1].replaceAll("'", ""));
//                        stmt.setString(3, split[2].replaceAll("'", ""));
//                        stmt.execute();
//                        stmt.close();
//                	}
//            	} catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            	
//            	loading.setProgress(0.56);
//            	
//            	try (BufferedReader br = new BufferedReader(new FileReader("C:\\wamp64\\www\\Repositorio\\enderecos\\bairros.csv"))) {
//                	Connection con = (Connection) new ConnectionFactory().getConnection();
//                	String delete = "drop table if exists bairros";
//                	String db = "create table if not exists bairros (" +
//                		"NOME varchar(60) NOT NULL" +
//              			")";
//                	Statement acao = (Statement) con.createStatement();
//                	acao.executeUpdate(delete);
//                	acao.executeUpdate(db);
//                	acao.close();
//                    
//                	String line;
//                	while ((line = br.readLine()) != null) {
//                		String[] split = line.split(",");
//                		String sql = "insert into bairros (NOME) values (?)";
//                        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
//                        stmt.setString(1, split[0].replaceAll("'", ""));
//                        stmt.execute();
//                        stmt.close();
//                	}
//            	} catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            	
//            	
//            	loading.setProgress(0.70);
//            	
//            	try (Connection con = (Connection) new ConnectionFactory().getConnection()) {
//                	String db = "create table if not exists doadores (" +
//                		"id int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
//						"nome varchar(60) NOT NULL," +
//						"instituicao varchar(60) NOT NULL," +
//						"email varchar(60) NOT NULL," +
//						"telefone varchar(60) NOT NULL," +
//						"endereco varchar(60) NOT NULL," +
//						"referencia varchar(60) NOT NULL," +
//						"bairro varchar(60) NOT NULL," +
//						"conteudo varchar(60) NOT NULL," +
//						"conhece varchar(60) NOT NULL," +
//						"complementares varchar(60) NOT NULL" +
//              			")";
//                	Statement acao = (Statement) con.createStatement();
//                	acao.executeUpdate(db);
//                	acao.close();
//            	} catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            	
//            	loading.setProgress(0.84);
//            	
//            	try (Connection con = (Connection) new ConnectionFactory().getConnection()) {
//                	String db = "create table if not exists doacoes (" +
//                		"iddoador varchar(60) NOT NULL," +
//              			"contato varchar(60) NOT NULL," +
//              			"coleta varchar(60) NOT NULL" +
//              			")";
//                	Statement acao = (Statement) con.createStatement();
//                	acao.executeUpdate(db);
//                	acao.close();
//            	} catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            	
//            	loading.setProgress(1);
////            	loading.setProgress(1./254 * i);
//            	
//        	    Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//        				try {
//        					Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Lista.fxml"));
//        					Main.root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//        					Main.scene.setScene(new Scene(Main.root));
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                    }
//                });
//            } catch (Exception e){}
//        }
//    };
	
	static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            ColumnConstraints c1 = new ColumnConstraints(); c1.setPercentWidth(30);
            ColumnConstraints c2 = new ColumnConstraints(); c2.setPercentWidth(30);
            ColumnConstraints c3 = new ColumnConstraints(); c3.setPercentWidth(30);
            ColumnConstraints c4 = new ColumnConstraints(); c4.setPercentWidth(10);
            
            GridPane gridpane = new GridPane();
            gridpane.getColumnConstraints().addAll(c1,c2,c3,c4); // each get 50% of width
            
            Label nome = new Label("Franscisco Thalis Darvin");
            Label ligacao = new Label("Data da Ligação: 02/06/2018");
            Label coleta = new Label("Data da Coleta: 02/06/2018");
            Group detalhes = new Group();
            
            Label string = new Label("+ Detalhes");
            string.setOnMouseClicked(new EventHandler<MouseEvent> ()  {  
                @Override  
                public void handle (MouseEvent c) {  
                	new PopUp("telas/popupDetalhes.fxml");
                }  
            });
            
            SVGPath button = new SVGPath();
            button.setContent("M91.71 10 27.29 10 15 20.37 15 33 104 33 104 20.37 91.71 10z");
            button.setOnMouseClicked(new EventHandler<MouseEvent> ()  {  
                @Override  
                public void handle (MouseEvent c) {  
                	new PopUp("telas/popupDetalhes.fxml");
                }  
            });  
            
            detalhes.getChildren().addAll(button,string);
            
        	button.setTranslateY(5);
        	string.setTranslateY(14);
        	string.setTranslateX(20);
            
            if (item != null) {
            	gridpane.add(nome     , 0, 0);
            	gridpane.add(ligacao  , 1, 0);
            	gridpane.add(coleta   , 2, 0);
            	gridpane.add(detalhes , 3, 0);
            	detalhes.setTranslateY(5);
                setGraphic(gridpane);
            }
        }
    }
	
}
