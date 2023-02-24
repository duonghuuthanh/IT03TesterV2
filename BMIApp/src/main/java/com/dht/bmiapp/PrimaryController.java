package com.dht.bmiapp;

import com.dht.services.BMIService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class PrimaryController {
    @FXML private TextField txtWeight;
    @FXML private TextField txtHeight;
    @FXML private Label lblResult;
    
    public void tinhBmiHandler(ActionEvent evt) {
        double h = Double.parseDouble(this.txtHeight.getText());
        double w = Double.parseDouble(this.txtWeight.getText());
        int d = BMIService.tinhBMI(h, w);
        
        String result;
        if (d == 1) {
            result = "Gầy";
            this.lblResult.setTextFill(Color.RED);
        } else if (d == 2) {
            result = "Bình thường";
            this.lblResult.setTextFill(Color.BLUE);
        } else {
            result = "Béo phì";
            this.lblResult.setTextFill(Color.ORANGERED);
        }
        
        this.lblResult.setText(result);
    }
}
