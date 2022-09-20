package gui;

import controller.CrudTretman;
import entities.Klijent;
import entities.Radnik;
import entities.Tretman;
import entities.Usluga;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.Main;
import messages.MessageAlerts;

/**
 *
 * @author Andrijana Jovanovic
 */
public class NoviTretman {

    private static List<Klijent> listaKlijenata = controller.CrudKlijent.readAllCriteria();
    private static TableView<Klijent> tabelaKlijenata;
    private static List<Radnik> listaRadnika = controller.CrudRadnik.readAllCriteria();
    private static TableView<Radnik> tabelaRadnika;
    private static List<Usluga> listaUsluga = controller.CrudUsluga.readAllCriteria();
    private static TableView<Usluga> tabelaUsluga;

    private static Label lblTretman = new Label("DODAJ TRETMAN");
    private static Button btnPocniTretman = new Button("Pocni tretman");
    private static Button btnTretmaniUToku = new Button("Tretmani u toku");
    private static Button btnObavljeniTretmani = new Button("Obavljeni tretmani");
    private static Button btnMain = new Button("Menu");
    private static Label lblKlijenti = new Label("TABELA KLIJENATA");
    private static Label lblRadnici = new Label("TABELA RADNIKA");
    private static Label lblUsluge = new Label("TABELA USLUGA");

    private static Klijent klijent;
    private static Radnik slobodanRadnik;
    private static Usluga usluga;

    private static List<Klijent> klijentiTretman = new ArrayList<>();
    private static List<Radnik> radniciTretman = new ArrayList<>();

    public static Scene getScene() {
        tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnika));
        tabelaKlijenata = new TableView<>(FXCollections.observableArrayList(listaKlijenata));
        tabelaUsluga = new TableView<>(FXCollections.observableArrayList(listaUsluga));

        HBox hb = new HBox();
        HBox hbTop = new HBox();
        VBox vb = new VBox();
        StackPane stackPane = new StackPane();

        BorderPane bp = new BorderPane();
        stackPane.setPrefWidth(1000);

        //Dodeljivanje ID zbog primene CSS-a
        bp.setId("bpTretman");
        hbTop.setId("hbTop");
        btnPocniTretman.setId("btnPocniTretman");
        btnTretmaniUToku.setId("btnTretmanUToku");
        btnObavljeniTretmani.setId("btnTretmanUToku");
        lblTretman.setId("lblIzmeni");
        lblKlijenti.setId("lblIme");
        lblRadnici.setId("lblIme");
        lblUsluge.setId("lblIme");
        btnMain.setId("btnMain");

        btnMain.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.AdminScene.getAdminView());
        });

        vb.setAlignment(Pos.CENTER);
        vb.setId("vbTretmani");
        vb.setSpacing(15);
        vb.setMaxWidth(300);

        hbTop.setSpacing(160);
        hbTop.setAlignment(Pos.TOP_RIGHT);

        //Dugme za prikaz tretmana u toku
        btnTretmaniUToku.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.TretmanUToku.getScene());
        });

        //Dugme za prikaz obavljenih tretmana
        btnObavljeniTretmani.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.ObavljenTretman.getScene());
        });

        hbTop.getChildren().addAll(lblKlijenti, lblRadnici, lblUsluge);
        hb.getChildren().addAll(tabelaKlijenata, tabelaRadnika, tabelaUsluga);
        vb.getChildren().addAll(btnMain, lblTretman, btnPocniTretman, btnTretmaniUToku, btnObavljeniTretmani);
        stackPane.getChildren().add(hb);
        bp.setCenter(stackPane);
        bp.setTop(hbTop);
        bp.setLeft(vb);

        //TABELA KLIJENATA
        TableColumn<Klijent, String> idKlijentCol = new TableColumn<>("ID");
        TableColumn<Klijent, String> imeKlijentCol = new TableColumn<>("IME");
        TableColumn<Klijent, String> prezimeKlijentCol = new TableColumn<>("PREZIME");

        tabelaKlijenata.getColumns().clear();
        tabelaKlijenata.getColumns().addAll(idKlijentCol, imeKlijentCol, prezimeKlijentCol);

        idKlijentCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        imeKlijentCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIme());
        });

        prezimeKlijentCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getPrezime());
        });

        // TABELA RADNINKA
        TableColumn<Radnik, String> idRadnikCol = new TableColumn<>("ID");
        TableColumn<Radnik, String> imeRadnikCol = new TableColumn<>("IME");
        TableColumn<Radnik, String> prezimeRadnikCol = new TableColumn<>("PREZIME");
        TableColumn<Radnik, String> specijalnostRadnikCol = new TableColumn<>("SPECIJALNOST");

        tabelaRadnika.getColumns().clear();
        tabelaRadnika.getColumns().addAll(idRadnikCol, imeRadnikCol, prezimeRadnikCol, specijalnostRadnikCol);

        idRadnikCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        imeRadnikCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getIme());
        });

        prezimeRadnikCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getPrezime());
        });

        specijalnostRadnikCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getSpecijalnost());
        });

        //TABELA USLUGA
        TableColumn<Usluga, String> idUslugeCol = new TableColumn<>("ID");
        TableColumn<Usluga, String> nazivUslugeCol = new TableColumn<>("NAZIV");
        TableColumn<Usluga, String> cenaUslugeCol = new TableColumn<>("CENA");

        tabelaUsluga.getColumns().clear();
        tabelaUsluga.getColumns().addAll(idUslugeCol, nazivUslugeCol, cenaUslugeCol);
        tabelaUsluga.setMinWidth(290);

        idUslugeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getId());
        });

        nazivUslugeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getNaziv());
        });

        cenaUslugeCol.setCellValueFactory(e -> {
            return new SimpleStringProperty("" + e.getValue().getCena());
        });

        //Dugme za pocetak tretmana
        btnPocniTretman.setOnAction(e -> {
            klijent = tabelaKlijenata.getSelectionModel().getSelectedItem();
            slobodanRadnik = tabelaRadnika.getSelectionModel().getSelectedItem();
            usluga = tabelaUsluga.getSelectionModel().getSelectedItem();
            if (klijent == null || slobodanRadnik == null || usluga == null) {
                MessageAlerts.showErrorMessage("Selektujte sve podatke!");
                System.out.println(klijent.toString());
            } else {
                Tretman tretman = new Tretman();

                tretman.setIdKlijenta(klijent);
                listaKlijenata.remove(klijent);
                tabelaKlijenata.getItems().remove(klijent);

                tretman.setIdRadnika(slobodanRadnik);
                listaRadnika.remove(slobodanRadnik);
                tabelaRadnika.getItems().remove(slobodanRadnik);

                tretman.setIdUsluge(usluga);

                tretman.setIdStatusa(controller.CrudStatus.readOne(1));
                CrudTretman.create(tretman);

                tabelaKlijenata = new TableView<>(FXCollections.observableArrayList(listaKlijenata));
                tabelaRadnika = new TableView<>(FXCollections.observableArrayList(listaRadnika));
                main.Main.getPrimaryStage().setScene(gui.NoviTretman.getScene());

            }
        });

        Scene scene = new Scene(bp, 1120, 600);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Dodaj tretman");
        main.Main.getPrimaryStage().setX(100);
        main.Main.getPrimaryStage().setY(60);
        return scene;
    }

    public static void setListaKlijenata(List<Klijent> listaKlijenata) {
        NoviTretman.listaKlijenata = listaKlijenata;
    }

    public static List<Radnik> getListaRadnika() {
        return listaRadnika;
    }

    public static List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

    public static List<Usluga> getListaUsluga() {
        return listaUsluga;
    }

}
