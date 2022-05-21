package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import structure.ArrayList;
import structure.Universo;
import structure.multiverso;


public class VistaControlador implements Initializable{
	
	@FXML private Button btnViajar;
	@FXML private Label lblNombre;
	@FXML private ImageView img1;
	@FXML private ImageView img2;
	@FXML private ImageView img3;
	@FXML private ImageView img4;
	private static Universo current;
	private static multiverso multiverso;

	
	public static void setMulti(multiverso m, Universo u) {
		multiverso= m;
		current = u;
	}
	
	
	
	@FXML
	private void viajar(MouseEvent event) {
		
		
		System.out.println(current.getNombre());
		this.current = this.multiverso.viajar(current, 1);
		System.out.println(current.getNombre());
		Image image1 = new Image("/vista/"+current.getPersonas().get(0)+".png");
		Image image2 = new Image("/vista/"+current.getPersonas().get(1)+".png");
		Image image3 = new Image("/vista/"+current.getPersonas().get(2)+".png");
		Image image4 = new Image("/vista/"+current.getPersonas().get(3)+".png");
		img1.setImage(image1);
		img2.setImage(image2);
		img3.setImage(image3);
		img4.setImage(image4);
		
	}
	
	
	
	
	
	
	public void reOpen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Vista.fxml"));
			Parent root = loader.load();
			VistaControlador controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 //TODO
	 }

	

}
