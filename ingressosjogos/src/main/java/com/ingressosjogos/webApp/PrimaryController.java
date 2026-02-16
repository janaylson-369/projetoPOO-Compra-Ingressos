package com.ingressosjogos.webApp;

import java.io.IOException;

import com.ingressosjogos.webApp.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
