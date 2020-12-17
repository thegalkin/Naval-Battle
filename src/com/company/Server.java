package com.company;

import java.util.ArrayList;

public class Server {

    public static Integer lobby(Integer lobbyCode){
        Integer localOutput = 0;

        if (lobbyCode != 0){
            System.out.println("Lobby has code: "+lobbyCode);
            //TODO make connection to the existing session
        }else{
            localOutput = keygen();
            System.out.println("Generated Lobby Code:" + localOutput);
        }
        return localOutput;

    }
    public static void newGame(){
        ArrayList<String> serverField1 = new ArrayList<String>();
        ArrayList<String> serverField2 = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            serverField1.add(".");
            serverField2.add(".");
        }
    }


    private static int keygen(){
        Double randomKey = Math.random()*10000*Math.random();
        return randomKey.intValue();
    }
}
