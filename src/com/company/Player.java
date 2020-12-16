package com.company;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Player {
    private static Socket clientSocket;
//сокет для общения

    private static BufferedReader reader;
// нам нужен ридер читающий с консоли, иначе как


// мы узнаем что хочет сказать клиент?

    private static BufferedReader in;
// поток чтения из сокета

    private static BufferedWriter out;
// поток записи в сокет


    public static void main(String[] args) {
        try {
            try {

// адрес - локальный хост, порт - 4004, такой же как у сервера

                clientSocket = new Socket("localhost", 4004);
// этой строкой мы запрашиваем


//  у сервера доступ на соединение

                reader = new BufferedReader(new InputStreamReader(System.in));

// читать соообщения с сервера

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

// писать туда же

                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

//При старте программы вызываем лобби, чтобы узнать, что делать дальше
                lobby();


// отправляем сообщение на сервер


                String serverWord = in.readLine();
// ждём, что скажет сервер

                System.out.println(serverWord);
// получив - выводим на экран

            } finally {
// в любом случае необходимо закрыть сокет и потоки

                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    public static void lobby(){
        Scanner localScanner = new Scanner(System.in);
        System.out.println("Do you have lobby-code?(y/n)");
        String tempInput = localScanner.nextLine();
        Integer lobbyCode = 0;
        if(tempInput.equals("y")){
            System.out.println("Please write your code:");
            communication(localScanner.nextInt());
        }else if(tempInput.equals("n")){
            System.out.println("Generating your code");

            communication(0);

        }else{
            System.out.println("What do you mean by - \""+tempInput + "\"");
        }


    }
    public static void communication(Integer lobbyCode){

        // команда отправки кода лобби
        String command = "lobby";
        try {

            out.write(command + "%" + lobbyCode + "\n");
            // отправляем сообщение на сервер
            out.flush();
            String serverAnswer = in.readLine();
            if (serverAnswer.equals("0")){
                System.out.println("Server send code: " + lobbyCode);
            }else{
                System.out.println("Server send code: " + serverAnswer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
