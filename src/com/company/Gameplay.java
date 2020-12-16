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
        Double windowSize = 700.0;
        Double basicSize = 20.0;

        Group targets = new Group();
        Double marginTop = windowSize / 3;
        Double marginLeft = marginTop;
        Double objectMargin = 5.0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle basicRect = new Rectangle( marginLeft + (basicSize + objectMargin) *i,marginTop + (basicSize + objectMargin) *j,basicSize,basicSize);
//            Rectangle basicRect2 = new Rectangle(20.0+basicSize*i, 20.0+basicSize*i, basicSize, basicSize);
                basicRect.setFill(Color.rgb(0,51,255));
                targets.getChildren().add(basicRect);
            }

        }


        primaryStage.setTitle("Naval Action");
        primaryStage.setWidth(windowSize);
        primaryStage.setHeight(windowSize);
        Scene scene = new Scene(targets, 0, 0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

