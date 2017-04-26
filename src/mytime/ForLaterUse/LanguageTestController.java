/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.ForLaterUse;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class LanguageTestController implements Initializable
{

    @FXML
    private Label lblHello;
    @FXML
    private Label lblBye;
    private ResourceBundle resources;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleDansk(ActionEvent event)
    {
        loadView(new Locale("da_DK"));
    }

    @FXML
    private void handleEng(ActionEvent event)
    {
        loadView(new Locale("eng"));
    }

    private void loadView(Locale locale)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResourceBundle bundle = ResourceBundle.getBundle("mytime.gui.UIResources", locale);
            Parent root = FXMLLoader.load(getClass().getResource("/mytime/gui/view/LanguageTest.fxml"), bundle);
            // replace the content
            AnchorPane content = (AnchorPane) lblBye.getScene().getRoot();
            content.getChildren().clear();
            content.getChildren().add(root);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
