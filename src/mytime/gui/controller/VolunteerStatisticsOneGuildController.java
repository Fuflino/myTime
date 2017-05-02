/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerStatisticsOneGuildController implements Initializable
{

    @FXML
    private ImageView imgViewIcon;
    @FXML
    private Label lblGuildName;
    @FXML
    private Label lblGuildHours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    } 
    
    public void setGuildIcon(String url)
    {
        this.imgViewIcon.setImage(new Image(url));
    }
    public void setGuildName(String name)
    {
        this.lblGuildName.setText(name);
    }
    public void setGuildHours(int hours)
    {
        lblGuildHours.setText(hours+"");
    }
    
}
