package com.example.client.controller;

import com.example.client.model.User;
import com.example.client.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private Stage stage;

    public FriendInfoController(String screenPath, User user, Stage stage) throws IOException {
        super(screenPath);
        this.user1 = user;
        this.stage = stage;
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

    public void setFriendRequestInfo(User user) {
        this.user2 = user;
        labelName.setText(user.getName());
        btnUnfriend.setVisible(false);
        btnRequestFriend.setVisible(false);
    }

    private void reloadPage() throws IOException {
        FriendController friendController = new FriendController(stage, Config.FRIEND_SCREEN_PATH);
        friendController.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUnfriend.setOnMouseClicked(e -> {
            try {
                BaseController.handler.unfriend(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnRequestFriend.setOnMouseClicked(e -> {
            try {
                BaseController.handler.sendFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnAccept.setOnMouseClicked(e-> {
            try {
                BaseController.handler.acceptFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDenied.setOnMouseClicked(e -> {
            try {
                BaseController.handler.deniedFriendRequest(user1.getId(), user2.getId());
                reloadPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
