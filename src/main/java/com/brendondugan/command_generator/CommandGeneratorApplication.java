package com.brendondugan.command_generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CommandGeneratorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CommandGeneratorApplication.class.getResource("command-generator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Chest Refiller Command Generator");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
