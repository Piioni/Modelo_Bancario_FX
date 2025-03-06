package foc.es.banco;

import foc.es.banco.gui.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApp extends Application {
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        context = SpringApplication.run(SpringApp.class);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneController sceneController = context.getBean(SceneController.class);
        sceneController.loadApplication(primaryStage);
    }

    @Override
    public void stop() {
        context.close();
    }
}