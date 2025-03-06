package foc.es.banco.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuPrincipal {
    private final Stage stage;

    public MenuPrincipal(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        // Crear el contenedor principal
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Crear el título
        Text title = new Text("Bienvenido");
        title.setFont(new Font(24));
        title.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(title);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Etiqueta y campo de texto para el usuario
        Label userLabel = new Label("Usuario:");
        TextField userField = new TextField();
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);

        // Etiqueta y campo de texto para la contraseña
        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        // Botón de entrar
        Button loginButton = new Button("Entrar");
        gridPane.add(loginButton, 1, 2);

        // Añadir el formulario al contenedor principal
        root.getChildren().add(gridPane);

        // Crear y devolver la escena
        Scene scene = new Scene(root, 300, 200);
        return scene;
    }
}
