package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;

/**
 *
 * @author Andrijana Jovanovic
 */
public class AdminScene {

    private static Label lblOpcije = new Label("O P C I J E");
    private static Button btnDodajKlijenta = new Button("Dodaj klijenta");
    private static Button btnDodajRadnika = new Button("Dodaj radnika");
    private static Button btnDodajUslugu = new Button("Dodaj uslugu");
    private static Button btnDodajTretman = new Button("Dodaj tretman");
    private static Button btnZarada = new Button("Zarada radnika");
    private static Button btnLogOut = new Button("Log out");
    private static VBox vb = new VBox(lblOpcije, btnDodajTretman, btnDodajKlijenta, btnDodajRadnika, btnDodajUslugu, btnZarada);
    private static HBox hb = new HBox(btnLogOut);

    public static Scene getAdminView() {
        BorderPane bp = new BorderPane();
        hb.setPadding(new Insets(5));
        hb.setAlignment(Pos.TOP_RIGHT);

        //Dodeljivanje ID zbog primene CSS-a
        btnDodajKlijenta.setId("btn");
        btnDodajRadnika.setId("btn");
        btnDodajUslugu.setId("btn");
        btnDodajTretman.setId("btn");
        btnZarada.setId("btn");
        btnLogOut.setId("btnLogOut");
        lblOpcije.setId("lblOpcije");
        bp.setId("bpAdmin");

        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(15);

        //Dodavanje u BorderPane
        bp.setCenter(vb);
        bp.setTop(hb);

        //Dugme za LogOut
        btnLogOut.setOnAction(e -> {
            main.Main.getPrimaryStage().close();
        });

        //Dugmici za prikaz drugih scena
        btnDodajKlijenta.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(null);
            main.Main.getPrimaryStage().setScene(gui.NoviKlijent.getScene());
        });
        btnDodajRadnika.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.NoviRadnik.getScene());
        });
        btnDodajUslugu.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.NovaUsluga.getScene());
        });
        btnDodajTretman.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.NoviTretman.getScene());
        });
        btnZarada.setOnAction(e -> {
            main.Main.getPrimaryStage().setScene(gui.Zarada.getScene());
        });

        Scene scene = new Scene(bp, 400, 500);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        main.Main.getPrimaryStage().setTitle("Admin scene");
        main.Main.getPrimaryStage().setX(500);
        main.Main.getPrimaryStage().setY(110);
        return scene;

    }

}
