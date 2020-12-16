package com.company;

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

    private static int keygen(){
        Double randomKey = Math.random()*10000*Math.random();
        return randomKey.intValue();
    }
}
