package com.example.client.controller;

import com.example.client.ConnectServer;
import com.example.client.handler.Handler;
import com.example.client.model.User;
import com.example.client.ultils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController extends BaseController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button btnLogin;

    public LoginController(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public boolean validateEmailAddress() {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher   = regexPattern.matcher(email.getText());
        if(!regMatcher.matches()) {
            displayAlert(Alert.AlertType.ERROR, "Lỗi", "Email không hợp lệ");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnMouseClicked( e-> {
            if(validateEmailAddress()) {
                try {
                    BaseController.handler = new Handler(new ConnectServer(new Socket("localhost", 1234)));
                    User user = BaseController.handler.loginHandler(email.getText(), password.getText());
                    if(user != null) {
                        try {
                            displayAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng nhập thành công");
                            BaseController.user = user;

                            HomeController homeController = new HomeController(this.stage, Config.HOME_SCREEN_PATH);
                            homeController.show();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        displayAlert(Alert.AlertType.ERROR, "Lỗi", "Email hoặc mật khẩu không chính xác");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
