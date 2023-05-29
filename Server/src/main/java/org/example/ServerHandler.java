package org.example;

import org.example.entity.User;
import org.example.service.FriendService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ServerHandler implements Runnable{
    private final Socket clientSocket;
    private UserService userService;
    private FriendService friendService;

    // Constructor
    public ServerHandler(Socket socket) {
        this.clientSocket = socket;
        userService = new UserService();
        friendService = new FriendService();
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // get the outputstream of client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                // writing the received message from client
                System.out.printf("Sent from the client: %s\n", message);
                List<String> requestList = List.of(message.split("[|]"));
                requestList.get(0);
                System.out.println(requestList.get(0));
                switch (requestList.get(0)){
                    case "LOGIN":
                        message = userService.checkUser(requestList.get(1), requestList.get(2));
                        out.println(message);
                        break;
                    case "NUMBER_FRIEND":
                        message = userService.numberFriend(Long.valueOf(requestList.get(1)));
                        out.println(message);
                        break;
                    case "USER_INFO" :
                        User user = userService.findUserById(Long.valueOf(requestList.get(1)));
                        message = "FRIEND_INFO" + "|" + user.toString();
                        out.println(message);
                        break;
                    case "UPDATE_PROFILE":
                        userService.updateUser(Long.valueOf(requestList.get(1)), requestList.get(2), requestList.get(3),
                                requestList.get(4), requestList.get(5));
                        message = "SUCCESS" + "|";
                        out.println(message);
                        break;
                    case "NUMBER_USER":
                        message = userService.numberUser();
                        out.println(message);
                        break;
                    case "UNFRIEND":
                        friendService.unfriend(Long.valueOf(requestList.get(1)), Long.valueOf(requestList.get(2)));
                        message = "SUCCESS" + "|";
                        out.println(message);
                        break;
                    case "FRIEND_REQUEST":
                        friendService.friendRequest(Long.valueOf(requestList.get(1)), Long.valueOf(requestList.get(2)));
                        message = "SUCCESS" + "|";
                        out.println(message);
                        break;
                    default:
                        System.out.println("Haha");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
