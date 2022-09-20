package gui;

import controller.CrudTretman;
import entities.Radnik;
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
import messages.MessageAlerts;

/**
 *
 * @author Andrijana Jovanovic
 */
public class TretmanUToku {

    private static Button btnNazad = new Button("NAZAD");
    private static Button btnZavrsiTretman = new Button("ZAVRSI TRETMAN");
    private static Label lblTretman = new Label("TRETMANI U TOKU");
    private static List<Tretman> listaTretmana = new ArrayList<>();
    private static TableView<Tretman> tabelaTretmanaUToku;
    private static Tretman tretman;

    public static Scene getScene() {
        VBox vb = new VBox();

        listaTretmana = controller.CrudTretman.readByStatus(controller.CrudStatus.readOne(1));

        tabelaTretmanaUToku = new TableView<>(FXCollections.observableArrayList(listaTretmana));

        //Tabela tretmana u toku
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
        vb.getChildren().addAll(lblTretman, btnNazad, btnZavrsiTretman);
        vb.setId("vbTretman");
        vb.setSpacing(15);
        vb.setAlignment(Pos.CENTER);

        //Dodeljivanje ID zbog primene CSS-a
        lblTretman.setId("lblTretman");
        btnNazad.setId("btnNazad");
        btnZavrsiTretman.setId("btnNazad");

        bp.setLeft(vb);
        bp.setCenter(tabelaTretmanaUToku);

        btnNazad.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.NoviTretman.getScene());
        });

        //Dugme za zavrsavanje tretmana
        btnZavrsiTretman.setOnAction(e -> {
            tretman = tabelaTretmanaUToku.getSelectionModel().getSelectedItem();

            if (tretman == null) {
                MessageAlerts.showErrorMessage("Selektujte tretman!");
            } else {
                Radnik radnik = controller.CrudRadnik.readById(tretman.getIdRadnika().getId());
                radnik.setZarada(radnik.getZarada() + radnik.racunajZaradu(tretman.getIdUsluge()));
                controller.CrudRadnik.update(radnik);
                gui.NoviTretman.getListaRadnika().add(tretman.getIdRadnika());

                tretman.setIdStatusa(controller.CrudStatus.readOne(2));
                CrudTretman.update(tretman);
                listaTretmana.remove(tretman);
                tabelaTretmanaUToku.getItems().remove(tretman);

                tabelaTretmanaUToku = new TableView<>(FXCollections.observableArrayList(listaTretmana));
            }
            main.Main.getPrimaryStage().setScene(gui.TretmanUToku.getScene());
        });

        Scene scene = new Scene(bp, 1120, 600);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Tretmani u toku");
        main.Main.getPrimaryStage().setX(100);
        main.Main.getPrimaryStage().setY(60);
        return scene;
    }

}
