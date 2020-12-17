package com.company;

import javafx.application.Platform;

public class Test {
    public static void main(String[] args){
    gaming();
    }
    public static void gaming(){
        Server.newGame();


//        Platform.startup(() ->
//        {
//            Gameplay gameplay = new Gameplay();
//
//            gameplay.setTurnText("new text");
//            System.out.println("In startup");
//        });


    }
}
