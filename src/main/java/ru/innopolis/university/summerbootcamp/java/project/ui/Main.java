package ru.innopolis.university.summerbootcamp.java.project.ui;/**
 * Created by dalv6_000 on 18.07.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/game.fxml"));
        Parent root = loader.load();
        root.getStylesheets().addAll(getClass().getClassLoader().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Innopoker");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 600));
        // Get the Stage.

        // Add a custom icon.
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("ui/logo.png").toExternalForm()));
        primaryStage.show();

    }





}
