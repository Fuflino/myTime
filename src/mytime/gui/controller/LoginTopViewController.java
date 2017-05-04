/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import mytime.gui.model.VolunteerModel;

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
    
    private VolunteerModel volunteerModel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        volunteerModel = VolunteerModel.getInstance();
    }    
    /**
     * Gets called when you would like to login as a manager.
     * @param event 
     */
    @FXML
    private void handleBtnManager(ActionEvent event)
    {
    }
    
    @FXML
    private void handleBtnDan(ActionEvent event)
    {
        loadView(new Locale("dan"));
    }
    
    @FXML
    private void handleBtnEng(ActionEvent event)
    {
        loadView(new Locale("eng"));
    }
    
    @FXML
    private void handleBtnGer(ActionEvent event)
    {
        loadView(new Locale("ger"));
    }
    
    private void loadView(Locale locale)
    {
        volunteerModel.setCurrentLocale(locale);
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResourceBundle bundle = ResourceBundle.getBundle("mytime.gui.UIResources", locale);
            Parent root = FXMLLoader.load(getClass().getResource("/mytime/gui/view/LoginMainView.fxml"), bundle);
            // replace the content
            BorderPane content = (BorderPane) anchorPane.getScene().getRoot();
            content.getChildren().clear();
            content.getChildren().add(root);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
