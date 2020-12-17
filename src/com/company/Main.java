package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                            Integer lobbyAnswer = Server.lobby(Integer.valueOf(localBody));
                            out.write(lobbyAnswer.toString());
                            out.flush();
                            break;
                        case("game"):
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
}
