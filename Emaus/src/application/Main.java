package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.File;

public class Main extends Application {  
	public static Stage scene;
	public static Parent root;
//	public static ObservableList<Doacoes> data = FXCollections.observableArrayList(
////			nome,instituicao,email,telefone,endereco,referencia,bairro,conteudo,conhece,complementares,contato,coleta
//	    new Doacoes("Josemilly Sales", "Equipe Prisma", "timeprisma@gmail.com","(XXX)9XXXX-XXXX","Av. Mister Hull","UFC",
//	    		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018"),
//	    new Doacoes("Iago Gomes", "Equipe Prisma", "timeprisma@gmail.com","(XXX)9XXXX-XXXX","Av. Mister Hull","UFC",
//	    		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018"),
//	    new Doacoes("Francisco Thalys", "Equipe Prisma", "timeprisma@gmail.com","(XXX)9XXXX-XXXX","Av. Mister Hull","UFC",
//	    		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018"),
//	    new Doacoes("Felipe Melquiades", "Equipe Prisma", "timeprisma@gmail.com","(XXX)9XXXX-XXXX","Av. Mister Hull","UFC",
//	    		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018"),
//	    new Doacoes("Erivan Pereira", "Equipe Prisma", "timeprisma@gmail.com","(XXX)9XXXX-XXXX","Av. Mister Hull","UFC",
//	    		"Pici","Lourem Upson","Facebook",":)","05/05/2018","05/05/2018")
//	);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Main.scene = primaryStage;
			Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
//			root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.scene.setTitle("SISTEMA DE ATENDIMENTO EMAÚS");
	        Main.scene.setScene(new Scene(Main.root)); 
	        Main.scene.show();  
	        
            Thread t = new Thread(load);
   		 	t.start();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		String path = new File(".").getCanonicalPath();
		
//		File wamp = new File("C:\\wamp64\\wampmanager.exe");
//		
//		if(wamp.exists()){
//			String line;
//			String pidInfo ="";
//
//			Process p = runtime.exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
//
//			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//			while ((line = input.readLine()) != null) {
//				pidInfo+=line; 
//				System.out.println(line);
//			}
//
//			input.close();
//
//			if(!pidInfo.contains("mysqld.exe"))
//			{
//				runtime.exec("cmd /c C:\\wamp64\\wampmanager.exe");
//			}
//        }
//		else {
//			runtime.exec("cmd /c start" +  path + "\\wampserver3.1.0_x64.exe");
//		}
		
		launch(args);
	}
	
	public void ListaPage() throws Exception{
		Parent Lista = FXMLLoader.load(getClass().getClassLoader().getResource("Lista.fxml"));
		Scene scene = new Scene(Lista);
		Main.scene.setScene(scene);
	}
//	https://www.devmedia.com.br/trabalhando-com-threads-em-java/28780
//	https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html
	private static Runnable load = new Runnable(){
        public void run() {
            try{
            	ProgressBar loading = (ProgressBar) Main.root.lookup("#loading");
        		byte[] ip = InetAddress.getLocalHost().getAddress();
        	    int timeout = 1000;
        	    for(int i=1;i<2;i++){
        	    	ip[3] = (byte)i;
        	    	InetAddress addr = InetAddress.getByAddress(ip);
        	    	if (addr.isReachable(timeout)){
//        	    		System.out.println(host + " is reachable");
        	    		String name = addr.getHostName();
        	    		System.out.println("name: " + name);
        	    	}
        	    	loading.setProgress(1./254 * i);
        	    }
        	    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	Main cnt = new Main();
        				try {
							cnt.ListaPage();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                });
            } catch (Exception e){}
        }
    };
}
