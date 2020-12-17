package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static Socket clientSocket;
//сокет для общения

    private static ServerSocket server;
// серверсокет

    private static BufferedReader in;
// поток чтения из сокета

    private static BufferedWriter out;
// поток записи в сокет


    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004);
// серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!");
// хорошо бы серверу
//   объявить о своем запуске
                clientSocket = server.accept();
// accept() будет ждать пока
//кто-нибудь не захочет подключиться
                try {
// установив связь и воссоздав сокет для общения с клиентом можно перейти
// к созданию потоков ввода/вывода.
// теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
// и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String word = in.readLine();
// ждём пока клиент что-нибудь нам напишет
                    String command = word.substring(0,word.indexOf("%"));
                    System.out.println("Command: " + command);


                    //обработка команд клиента
                    String localBody = word.substring(word.indexOf("%")+1);
                    switch (command){

                        case("lobby"):
                            Integer lobbyAnswer = lobby(Integer.valueOf(localBody));
                            out.write(lobbyAnswer.toString());
                            out.flush();
                            break;
                        case("newGame"):
                            //TODO start a game here with parameters(lobbyCode). It must be previously generated or gained
                            try {
                                Integer lobbyCode = Integer.valueOf(localBody);
                            }catch (NumberFormatException e){
                                //Если каким-то образом команда была выполнена без надлежащего кода лобби
                                System.err.println(e);
                            }



                            break;



                    }











// не долго думая отвечает клиенту

// выталкиваем все из буфера
                } finally {
// в любом случае сокет будет закрыт
                    clientSocket.close();
// потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }



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


    //Блок работы с файлами


    public static String lobbiesFilePath = "/com.company/resources/lobbies.txt";
    private static HashMap<Integer, ArrayList<String>> lobbiesFileReader(Integer lobbyCode) throws IOException {

        HashMap<Integer, ArrayList<String>> lobbiesHash = new HashMap<>();
        List<String> tempLobbiesList = Files.readAllLines(Path.of(lobbiesFilePath));
        if (tempLobbiesList.stream().count() != 0) {

            for (String line : tempLobbiesList) {
                Integer lobbyCodeLocal = Integer.valueOf(line.substring(0, line.indexOf(":"))); //may be bugs here
                ArrayList<String> lobbyMap = new ArrayList<String>(Arrays.asList(line.substring(line.indexOf(":") + 1)));
                lobbiesHash.put(lobbyCodeLocal, lobbyMap);
            }

        }

    return lobbiesHash;

    }
    private static void lobbiesFileDelete(Integer lobbyCode) throws IOException {
        File lobbiesFile = new File(lobbiesFilePath);
        HashMap<Integer, ArrayList<String>> lobbiesHash = lobbiesFileReader(lobbyCode);
        lobbiesFile.delete();

        Files.createFile(Path.of(lobbiesFilePath));
        lobbiesFile = new File(lobbiesFilePath);
        FileWriter fw = new FileWriter(lobbiesFile, true);
        lobbiesHash.remove(lobbyCode);
        for (Map.Entry<Integer, ArrayList<String>> entry: lobbiesHash.entrySet()){
            fw.write(entry.getKey() + ":" + entry.getValue() + "\n");
        }
        fw.close();

    }

    private static void lobbiesFileWriterAppend(Integer lobbyCode, ArrayList<String> lobbyMap) throws IOException {
        File usersFile = new File(lobbiesFilePath);
        FileWriter fw = new FileWriter(usersFile, true);
        fw.write(lobbyCode.toString() + ":" + lobbyCode.toString());
        fw.close();

    }


    private static ArrayList<String> findMapByLobbyCode(Integer lobbyCode) throws IOException {
        HashMap<Integer, ArrayList<String>> lobbiesHash = lobbiesFileReader(lobbyCode);
        return lobbiesHash.get(lobbyCode);
    }

    private static Integer firstTurn(){
        Random random = new Random();
        if (random.nextBoolean()){
            return 1;
        }else{
            return 0;
        }
    }


}
