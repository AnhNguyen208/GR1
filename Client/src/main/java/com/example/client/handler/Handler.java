package com.example.client.handler;

import com.example.client.ConnectServer;
import com.example.client.model.User;
import com.example.client.ultils.Request;
import com.example.client.ultils.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    private ConnectServer connectServer;

    public Handler(ConnectServer connectServer) {
        this.connectServer = connectServer;
    }

    public User loginHandler(String email, String password) {
        String msg = connectServer.sendRequest(Request.LOGIN + "|" + email + "|" + password + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            try {
                User user = new User();
                user.setId(Long.valueOf(responseList.get(1)));
                user.setEmail(responseList.get(2));
                user.setName(responseList.get(3));
                user.setPhoneNumber(responseList.get(4));
                user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(responseList.get(5)));

                return user;
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            return null;
        }
    }

    public List<User> friendList(Long id) {
        List<User> userList = new ArrayList<>();
        String msg = connectServer.sendRequest(Request.NUMBER_FRIEND + "|" + id + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        Integer numberFriend = Integer.valueOf(responseList.get(1));
        if(numberFriend > 0) {
            for(int i = 0; i < numberFriend; i++) {
                Long idUser = Long.valueOf(responseList.get(i+2));
                userList.add(userInfo(idUser));
            }
        }
        return userList;
    }

    public List<User> friendSuggestionList(Long id) {
        List<User> userList = new ArrayList<>();
        String msg = connectServer.sendRequest(Request.NUMBER_FRIEND_SUGGESTION + "|" + id + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        Integer numberUser = Integer.valueOf(responseList.get(1));
        if(numberUser > 0) {
            for(int i = 0; i < numberUser; i++) {
                Long idUser = Long.valueOf(responseList.get(i+2));
                userList.add(userInfo(idUser));
            }
        }
        return userList;
    }

    public User userInfo(Long id) {
        String msg = connectServer.sendRequest(Request.USER_INFO + "|" + id + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        try {
            User user = new User();
            user.setId(Long.valueOf(responseList.get(1)));
            user.setEmail(responseList.get(2));
            user.setName(responseList.get(3));
            user.setPhoneNumber(responseList.get(4));
            user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(responseList.get(5)));
            return user;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean updateProfileHandler(Long id, String email, String name, String phoneNumber, String dob) {
        String msg = connectServer.sendRequest(Request.UPDATE_PROFILE + "|" + id + "|" + email + "|" + name + "|" + phoneNumber + "|" + dob + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean unfriend(Long id1, Long id2) {
        String msg = connectServer.sendRequest(Request.UNFRIEND + "|" + id1 + "|" + id2 + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendFriendRequest(Long id1, Long id2) {
        String msg = connectServer.sendRequest(Request.SEND_FRIEND_REQUEST + "|" + id1 + "|" + id2 + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> friendRequestList(Long id) {
        List<User> userList = new ArrayList<>();
        String msg = connectServer.sendRequest(Request.NUMBER_FRIEND_REQUEST + "|" + id + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        Integer numberUser = Integer.valueOf(responseList.get(1));
        if(numberUser > 0) {
            for(int i = 0; i < numberUser; i++) {
                Long idUser = Long.valueOf(responseList.get(i+2));
                userList.add(userInfo(idUser));
            }
        }
        return userList;
    }

    public boolean acceptFriendRequest(Long id1, Long id2) {
        String msg = connectServer.sendRequest(Request.ACCEPT_REQUEST + "|" + id1 + "|" + id2 + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deniedFriendRequest(Long id1, Long id2) {
        String msg = connectServer.sendRequest(Request.DENIED_REQUEST + "|" + id1 + "|" + id2 + "|");
        List<String> responseList = List.of(msg.split("[|]"));
        if(responseList.get(0).equals(Response.SUCCESS.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
