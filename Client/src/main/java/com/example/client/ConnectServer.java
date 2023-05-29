package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectServer {
    private Socket socket;
    public ConnectServer(Socket socket) {
        this.socket = socket;
    }
    public String sendRequest(String message) {
        try {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // sending the user input to server
            out.println(message);
            out.flush();

            // displaying server reply
            message = in.readLine();
            System.out.println("Server replied " + message);

            return message;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
