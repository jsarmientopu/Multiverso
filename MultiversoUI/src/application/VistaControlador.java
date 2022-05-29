package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import structure.ArrayList;
import structure.Universo;
import structure.multiverso;


public class VistaControlador implements Initializable{
	
	@FXML private Button btnViajar;
	@FXML private Button btnEliminar;
	@FXML private Button btnAgregar;
	@FXML private Label lblNombre;
	@FXML private ImageView img1;
	@FXML private ImageView img2;
	@FXML private ImageView img3;
	@FXML private ImageView img4;
	@FXML private TableView<Universo> tabla;
	@FXML private TableColumn<Universo,String> universo;
	@FXML private TextField txtNew;
	private static Universo current;
	private static multiverso multiverso;

	
	public static void setMulti(multiverso m, Universo u) {
		multiverso= m;
		current = u;
	}
	
	@FXML
	private void viajarA(MouseEvent event) {
		
		Universo destino = tabla.getSelectionModel().getSelectedItem();
		System.out.println(current.getId());
		this.multiverso.viaje(this.current, destino);
		this.current = destino;
		
		lblNombre.setText(current.getNombre());
		if(current.getPersonas()==null) {
			int numero = (int)(Math.random()*7+1);
			int numero1 = (int)(Math.random()*7+1);
			int numero2 = (int)(Math.random()*7+1);
			int numero3 = (int)(Math.random()*7+1);
			Image image1 = new Image("/vista/"+numero+".png");
			Image image2 = new Image("/vista/"+numero1+".png");
			Image image3 = new Image("/vista/"+numero2+".png");
			Image image4 = new Image("/vista/"+numero3+".png");
			img1.setImage(image1);
			img2.setImage(image2);
			img3.setImage(image3);
			img4.setImage(image4);
		}else {
			Image image1 = new Image("/vista/"+current.getPersonas().get(0)+".png");
			Image image2 = new Image("/vista/"+current.getPersonas().get(1)+".png");
			Image image3 = new Image("/vista/"+current.getPersonas().get(2)+".png");
			Image image4 = new Image("/vista/"+current.getPersonas().get(3)+".png");
			img1.setImage(image1);
			img2.setImage(image2);
			img3.setImage(image3);
			img4.setImage(image4);
		}
		
	}
	
	@FXML
	private void eliminar(MouseEvent event) {
		Universo deleted = tabla.getSelectionModel().getSelectedItem();
		this.multiverso.delete(deleted);
		ObservableList<Universo>items=FXCollections.observableArrayList();
		ArrayList<Universo> universos = this.multiverso.universosMulti();
		 for(int i=0; i<universos.size();i++) {
			 items.add(universos.get(i));
		 }
		 tabla.setItems(items);
		 universo.setCellValueFactory(new PropertyValueFactory<Universo,String>("nombre"));
	}
	
	@FXML
	private void agregar(MouseEvent event) {
		Universo conect = tabla.getSelectionModel().getSelectedItem();
		String name = txtNew.getText();
		if(name.length()>=1) {
			Universo newUniverse =this.multiverso.add(name, conect);
			tabla.getItems().add(newUniverse);
		}
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
		 ObservableList<Universo>items=FXCollections.observableArrayList();
		 ArrayList<Universo> universos = this.multiverso.universosMulti();
		 for(int i=0; i<universos.size();i++) {
			 items.add(universos.get(i));
		 }
		 tabla.setItems(items);
		 universo.setCellValueFactory(new PropertyValueFactory<Universo,String>("nombre"));
	 }

	

}
