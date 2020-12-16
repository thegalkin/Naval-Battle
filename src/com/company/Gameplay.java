package com.company;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.net.SecureCacheResponse;
import java.net.URISyntaxException;

public class Gameplay extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Media soundTheme = null;
//
//            File f = new File("out/main/resources/ost.mp3");
//            soundTheme= new Media(f.toURI().toString());
//
//        MediaPlayer mediaPlayer = new MediaPlayer(soundTheme);
//        mediaPlayer.autoPlayProperty();
        Double basicSize = 20.0;
        Double objectMargin = 10.0;
        Group targets = new Group();
        for (int i = 0; i < 100; i++) {
            Rectangle basicRect = new Rectangle(primaryStage.getWidth()/4 + (basicSize + objectMargin) *i,primaryStage.getHeight()/4 + (basicSize + objectMargin) *i,basicSize,basicSize);
            Rectangle basicRect = new Rectangle(20.0+basicSize, )
            basicRect.setFill(Color.rgb(0,0,0));
            targets.getChildren().add(basicRect);
        }

        targets.getChildren().add(new Rectangle(0,0,20,20));
        primaryStage.setTitle("Naval Action");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        Scene scene = new Scene(targets, 0, 0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

