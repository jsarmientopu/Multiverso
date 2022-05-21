package application;

import javafx.scene.control.*;
import javafx.application.Application;
import application.VistaControlador;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		logica.crear();
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/Vista.fxml"));
			Scene scene = new Scene(root,850,578);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Incio Sesión");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
