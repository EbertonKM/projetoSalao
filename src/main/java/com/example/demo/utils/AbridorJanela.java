package com.example.demo.utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.stereotype.Component;

@Component
public class AbridorJanela {

    private final SpringFXMLLoader fxmlLoader;
    private FXMLLoader loader;
    public static Stage stagePrincipal = null;

    public AbridorJanela(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void abrirNovaJanela(String fxmlPath, String titulo, double width, double height) {
        try {
            loader = fxmlLoader.load(fxmlPath);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(titulo);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.setScene(new Scene(loader.getRoot()));
            if(stagePrincipal == null)
                stagePrincipal = stage;
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir janela: " + e.getMessage());
        }
    }

    public void alterarJanela(String fxmlPath, String titulo, Stage stage) {
        if(stage == null)
            stage = stagePrincipal;
        try {
            loader = fxmlLoader.load(fxmlPath);
            Parent root = loader.getRoot();
            Scene scene = stage.getScene();

            if (scene == null) {
                scene = new Scene(root);
                stage.setScene(scene);
            } else {
                scene.setRoot(root);
            }

            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao trocar tela: " + e.getMessage());
        }
    }

    public FXMLLoader getFxmlLoader() {
        return loader;
    }

}