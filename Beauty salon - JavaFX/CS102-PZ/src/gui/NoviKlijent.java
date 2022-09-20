package gui;

import controller.CrudKlijent;
import entities.Klijent;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;
import messages.MessageAlerts;

/**
 *
 * @author Andrijana Jovanovic
 */
public class NoviKlijent {

    private static Label lblIme = new Label("Ime:");
    private static Label lblPrezime = new Label("Prezime:");
    private static Label lblDodajKlijenta = new Label("DODAJ KLIJENTA");
    private static Label lblIzmeniIme = new Label("Ime:");
    private static Label lblIzmeniPrezime = new Label("Prezime:");
    private static Label lblIzmeniKlijenta = new Label("IZMENI KLIJENTA");
    private static TextField tfIme = new TextField();
    private static TextField tfPrezime = new TextField();
    private static TextField tfIzmeniIme = new TextField();
    private static TextField tfIzmeniPrezime = new TextField();
    private static Button btnDodaj = new Button("Dodaj");
    private static Button btnDelete = new Button("Obrisi");
    private static Button btnIzmeni = new Button("Izmeni");
    private static Button btnMain = new Button("Menu");
    private static VBox vb = new VBox(btnMain, lblDodajKlijenta, lblIme, tfIme, lblPrezime, tfPrezime, btnDodaj, btnDelete);
    private static VBox vb2 = new VBox(lblIzmeniKlijenta, lblIzmeniIme, tfIzmeniIme, lblIzmeniPrezime, tfIzmeniPrezime, btnIzmeni);
    private static Klijent klijent;

    private static List<Klijent> listaKlijenata;
    private static TableView<Klijent> tabelaKlijenata;

    public static Scene getScene() {
        listaKlijenata = controller.CrudKlijent.readAllCriteria();
        tabelaKlijenata = new TableView<>(FXCollections.observableArrayList(listaKlijenata));

        BorderPane bp = new BorderPane();

        //Natpis koji ce da se vidi u text field-u
        tfIme.setPromptText("Unesite ime");
        tfPrezime.setPromptText("Unesite prezime");
        tfIzmeniIme.setPromptText("Unesite ime");
        tfIzmeniPrezime.setPromptText("Unesite prezime");

        //Dodeljivanje ID zbog primene CSS-a
        lblIzmeniKlijenta.setId("lblIzmeni");
        lblDodajKlijenta.setId("lblDodaj");
        lblDodajKlijenta.setAlignment(Pos.CENTER);
        lblIme.setId("lblIme");
        lblPrezime.setId("lblPrezime");
        lblIzmeniIme.setId("lblIme");
        lblIzmeniPrezime.setId("lblPrezime");
        btnDodaj.setId("btnDodaj");
        btnIzmeni.setId("btnIzmeni");
        btnDelete.setId("btnDelete");
        btnMain.setId("btnMain");
        vb2.setId("vb");
        vb.setId("vb");

        vb.setSpacing(10);
        vb.setPadding(new Insets(20));
        vb.setAlignment(Pos.CENTER_LEFT);

        vb2.setSpacing(10);
        vb2.setPadding(new Insets(20));
        vb2.setAlignment(Pos.CENTER_LEFT);
        
        //Dodavanje u BorderPane
        bp.setLeft(vb);
        bp.setRight(vb2);

        //Dugme za vracanje na opcije
        btnMain.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.AdminScene.getAdminView());
        });
        
        //Dugme za dodavanje klijenata
        btnDodaj.setOnAction(e -> {
            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            if (ime.isEmpty() || prezime.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else {
                Klijent k = new Klijent();
                k.setIme(tfIme.getText());
                k.setPrezime(tfPrezime.getText());

                CrudKlijent.create(k);
                gui.NoviTretman.getListaKlijenata().add(k);
                clearFields();

                listaKlijenata = controller.CrudKlijent.readAllCriteria();
                tabelaKlijenata = new TableView<>(FXCollections.observableArrayList(listaKlijenata));
                main.Main.getPrimaryStage().setScene(gui.NoviKlijent.getScene());
            }
        });

        //Dugme za izmenu klijenata
        btnIzmeni.setOnAction(e -> {
            klijent = tabelaKlijenata.getSelectionModel().getSelectedItem();

            gui.NoviTretman.getListaKlijenata().remove(klijent);
            String ime = tfIzmeniIme.getText();
            String prezime = tfIzmeniPrezime.getText();

            if (ime.isEmpty() || prezime.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else {
                klijent.setIme(ime);
                klijent.setPrezime(prezime);

                CrudKlijent.update(klijent);
                clearFields();

                listaKlijenata = controller.CrudKlijent.readAllCriteria();
                tabelaKlijenata = new TableView<>(FXCollections.observableArrayList(listaKlijenata));
                gui.NoviTretman.getListaKlijenata().add(klijent);

                main.Main.getPrimaryStage().setScene(gui.NoviKlijent.getScene());
            }

        });
        
        //Dugme za brisanje klijenata
        btnDelete.setOnAction(e -> {
            klijent = tabelaKlijenata.getSelectionModel().getSelectedItem();

            gui.NoviTretman.getListaKlijenata().remove(klijent);
            controller.CrudKlijent.delete(klijent);

            main.Main.getPrimaryStage().setScene(gui.NoviKlijent.getScene());
        });
        
        //Tabela za prikaz svih klijenata iz baze
        TableColumn<Klijent, String> idCol = new TableColumn<>("ID");
        TableColumn<Klijent, String> imeCol = new TableColumn<>("IME");
        TableColumn<Klijent, String> prezimeCol = new TableColumn<>("PREZIME");

        tabelaKlijenata.getColumns().clear();
        tabelaKlijenata.getColumns().addAll(idCol, imeCol, prezimeCol);

        idCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        imeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIme());
        });

        prezimeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getPrezime());
        });

        bp.setCenter(tabelaKlijenata);

        Scene scene = new Scene(bp, 700, 500);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Dodaj klijenta");
        main.Main.getPrimaryStage().setX(350);
        main.Main.getPrimaryStage().setY(90);
        return scene;
    }

    private static void clearFields() {
        tfIme.setText("");
        tfPrezime.setText("");
        tfIzmeniIme.setText("");
        tfIzmeniPrezime.setText("");
    }
}
