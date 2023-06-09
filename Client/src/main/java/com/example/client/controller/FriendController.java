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
    private List<User> friendRequestList;

    public FriendController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private void displayFriendList() {
        try {
            label.setText("Danh sách bạn bè");
            this.gridPane.getChildren().clear();
            friendList = BaseController.handler.friendList(BaseController.user.getId());
            for (User user: friendList) {
                Integer integer = friendList.indexOf(user);

                FriendInfoController friendInfoController = new FriendInfoController(Config.FRIEND_INFO_SCREEN_PATH,
                        BaseController.user, this.stage);
                friendInfoController.setFriendInfo(user);
                this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void displayFriendSuggestionList() {
        try {
            label.setText("Gợi ý kết bạn");
            this.gridPane.getChildren().clear();
            friendSuggestionList = BaseController.handler.friendSuggestionList(BaseController.user.getId());
            for (User user: friendSuggestionList) {
                Integer integer = friendSuggestionList.indexOf(user);
                FriendInfoController friendInfoController = new FriendInfoController(Config.FRIEND_INFO_SCREEN_PATH,
                        BaseController.user, this.stage);
                friendInfoController.setFriendSuggestionInfo(user);
                this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void displayFriendRequestList() {
        try {
            label.setText("Lời mời kết bạn");
            this.gridPane.getChildren().clear();
            friendRequestList = BaseController.handler.friendRequestList(BaseController.user.getId());
            for (User user : friendRequestList) {
                Integer integer = friendRequestList.indexOf(user);
                FriendInfoController friendInfoController = new FriendInfoController(Config.FRIEND_INFO_SCREEN_PATH,
                        BaseController.user, this.stage);
                friendInfoController.setFriendRequestInfo(user);
                this.gridPane.add(friendInfoController.getContent(), integer / 3, integer % 3);
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText("Bạn bè");
        menuButton.setText("Xin chào, " + BaseController.user.getName());
        menuItem1.setText("Trang chủ");
        menuItem1.setOnAction(e -> {
            try {
                HomeController homeController = new HomeController(this.stage, Config.HOME_SCREEN_PATH);
                homeController.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItem2.setText("Thông tin cá nhân");
        menuItem2.setOnAction(e -> {
            try {
                ProfileController profileController = new ProfileController(this.stage, Config.PROFILE_SCREEN_PATH);
                profileController.show();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });
        menuItem3.setText("Đăng xuất");
        menuItem3.setOnAction(e -> {
            try {
                LoginController loginController = new LoginController(this.stage, Config.LOGIN_SCREEN_PATH);
                loginController.show();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });

        displayFriendList();
        label1.setOnMouseClicked(e-> displayFriendList());
        label2.setOnMouseClicked(e-> displayFriendRequestList());
        label3.setOnMouseClicked(e-> displayFriendSuggestionList());
    }
}
