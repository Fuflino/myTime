/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import mytime.be.Group;
import mytime.gui.model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Stefan Olsen
 */
public class VolunteerMainViewController implements Initializable
{

    @FXML
    private JFXMasonryPane masonryPane;
    @FXML
    private ScrollPane scrollPane;
    
    private VolunteerModel volunteerModel;
    @FXML
    private Label lblUserHourInput;
    private List<VolunteerOneGuildController> guildControllers;
    private JFXSnackbar snackBar;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        snackBar = new JFXSnackbar(gridPane);
        
        
        volunteerModel = VolunteerModel.getInstance();
        lblUserHourInput.textProperty().bind(volunteerModel.getUserHourInput().asString());
        ArrayList<Node> elements = new ArrayList();
        masonryPane.setCellHeight(100);
        masonryPane.getStyleClass().add("defaultBackgroundColor");
        guildControllers = new ArrayList();

        try
        {
            List<Group> guildsAtLocation = VolunteerModel.getInstance().getCurrentLocation().getGroups();
            for (int i = 0; i < guildsAtLocation.size(); i++)
            {
                elements.add(getNodeForGuild(guildsAtLocation.get(i)));
                
            }
        } catch (IOException ex)
        {
            Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        masonryPane.getChildren().setAll(elements);
    }

    /**
     * Method for getting a guild as a node, so that we can load it in our view
     * as a JAVAFX component.
     *
     * @param volunteer
     * @return
     * @throws IOException
     */
    private Node getNodeForGuild(Group group) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytime/gui/view/VolunteerOneGuild.fxml"));
        
        
        Node node = loader.load();
        Button button = (Button) node;
        
        VolunteerOneGuildController controller = loader.getController();
        guildControllers.add(controller);
        controller.setGuild(group);
        controller.setMain(this);
        
        // load the image
        Image image = new Image("mytime/gui/view/css/notebook.png");
        
        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
//
        iv1.setFitWidth(70);
        iv1.setFitHeight(50);
//        Circle clip = new Circle(iv1.getFitHeight() / 2, iv1.getFitWidth() / 3, 26);
//
//        iv1.setClip(clip);
        iv1.setImage(image);
//
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        button.setPrefHeight(70);
        button.setPrefWidth(150);
        button.getStyleClass().add("LoginVolunteerBtn");

        button.setScaleX(0);
        button.setScaleY(0);
        button.setGraphic(iv1);
        button.setText(group.getName().get());

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(240), new KeyValue(button.scaleXProperty(), 1, Interpolator.EASE_BOTH),
                new KeyValue(button.scaleYProperty(), 1, Interpolator.EASE_BOTH)));
        animation.setDelay(Duration.millis(100 * 2 + (1000)));
        animation.play();

        return node;
    }
    /**
     * Gets called when you substract a hour
     * @param event 
     */
    @FXML
    private void handleHourInputDown(ActionEvent event)
    {
        volunteerModel.minusOneUpInUserHoursInput();
    }
    /**
     * Gets called when you add a hour up
     * @param event 
     */
    @FXML
    private void handleHourInputUp(ActionEvent event)
    {
        volunteerModel.addOneUpInUserHourInput();
    }
    /**
     * Gets called when you press on the execute button.
     * @param event 
     */
    @FXML
    private void handleExecuteHourInput(ActionEvent event)
    {
        int hours = volunteerModel.getUserHourInput().get();
        try
        {
            volunteerModel.executeHourDocumentation();
        } catch (SQLException ex)
        {
            //Alert no connection to database
            Logger.getLogger(VolunteerMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        snackBar.show("Dokumenterede "+hours+ " timer ved laug "+ volunteerModel.getCurrentGuild() +" med success!" , 4000);
    }

    public List<VolunteerOneGuildController> getGuildControllers()
    {
        return guildControllers;
    }

}
