package main;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import messages.MessageAlerts;

/**
 *
 * @author Andrijana Jovanovic
 */
public class Main extends Application {

    private TextField usernameTxt = new TextField();
    private PasswordField passwordTxt = new PasswordField();
    private Button loginBtn = new Button("Login");
    private Label lblLogin = new Label("Login");
    private Label lblUsername = new Label("Korisnicko ime:");
    private Label lblPassword = new Label("Sifra:");

    private static Stage primaryStage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        
        //Ucitavanje usluga sa Web-a
        /*
            web.Web.getUsluge();
         */

        this.primaryStage = primaryStage;
        File imageFile = new File("img/ANN(dark).png");
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setId("image");
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);

        usernameTxt.setPromptText("Unesite korisnicko ime");
        usernameTxt.setId("usernameTxt");
        passwordTxt.setPromptText("Unesite sifru");
        passwordTxt.setId("passwordTxt");
        lblUsername.setId("lblUsername");
        lblPassword.setId("lblPassword");

        VBox vbox1 = new VBox(lblLogin, lblUsername, usernameTxt, lblPassword, passwordTxt, loginBtn);
        lblLogin.setId("lblLogin");
        vbox1.setId("vbox");
        vbox1.setSpacing(10);
        vbox1.setPadding(new Insets(20));
        vbox1.setAlignment(Pos.CENTER_LEFT);

        VBox vbox2 = new VBox(imageView);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(20));

        BorderPane bp = new BorderPane();
        bp.setRight(vbox1);
        bp.setLeft(vbox2);
        bp.setId("bp");

        loginBtn.setId("loginBtn");
        loginBtn.setMaxWidth(200);
        loginBtn.setMaxHeight(30);

        scene = new Scene(bp, 400, 400);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                String username = usernameTxt.getText();
                String password = passwordTxt.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    MessageAlerts.showErrorMessage("Unesite sve podatke!");
                } else if (username.equals("admin") && password.equals("admin")) {
                    primaryStage.setScene(gui.AdminScene.getAdminView());
                } else {
                    MessageAlerts.showErrorMessage("Korisnicki podaci nisu validni!");
                }
            }
        });

        loginBtn.setOnAction(e -> {
            String username = usernameTxt.getText();
            String password = passwordTxt.getText();
            if (username.isEmpty() || password.isEmpty()) {
                MessageAlerts.showErrorMessage("Unesite sve podatke!");
            } else if (username.equals("admin") && password.equals("admin")) {
                primaryStage.setScene(gui.AdminScene.getAdminView());
            } else {
                MessageAlerts.showErrorMessage("Korisnicki podaci nisu validni!");
            }
        });

        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image(imageFile.toURI().toString()));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        bp.requestFocus();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
