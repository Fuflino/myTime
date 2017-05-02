/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import mytime.be.Group;
import mytime.gui.model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Stefan Olsen
 */
public class VolunteerOneGuildController implements Initializable
{

    private static final String SELECTED = "-fx-background-color: rgb(215,215,215);-fx-background-radius: 10px;";
    @FXML
    private Button btnGuild;
    private Group guild;
    
    private VolunteerMainViewController mainController;

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
        List<VolunteerOneGuildController> guildControllers = mainController.getGuildControllers();
        btnGuild.setStyle(SELECTED);
        VolunteerModel.getInstance().setCurrentGuild(guild);
        for (VolunteerOneGuildController guildController : guildControllers)
        {
            
            
            if (guildController != this)
            {
                guildController.getBtnGuild().setStyle(null);
                
            }
        }
        
    }

    /**
     * Sets the "super controller" that holds the list of guild-buttons.
     * @param controller 
     */
    void setMain(VolunteerMainViewController controller)
    {
        mainController =  controller;
    }

    public Button getBtnGuild()
    {
        return btnGuild;
    }

    public void setGuild(Group guild)
    {
        this.guild = guild;
    }
    
}
