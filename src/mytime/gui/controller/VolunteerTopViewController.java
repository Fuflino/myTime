/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mytime.gui.model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Stefan Olsen
 */
public class VolunteerTopViewController implements Initializable
{

    @FXML
    private ImageView imageView;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhonenumber;
    @FXML
    private JFXButton btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        dataBindGuiComponents();
        
        // TODO
    }    

    /**
     * Binds the labels to the values of the current volunteer, and sets the image
     */
    private void dataBindGuiComponents()
    {
        VolunteerModel vmodel = VolunteerModel.getInstance();
        
        lblName.textProperty().bind(vmodel.getCurrentVolunteer().getName());
        lblEmail.textProperty().bind(vmodel.getCurrentVolunteer().getEmail());
        lblPhonenumber.textProperty().bind(vmodel.getCurrentVolunteer().getPhonenumber());
        Image img = new Image(vmodel.getCurrentVolunteer().getProfilePicture().get());
        imageView.setImage(img);
    }
    
    /**
     * Closes this view and loads the mainview with all the volunteers at the
     * current location.
     * @param event 
     */
    @FXML
    private void handleGoBack(ActionEvent event)
    { 
        VolunteerModel vmodel = VolunteerModel.getInstance();
        vmodel.getUserHourInput().set(0);
        vmodel.setCurrentGuild(null);
        vmodel.setCurrentVolunteer(null);
        
        Stage mainView = (Stage) btnBack.getScene().getWindow();
        mainView.close();

        Parent mainViewLoad = null;
        try
        {
            mainViewLoad = FXMLLoader.load(getClass().getResource("/mytime/gui/view/LoginMainView.fxml"));
        } catch (IOException ex)
        {
            Logger.getLogger(LoginOneVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(mainViewLoad);

        mainView.setScene(scene);
        mainView.setResizable(true);
        mainView.show();
    }
    
}
