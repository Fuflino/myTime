/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the Top of our borderpane, in the view where you select wich volunteer you would like to add
 * hours to.
 *
 * @author Stefan-VpcEB3J1E
 */
public class LoginTopViewController implements Initializable
{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imgView;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    /**
     * Gets called when you would like to login as a manager.
     * @param event 
     */
    @FXML
    private void handleBtnManager(ActionEvent event)
    {
    }
    
}
