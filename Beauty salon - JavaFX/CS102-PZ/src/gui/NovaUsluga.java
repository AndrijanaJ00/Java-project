package gui;

import controller.CrudUsluga;
import entities.Usluga;
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
public class NovaUsluga {

    private static Label lblNaziv = new Label("Naziv:");
    private static Label lblCena = new Label("Cena:");
    private static Label lblDodajUslugu = new Label("DODAJ USLUGU");
    private static TextField tfNaziv = new TextField();
    private static TextField tfCena = new TextField();
    private static Button btnDodaj = new Button("Dodaj");
    private static Label lblIzmeniNaziv = new Label("Naziv:");
    private static Label lblIzmeniCenu = new Label("Cena:");
    private static Label lblIzmeniUslugu = new Label("IZMENI USLUGU");
    private static TextField tfIzmeniNaziv = new TextField();
    private static TextField tfIzmeniCena = new TextField();
    private static Button btnIzmeni = new Button("Izmeni");
    private static Button btnMain = new Button("Menu");
    private static Button btnDelete = new Button("Obrisi");
    private static VBox vb = new VBox(btnMain, lblDodajUslugu, lblNaziv, tfNaziv, lblCena, tfCena, btnDodaj, btnDelete);
    private static VBox vb2 = new VBox(lblIzmeniUslugu, lblIzmeniNaziv, tfIzmeniNaziv, lblIzmeniCenu, tfIzmeniCena, btnIzmeni);
    private static Usluga usluga;

    private static List<Usluga> listaUsluga;
    private static TableView<Usluga> tabelaUsluga;

    public static Scene getScene() {

        listaUsluga = controller.CrudUsluga.readAllCriteria();
        tabelaUsluga = new TableView<>(FXCollections.observableArrayList(listaUsluga));

        BorderPane bp = new BorderPane();

        //Natpis koji ce da se vidi u text field-u
        tfNaziv.setPromptText("Unesite naziv");
        tfCena.setPromptText("Unesite cenu");
        tfIzmeniNaziv.setPromptText("Unesite naziv");
        tfIzmeniCena.setPromptText("Unesite cenu");

        //Dodeljivanje ID zbog primene CSS-a
        lblIzmeniUslugu.setId("lblIzmeni");
        lblDodajUslugu.setId("lblDodaj");
        lblDodajUslugu.setAlignment(Pos.CENTER);
        lblNaziv.setId("lblNaziv");
        lblCena.setId("lblCena");
        lblIzmeniNaziv.setId("lblNaziv");
        lblIzmeniCenu.setId("lblCena");
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

        //Dugme za dodavanje usluga
        btnDodaj.setOnAction(e -> {
            String naziv = tfNaziv.getText();
            String cena = tfCena.getText();
            String regex = "\\d+";
            if (naziv.isEmpty() || cena.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else if (!cena.matches(regex)) {
                MessageAlerts.showErrorMessage("Umesto cene uneli ste slova!");
            } else {
                Usluga u = new Usluga();
                u.setNaziv(tfNaziv.getText());
                u.setCena(Integer.parseInt(tfCena.getText()));

                CrudUsluga.create(u);
                gui.NoviTretman.getListaUsluga().add(u);
                clearFields();

                listaUsluga = controller.CrudUsluga.readAllCriteria();
                tabelaUsluga = new TableView<>(FXCollections.observableArrayList(listaUsluga));
                main.Main.getPrimaryStage().setScene(gui.NovaUsluga.getScene());
            }
        });

        //Dugme za izmenu usluga
        btnIzmeni.setOnAction(e -> {

            usluga = tabelaUsluga.getSelectionModel().getSelectedItem();

            gui.NoviTretman.getListaUsluga().remove(usluga);
            String naziv = tfIzmeniNaziv.getText();
            String cena = tfIzmeniCena.getText();
            String regex = "\\d+";
            if (naziv.isEmpty() || cena.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else if (!cena.matches(regex)) {
                MessageAlerts.showErrorMessage("Umesto cene uneli ste slova!");
            } else {
                usluga.setNaziv(naziv);
                usluga.setCena(Integer.parseInt(cena));

                CrudUsluga.update(usluga);
                clearFields();

                listaUsluga = controller.CrudUsluga.readAllCriteria();
                tabelaUsluga = new TableView<>(FXCollections.observableArrayList(listaUsluga));
                gui.NoviTretman.getListaUsluga().add(usluga);

                main.Main.getPrimaryStage().setScene(gui.NovaUsluga.getScene());
            }
        });

        //Dugme za brisanje usluga
        btnDelete.setOnAction(e -> {
            usluga = tabelaUsluga.getSelectionModel().getSelectedItem();

            controller.CrudUsluga.delete(usluga);
            gui.NoviTretman.getListaUsluga().remove(usluga);

            main.Main.getPrimaryStage().setScene(gui.NovaUsluga.getScene());
        });

        //Tabela za prikaz svih usluga iz baze
        TableColumn<Usluga, String> idCol = new TableColumn<>("ID");
        TableColumn<Usluga, String> nazivCol = new TableColumn<>("NAZIV");
        TableColumn<Usluga, String> cenaCol = new TableColumn<>("CENA");

        tabelaUsluga.getColumns().clear();
        tabelaUsluga.getColumns().addAll(idCol, nazivCol, cenaCol);

        idCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        nazivCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getNaziv());
        });

        cenaCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getCena());
        });

        bp.setCenter(tabelaUsluga);

        Scene scene = new Scene(bp, 700, 500);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Dodaj uslugu");
        main.Main.getPrimaryStage().setX(350);
        main.Main.getPrimaryStage().setY(90);
        return scene;

    }

    private static void clearFields() {
        tfNaziv.setText("");
        tfCena.setText("");
        tfIzmeniNaziv.setText("");
        tfIzmeniCena.setText("");
    }
}
