package foc.es.banco.gui;

import foc.es.banco.client.service.ClientReadService;
import foc.es.banco.client.service.ClientWriteService;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SceneController {
    private final ApplicationContext context;
    private Stage primaryStage;

    ClientReadService clientReadService;
    ClientWriteService clientWriteService;

    public SceneController(ApplicationContext context, ClientReadService clientReadService, ClientWriteService clientWriteService) {
        this.context = context;
        this.clientReadService = clientReadService;
        this.clientWriteService = clientWriteService;
    }

    public void loadApplication(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Main Menu");
        this.primaryStage.setResizable(false);

        MenuPrincipal menuPrincipal = new MenuPrincipal(primaryStage);
        Scene sceneMenuPrincipal = menuPrincipal.getScene();

        ClientManagment clientManagment = new ClientManagment(clientReadService, clientWriteService);
        Scene sceneClientManagment = clientManagment.getScene(primaryStage);

        primaryStage.setScene(sceneClientManagment);
        primaryStage.show();
    }
}