/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mytime.gui.model.VolunteerModel;

/**
 * Controller class for the Top of our borderpane, in the view where you select
 * wich volunteer you would like to add hours to.
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

    @FXML
    private JFXTextField textFieldFilter;
    @FXML
    private Button btnEng;
    @FXML
    private ImageView imgViewEng;
    @FXML
    private ImageView imgViewGer;
    @FXML
    private ImageView imgViewDan;
    @FXML
    private Button btnGer;
    @FXML
    private Button btnDan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        volunteerModel = VolunteerModel.getInstance();

        volunteerModel.getSearchQuery().bindBidirectional(textFieldFilter.textProperty());
        textFieldFilter.disableProperty().bind(volunteerModel.getIsTextFieldRdy());
        btnDan.disableProperty().bind(volunteerModel.getIsTextFieldRdy());
        btnEng.disableProperty().bind(volunteerModel.getIsTextFieldRdy());
        btnGer.disableProperty().bind(volunteerModel.getIsTextFieldRdy());
        // TODO

    }

    /**
     * Gets called when you would like to login as a manager.
     *
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

        volunteerModel.getUserHourInput().set(0);
        volunteerModel.setCurrentGuild(null);
        volunteerModel.setCurrentVolunteer(null);

        Stage mainView = (Stage) btnEng.getScene().getWindow();
//        mainView.close();

        Parent mainViewLoad = null;
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle("mytime.gui.UIResources", locale);
            mainViewLoad = FXMLLoader.load(getClass().getResource("/mytime/gui/view/LoginMainView.fxml"), bundle);
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
