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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Stefan Olsen
 */
public class VolunteerOneGuildController implements Initializable
{

    private static final String SELECTED = "-fx-background-color: rgb(195,195,195);-fx-background-radius: 10px;";
    @FXML
    private Button btnGuild;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
    /**
     * Gets called when you mark a guild you want to set how many hours you've worked
     * @param event 
     */
    @FXML
    private void handleOnGuildClick(ActionEvent event)
    {
        btnGuild.setStyle(SELECTED);
    }

}
