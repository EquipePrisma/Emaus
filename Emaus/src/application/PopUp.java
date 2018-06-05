package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUp {
	PopUp(String name){
		try {
			Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(name));
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UTILITY);
//			stage.setTitle("Web View");
			Scene popup = new Scene(page);
			stage.setScene(popup);     
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
