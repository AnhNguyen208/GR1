package com.example.client.controller;

import com.example.client.handler.Handler;
import com.example.client.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendInfoController extends FXMLController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Label labelName;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnDenied;
    @FXML
    private Button btnRequestFriend;
    @FXML
    private Button btnUnfriend;
    private User user1;
    private User user2;
    private Handler handler;

    public FriendInfoController(String screenPath, User user) throws IOException {
        super(screenPath);
        this.user1 = user;
    }

    public void setFriendInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnRequestFriend.setVisible(false);
    }

    public void setFriendSuggestionInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnAccept.setVisible(false);
        btnDenied.setVisible(false);
        btnUnfriend.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUnfriend.setOnMouseClicked(e -> {
            BaseController.handler.unfriend(user1.getId(), user2.getId());
            this.getContent().getChildren().clear();
        });

        btnRequestFriend.setOnMouseClicked(e -> {
            BaseController.handler.friendRequest(user1.getId(), user2.getId());
            this.getContent().getChildren().clear();
        });
    }
}
