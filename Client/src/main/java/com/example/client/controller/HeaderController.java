package com.example.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class HeaderController extends FXMLController{
    private Stage stage;
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
    public HeaderController(String screenPath, Stage stage) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    public void setMenuItem(MenuItem menuItem1, MenuItem menuItem2, MenuItem menuItem3) {
        this.menuItem1 = menuItem1;
        this.menuItem2 = menuItem2;
        this.menuItem3 = menuItem3;
    }

    public void setHeader(String stringTitle, String name) {
        title.setText(stringTitle);
        menuButton.setText("Xin chào, " + name);
    }
}
