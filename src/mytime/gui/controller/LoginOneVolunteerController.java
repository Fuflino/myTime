/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mytime.be.Person;
import mytime.gui.model.Model;
import mytime.gui.model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class LoginOneVolunteerController implements Initializable
{

    @FXML
    private Button btnVolunteer;
    private Person volunteer;
    private VolunteerModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = VolunteerModel.getInstance();
    }

    /**
     * Gets called when you click on a volunteer button in the TileView.
     *
     * @param event
     */
    @FXML
    private void handleBtnVolunteerClick(ActionEvent event)
    {
        VolunteerModel.getInstance().setCurrentVolunteer(volunteer);
        System.out.println(volunteer.getId().get());

        Stage mainView = (Stage) btnVolunteer.getScene().getWindow();
        mainView.close();

        Parent mainViewLoad = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytime/gui/view/NewVolunteerView.fxml"));
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            Logger.getLogger(LoginOneVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainViewLoad = loader.getRoot();
        
        Scene scene = new Scene(mainViewLoad);

        mainView.setScene(scene);
        mainView.setResizable(true);
        mainView.show();
    }

    /**
     * Sets the volunteer of this controller class
     *
     * @param volunteer
     */
    public void setVolunteer(Person volunteer)
    {
        this.volunteer = volunteer;
    }

}
