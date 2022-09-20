package gui;

import controller.CrudRadnik;
import entities.Radnik;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class NoviRadnik {

    private static Label lblIme = new Label("Ime:");
    private static Label lblPrezime = new Label("Prezime:");
    private static Label lblSpecijalnost = new Label("Specijalnost:");
    private static Label lblDodajRadnika = new Label("DODAJ RADNIKA");
    private static Label lblIzmeniIme = new Label("Ime:");
    private static Label lblIzmeniPrezime = new Label("Prezime:");
    private static Label lblIzmeniSpecijalnost = new Label("Specijalnost:");
    private static Label lblIzmeniRadnika = new Label("IZMENI RADNIKA");
    private static TextField tfIme = new TextField();
    private static TextField tfPrezime = new TextField();
    private static TextField tfSpecijalnost = new TextField();
    private static TextField tfIzmeniIme = new TextField();
    private static TextField tfIzmeniPrezime = new TextField();
    private static TextField tfIzmeniSpecijalnost = new TextField();
    private static Button btnDodaj = new Button("Dodaj");
    private static Button btnIzmeni = new Button("Izmeni");
    private static Button btnDelete = new Button("Obrisi");
    private static Button btnMain = new Button("Menu");
    private static VBox vb = new VBox(btnMain, lblDodajRadnika, lblIme, tfIme, lblPrezime, tfPrezime, lblSpecijalnost, tfSpecijalnost, btnDodaj, btnIzmeni, btnDelete);
    private static VBox vb2 = new VBox(lblIzmeniRadnika, lblIzmeniIme, tfIzmeniIme, lblIzmeniPrezime, tfIzmeniPrezime, lblIzmeniSpecijalnost, tfIzmeniSpecijalnost, btnIzmeni);
    private static Radnik radnik;

    private static List<Radnik> listaRadnika;
    private static TableView<Radnik> tabelaRadnika;

    public static Scene getScene() {
        listaRadnika = controller.CrudRadnik.readAllCriteria();
        tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnika));

        BorderPane bp = new BorderPane();
        
        //Natpis koji ce da se vidi u text field-u
        tfIme.setPromptText("Unesite ime");
        tfPrezime.setPromptText("Unesite prezime");
        tfSpecijalnost.setPromptText("Unesite specijalnost");
        tfIzmeniIme.setPromptText("Unesite ime");
        tfIzmeniPrezime.setPromptText("Unesite prezime");
        tfIzmeniSpecijalnost.setPromptText("Unesite specijalnost");

        //Dodeljivanje ID zbog primene CSS-a
        lblDodajRadnika.setId("lblDodaj");
        lblDodajRadnika.setAlignment(Pos.CENTER);
        lblIzmeniRadnika.setId("lblIzmeni");
        lblIme.setId("lblIme");
        lblPrezime.setId("lblPrezime");
        lblSpecijalnost.setId("lblSpecijalnost");
        lblIzmeniIme.setId("lblIme");
        lblIzmeniPrezime.setId("lblPrezime");
        lblIzmeniSpecijalnost.setId("lblSpecijalnost");
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

        bp.setLeft(vb);
        bp.setRight(vb2);

        btnMain.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.AdminScene.getAdminView());
        });

        //Dugme za dodavanje radnika
        btnDodaj.setOnAction(e -> {
            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            String specijalnost = tfSpecijalnost.getText();
            if (ime.isEmpty() || prezime.isEmpty() || specijalnost.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else {
                Radnik r = new Radnik();
                r.setIme(tfIme.getText());
                r.setPrezime(tfPrezime.getText());
                r.setSpecijalnost(tfSpecijalnost.getText());

                CrudRadnik.create(r);
                gui.NoviTretman.getListaRadnika().add(r);
                clearFields();

                listaRadnika = controller.CrudRadnik.readAllCriteria();
                tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnika));

                main.Main.getPrimaryStage().setScene(gui.NoviRadnik.getScene());
            }
        });

        //Dugme za izmenu radnika
        btnIzmeni.setOnAction(e -> {
            radnik = tabelaRadnika.getSelectionModel().getSelectedItem();

            gui.NoviTretman.getListaRadnika().remove(radnik);

            String ime = tfIzmeniIme.getText();
            String prezime = tfIzmeniPrezime.getText();
            String specijalnost = tfIzmeniSpecijalnost.getText();
            if (ime.isEmpty() || prezime.isEmpty() || specijalnost.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else {
                radnik.setIme(ime);
                radnik.setPrezime(prezime);
                radnik.setSpecijalnost(specijalnost);

                CrudRadnik.update(radnik);
                messages.MessageAlerts.showInfoMessage("Uspesno ste izmenili radnika!");
                clearFields();

                listaRadnika = controller.CrudRadnik.readAllCriteria();
                tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnika));
                gui.NoviTretman.getListaRadnika().add(radnik);

                main.Main.getPrimaryStage().setScene(gui.NoviRadnik.getScene());
            }
        });

        //Dugme za brisanje radnika
        btnDelete.setOnAction(e -> {
            radnik = tabelaRadnika.getSelectionModel().getSelectedItem();

            gui.NoviTretman.getListaRadnika().remove(radnik);
            controller.CrudRadnik.delete(radnik);

            main.Main.getPrimaryStage().setScene(gui.NoviRadnik.getScene());
        });

        //Tabela za prikaz svih radnika iz baze
        TableColumn<Radnik, String> idCol = new TableColumn<>("ID");
        TableColumn<Radnik, String> imeCol = new TableColumn<>("IME");
        TableColumn<Radnik, String> prezimeCol = new TableColumn<>("PREZIME");
        TableColumn<Radnik, String> specijalnostCol = new TableColumn<>("SPECIJALNOST");

        tabelaRadnika.getColumns().clear();
        tabelaRadnika.getColumns().addAll(idCol, imeCol, prezimeCol, specijalnostCol);

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

        bp.setCenter(tabelaRadnika);

        Scene scene = new Scene(bp, 800, 600);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Dodaj radnika");
        main.Main.getPrimaryStage().setX(290);
        main.Main.getPrimaryStage().setY(70);
        return scene;
    }

    private static void clearFields() {
        tfIme.setText("");
        tfPrezime.setText("");
        tfSpecijalnost.setText("");
        tfIzmeniIme.setText("");
        tfIzmeniPrezime.setText("");
        tfIzmeniSpecijalnost.setText("");
    }
}
