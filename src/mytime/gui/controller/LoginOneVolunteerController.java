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
import mytime.be.Volunteer;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class LoginOneVolunteerController implements Initializable
{

    @FXML
    private Button btnVolunteer;
    private Volunteer volunteer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    /**
     * Gets called when you click on a volunteer button in the TileView.
     * @param event 
     */
    @FXML
    private void handleBtnVolunteerClick(ActionEvent event)
    {
    }
    /**
     * Sets the volunteer of this controller class
     * @param volunteer 
     */
    public void setVolunteer(Volunteer volunteer)
    {
        this.volunteer = volunteer;
    }
    
    
    
}
