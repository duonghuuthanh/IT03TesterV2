package com.dht.bmiapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        int t = 100;
        if (t > 0)
            App.setRoot("secondary");
    }
}
