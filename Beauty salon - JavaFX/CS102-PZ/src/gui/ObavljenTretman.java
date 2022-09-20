package gui;

import entities.Tretman;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
public class ObavljenTretman {

    private static Button btnNazad = new Button("NAZAD");
    private static Label lblTretman = new Label("OBAVLJENI TRETMANI");
    private static List<Tretman> listaTretmana = new ArrayList<>();
    private static TableView<Tretman> tabelaTretmanaUToku;
    private static List<Tretman> tretmani = new ArrayList<>();

    public static Scene getScene() {
        VBox vb = new VBox();

        listaTretmana = controller.CrudTretman.readByStatus(controller.CrudStatus.readOne(2));

        tabelaTretmanaUToku = new TableView<>(FXCollections.observableArrayList(listaTretmana));

        //Tabela za prikaz obavljenih tretmana
        TableColumn<Tretman, Integer> idUslugeCol = new TableColumn<>("ID");
        TableColumn<Tretman, String> klijentTretmanCol = new TableColumn<>("KLIJENT");
        TableColumn<Tretman, String> radnikTretmanCol = new TableColumn<>("RADNIK");
        TableColumn<Tretman, String> uslugaTretmanCol = new TableColumn<>("USLUGA");
        TableColumn<Tretman, String> statusTretmanCol = new TableColumn<>("STATUS");

        tabelaTretmanaUToku.getColumns().clear();
        tabelaTretmanaUToku.getColumns().addAll(idUslugeCol, klijentTretmanCol, radnikTretmanCol, uslugaTretmanCol, statusTretmanCol);

        idUslugeCol.setCellValueFactory(e -> {
            return new SimpleObjectProperty<>(e.getValue().getId());
        });

        klijentTretmanCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIdKlijenta());
        });

        radnikTretmanCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIdRadnika());
        });

        uslugaTretmanCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIdUsluge());
        });

        statusTretmanCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIdStatusa());
        });

        BorderPane bp = new BorderPane();
        vb.getChildren().addAll(lblTretman, btnNazad);
        vb.setSpacing(15);
        vb.setId("vbTretman");
        vb.setAlignment(Pos.CENTER);

        //Dodeljivanje ID zbog primene CSS-a
        lblTretman.setId("lblTretman");
        btnNazad.setId("btnNazad");

        bp.setLeft(vb);
        bp.setCenter(tabelaTretmanaUToku);

        btnNazad.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.NoviTretman.getScene());
        });

        Scene scene = new Scene(bp, 1120, 600);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Obavljeni tretmani");
        main.Main.getPrimaryStage().setX(100);
        main.Main.getPrimaryStage().setY(60);
        return scene;
    }
}
