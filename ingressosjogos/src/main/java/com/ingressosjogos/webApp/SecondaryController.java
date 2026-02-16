package com.ingressosjogos.webApp;

import java.io.IOException;

import com.ingressosjogos.webApp.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}