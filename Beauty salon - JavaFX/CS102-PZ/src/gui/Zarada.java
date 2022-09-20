package gui;

import entities.Radnik;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;

/**
 *
 * @author Andrijana Jovanovic
 */
public class Zarada {

    private static List<Radnik> listaRadnikaZarada;
    private static TableView<Radnik> tabelaRadnika;  
    private static Label lblZarada = new Label("Zarada radnika");
    private static Button btnNazad= new Button("Nazad");

    public static Scene getScene() {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox(lblZarada,btnNazad);
        
        vb.setId("vb");
        vb.setSpacing(10);
        vb.setPadding(new Insets(15));
        vb.setAlignment(Pos.CENTER_LEFT);
        
        btnNazad.setId("btnNazad");
        lblZarada.setId("lblIzmeni");
        
        listaRadnikaZarada = controller.CrudRadnik.readAllCriteria();
        tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnikaZarada));

        //Tabela radnika i njihova zarada
        TableColumn<Radnik, String> idCol = new TableColumn<>("ID");
        TableColumn<Radnik, String> imeCol = new TableColumn<>("IME");
        TableColumn<Radnik, String> prezimeCol = new TableColumn<>("PREZIME");
        TableColumn<Radnik, String> specijalnostCol = new TableColumn<>("SPECIJALNOST");
        TableColumn<Radnik, String> zaradaCol = new TableColumn<>("ZARADA");
        
        tabelaRadnika.getColumns().addAll(idCol, imeCol, prezimeCol, specijalnostCol, zaradaCol);

        idCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        imeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIme());
        });

        prezimeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getPrezime());
        });

        specijalnostCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getSpecijalnost());
        });
        
        zaradaCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getZarada());
        });
        
        btnNazad.setOnAction(e -> {
             main.Main.getPrimaryStage().setScene(gui.AdminScene.getAdminView());
        });
        
        bp.setCenter(tabelaRadnika);
        bp.setLeft(vb);
        
        Scene scene = new Scene(bp, 800, 500);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Zarada radnika");
        main.Main.getPrimaryStage().setX(270);
        main.Main.getPrimaryStage().setY(110);
        return scene;
    }
}
