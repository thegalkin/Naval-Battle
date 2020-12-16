package com.company;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.util.Scanner;

public class Gameplay extends Application {
    public void main(String[] args) {
        launch();


        setTurnText("new turn text dima krasaua");
        
    }
    Text whoseTurnBanner;
//
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
                basicRect.setOnMouseClicked( e -> {
                    basicRect.setFill(Color.rgb(255,204,51));
                    //todo handle the click for fight or for preparation
                });
            }

        }
//        String whosTurnText = "Yours";
//        Text whosTurnBanner = new Text(whosTurnText);


//        whosTurnBanner.setText(whosTurnText);
        whoseTurnBanner = new Text("Current Text");
        whoseTurnBanner.setFont(new Font("Roboto", 30));
        targets.getChildren().add(whoseTurnBanner);
        primaryStage.setTitle("Naval Action");
        primaryStage.setWidth(windowSize);
        primaryStage.setHeight(windowSize);
        Scene scene = new Scene(targets, 0, 0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void setTurnText(final String newText) {
       Platform.runLater( new Runnable() {
            @Override
            public void run() {
                whoseTurnBanner.setText(newText);
            }
        });
    }
}

