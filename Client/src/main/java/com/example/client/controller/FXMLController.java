package com.example.client.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FXMLController {
    protected FXMLLoader loader;
    protected AnchorPane content;

    public FXMLController(String screenPath) throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(screenPath));
        // Set this class as the controller
        this.loader.setController(this);
        this.content = (AnchorPane) loader.load();
    }

    public AnchorPane getContent() {
        return this.content;
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }
}
