package com.example.client.controller;

import com.example.client.model.User;
import com.example.client.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FriendController extends BaseController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    private List<User> friendList;
    private List<User> friendSuggestionList;
    public FriendController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private boolean checkFriendSuggestion(User user) {
        for (User user1: friendList) {
            if(user1.getId() == user.getId()) {
                return false;
            }
        }
        return true;
    }

    private void displayFriendList() {
        try {
            label.setText("Danh sách bạn bè");
            this.gridPane.getChildren().clear();
            friendList = BaseController.handler.friendList(BaseController.user.getId());
            for (User user: friendList) {
                Integer integer = friendList.indexOf(user);

                FriendInfoController friendInfoController = new FriendInfoController(Config.FRIEND_INFO_SCREEN_PATH, BaseController.user);

                friendInfoController.setFriendInfo(user);
                this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void displayFriendSuggestion() {
        try {
            label.setText("Gợi ý kết bạn");
            this.gridPane.getChildren().clear();
            friendSuggestionList = BaseController.handler.userList();
            List<User> cloneList = new ArrayList<>(friendSuggestionList);
            for (User user: friendSuggestionList) {
                if((!checkFriendSuggestion(user)) || (user.getId() == BaseController.user.getId())) {
                    cloneList.remove(user);
                }
            }
            for (User user: cloneList) {
                Integer integer = cloneList.indexOf(user);
                FriendInfoController friendInfoController = new FriendInfoController(Config.FRIEND_INFO_SCREEN_PATH, BaseController.user);

                friendInfoController.setFriendSuggestionInfo(user);
                this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void displayFriendRequest() {
        label.setText("Lời mời kết bạn");
        this.gridPane.getChildren().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText("Bạn bè");
        menuButton.setText("Xin chào, " + BaseController.user.getName());

        displayFriendList();

        label1.setOnMouseClicked(e-> displayFriendList());

        label2.setOnMouseClicked(e-> displayFriendRequest());

        label3.setOnMouseClicked(e-> displayFriendSuggestion());

    }
}
