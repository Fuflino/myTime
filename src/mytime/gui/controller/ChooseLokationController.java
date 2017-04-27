/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class ChooseLokationController implements Initializable
{

    @FXML
    private JFXComboBox<String> comboBoxLocation;
    @FXML
    private JFXCheckBox checkBoxRemember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    /**
     * Executes the choose location
     * @param event 
     */
    @FXML
    private void handleChooseLocation(ActionEvent event)
    {
    }
    
}
