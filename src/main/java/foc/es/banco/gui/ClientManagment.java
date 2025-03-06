package foc.es.banco.gui;

import foc.es.banco.client.model.Client;
import foc.es.banco.client.service.ClientReadService;
import foc.es.banco.client.service.ClientWriteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class ClientManagment {
    private final ClientReadService clientReadService;
    private final ClientWriteService clientWriteService;
    private TextField nameField;
    private TextField addressField;
    private TextField phoneField;

    public ClientManagment( ClientReadService clientReadService, ClientWriteService clientWriteService) {
        this.clientReadService = clientReadService;
        this.clientWriteService = clientWriteService;
    }

    public Scene getScene(Stage stage) {
        // Crear el contenedor principal
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Crear el GridPane para los campos de cliente
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

        // Etiquetas y campos de texto para los datos del cliente
        Label nameLabel = new Label("Nombre:");
        nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        Label addressLabel = new Label("Dirección:");
        addressField = new TextField();
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1);

        Label phoneLabel = new Label("Teléfono:");
        phoneField = new TextField();
        gridPane.add(phoneLabel, 0, 2);
        gridPane.add(phoneField, 1, 2);

        // Crear el ListView para la lista de clientes
        Label clientListLabel = new Label("Client List:");
        ListView<String> clientListView = new ListView<>();
        ObservableList<String> clientList = FXCollections.observableArrayList();
        clientListView.setItems(clientList);
        clientListView.setPrefHeight(150);

        // Crear el HBox para los botones
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);

        // Botón para agregar cliente
        Button addButton = new Button("Agregar");
        addButton.setOnAction(event -> agregarCliente());

        // Botón para buscar cliente
        Button searchButton = new Button("Buscar");
        searchButton.setOnAction(event -> buscarCliente());

        // Botón para eliminar cliente
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(event -> eliminarCliente());

        // Añadir los botones al HBox
        buttonBox.getChildren().addAll(addButton, searchButton, deleteButton);

        // Añadir el GridPane, el ListView y el HBox al contenedor principal
        root.getChildren().addAll(gridPane, clientListLabel, clientListView, buttonBox);

        // Crear y devolver la escena
        return new Scene(root, 400, 500);
    }

    private void agregarCliente() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();

        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setPhone(phone);

        clientWriteService.create(client);

    }

    private void buscarCliente() {
    }

    private void eliminarCliente() {

    }
}
