/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSpinner;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mytime.be.Location;
import mytime.gui.model.Model;
import mytime.gui.model.VolunteerModel;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.ValidationDecoration;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class ChooseLokationController implements Initializable
{

    @FXML
    private JFXComboBox<Location> comboBoxLocation;
    @FXML
    private JFXCheckBox checkBoxRemember;

    private Model model;
    private VolunteerModel volunteerModel;
    private Executor exec;
    @FXML
    private VBox vbox;
    private ValidationDecoration iconDecorator;
    @FXML
    private AnchorPane pane;
    private JFXSnackbar snackbar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        snackbar = new JFXSnackbar(pane);
        iconDecorator = new GraphicValidationDecoration();
        model = Model.getInstance();
        volunteerModel = VolunteerModel.getInstance();

        // Hente alle locations
        setAllLocationsAsItems();

        exec = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });

        comboBoxLocation.focusedProperty().addListener((o, oldVal, newVal)
                -> 
                {
                    iconDecorator.removeDecorations(comboBoxLocation);
        });
    }

    /**
     * Executes the choose location
     *
     * @param event
     */
    @FXML
    private void handleChooseLocation(ActionEvent event)
    {

        if (comboBoxLocation.getSelectionModel().getSelectedItem() == null)
        {
            iconDecorator.removeDecorations(comboBoxLocation);
            iconDecorator.applyValidationDecoration(ValidationMessage.error(comboBoxLocation, ""));
            pane.requestLayout();
            snackbar.show("Vælg lokation først", 2700);
        } else
        {
            iconDecorator.removeDecorations(comboBoxLocation);
            Node button = vbox.getChildren().get(2);
            vbox.getChildren().remove(2);
            JFXSpinner spinner = new JFXSpinner();
            vbox.getChildren().add(spinner);
            spinner.requestFocus();
            Task<Location> courseTask = new Task<Location>()
            {
                @Override
                public Location call()
                {
                    Location b = null;
                    try
                    {
                        b = model.getSelectedLocation(comboBoxLocation.getSelectionModel().getSelectedItem());
                    } catch (SQLException ex)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("SQL-Exception");
                        alert.setContentText(ex.getMessage());
                        alert.show();
                    }

                    return b;
                }
            };

            courseTask.setOnSucceeded(e

                    -> 
                    {
                        Location course = courseTask.getValue();
                        if (null != course)
                        {
                            volunteerModel.setCurrentLocation(course);

                            try
                            {
                                Stage mainView = (Stage) comboBoxLocation.getScene().getWindow();
                                mainView.close();

                                Parent mainViewLoad = FXMLLoader.load(getClass().getResource("/mytime/gui/view/LoginMainView.fxml"));
                                Scene scene = new Scene(mainViewLoad);

                                mainView.setScene(scene);
                                mainView.setResizable(true);
                                mainView.show();

                            } catch (IOException ex)
                            {
                                ex.printStackTrace();
                            }

                        } else
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Could not get any locations");
                            alert.setContentText("The returned locations was null. You fucked up, dickhead");
                            alert.show();
                            vbox.getChildren().remove(2);
                            vbox.getChildren().add(button);
                            comboBoxLocation.requestFocus();
                        }

            }
            );
            courseTask.setOnFailed(e
                    -> 
                    {
                        courseTask.getException().printStackTrace();
            }
            );
            exec.execute(courseTask);
        }
    }

    private void setAllLocationsAsItems()
    {
        try
        {
            ObservableList items = FXCollections.observableArrayList();
            items.addAll(model.getAllLocations());
            comboBoxLocation.setItems(items);

        } catch (SQLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Couldn't get a connection to the database");
            alert.setContentText("Check your internet connection, and make you you are connected properly\nErrorcode: " + ex.getMessage());
            alert.show();

        }
    }

}
