/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import mytime.be.Group;
import mytime.be.Person;
import mytime.gui.model.Model;
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
    private JFXSnackbar snackBar, undoSnackbar;

    @FXML
    private JFXTabPane tabPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane root;
    @FXML
    private JFXButton btnHourDown;
    @FXML
    private JFXButton btnHourUp;
    @FXML
    private JFXButton btnExecuteHourInput;

    private Executor exec;

    private EventHandler undoChangesHandler;

    @FXML
    private StackPane centerStackPane;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        setStyleForButtons();

        exec = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });

        snackBar = new JFXSnackbar(gridPane);
        undoSnackbar = new JFXSnackbar(gridPane);
        undoChangesHandler = (EventHandler) (Event event)
                -> 
                {
                    try
                    {
                        undoSnackbar.close();
                        snackBar.close();
                        volunteerModel.undoLastChanges();
                        undoSnackbar.show("          Slettede sidste udførte handling med success!", 3000);
                    } catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
        };

        volunteerModel = VolunteerModel.getInstance();
        lblUserHourInput.textProperty().bind(volunteerModel.getUserHourInput().asString());
        ArrayList<Node> elements = new ArrayList();
        masonryPane.setCellHeight(100);
        masonryPane.setCellWidth(110);
        masonryPane.getStyleClass().add("defaultBackgroundColor");
        guildControllers = new ArrayList();

        try
        {
            Person currentVolunteer = volunteerModel.getCurrentVolunteer();

            //List<Group> guildsAtLocation = VolunteerModel.getInstance().getCurrentLocation().getGroups();
            List<Group> guildsAtLocation = null;
            try
            {
                guildsAtLocation = Model.getInstance().getAMembersGuildsAtLocation(currentVolunteer.getId().get(), volunteerModel.getCurrentLocation().getId().get());
            } catch (SQLException ex)
            {
                Logger.getLogger(VolunteerMainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < guildsAtLocation.size(); i++)
            {
                elements.add(getNodeForGuild(guildsAtLocation.get(i)));

            }
        } catch (IOException ex)
        {
            Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        masonryPane.getChildren().setAll(elements);
        Platform.runLater(() -> scrollPane.requestLayout());
    }

    /**
     * Method for giving the controls on the volunteer view icons.
     */
    private void setStyleForButtons()
    {
        btnHourDown.getStyleClass().add("btnVolunteerView");
        btnExecuteHourInput.getStyleClass().add("btnVolunteerView");
        btnHourUp.getStyleClass().add("btnVolunteerView");

        Image imgExecute = new Image("mytime/gui/view/css/checked.png");
        Image imgUp = new Image("mytime/gui/view/css/up-arrow.png");
        Image imgDown = new Image("mytime/gui/view/css/down-arrow.png");

        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView(imgExecute);
        ImageView iv2 = new ImageView(imgUp);
        ImageView iv3 = new ImageView(imgDown);
        iv3.rotateProperty().setValue(180);

//
        iv1.setFitWidth(30);
        iv2.setFitWidth(30);
        iv3.setFitWidth(30);
        iv1.setFitHeight(50);
        iv2.setFitHeight(50);
        iv3.setFitHeight(50);
//        Circle clip = new Circle(iv1.getFitHeight() / 2, iv1.getFitWidth() / 3, 26);
//
//        iv1.setClip(clip);
//
        iv1.setPreserveRatio(true);
        iv2.setPreserveRatio(true);
        iv3.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv2.setSmooth(true);
        iv3.setSmooth(true);
        iv1.setCache(true);
        iv2.setCache(true);
        iv3.setCache(true);
        btnHourDown.setGraphic(iv3);

        btnExecuteHourInput.setGraphic(iv1);
        btnHourUp.setGraphic(iv2);

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
     *
     * @param event
     */
    @FXML
    private void handleHourInputDown(ActionEvent event)
    {
        volunteerModel.minusOneUpInUserHoursInput();
    }

    /**
     * Gets called when you add a hour up
     *
     * @param event
     */
    @FXML
    private void handleHourInputUp(ActionEvent event)
    {
        volunteerModel.addOneUpInUserHourInput();
    }

    /**
     * Gets called when you press on the execute button.
     *
     * @param event
     */
    @FXML
    private void handleExecuteHourInput(ActionEvent event)
    {
        int hours = volunteerModel.getUserHourInput().get();
        if (volunteerModel.getCurrentGuild() == null || hours == 0)
        {

            URL url = this.getClass().getResource("/mytime/gui/view/css/Style-red.css");

            if (url == null)
            {
            } else
            {
                root.getStylesheets().clear();

                String css = url.toExternalForm();

                root.getStylesheets().add(css);
                undoSnackbar.close();
                snackBar.close();
                snackBar.show("         Du har glemt enten at vælge laug, eller vælge time antal", 5000);

//            snackBar.setStyle(null);
            }

        } else
        {
            URL url2 = this.getClass().getResource("/mytime/gui/view/css/Style.css");
            String css2 = url2.toExternalForm();
            root.getStylesheets().clear();
            root.getStylesheets().add(css2);

            Task<Boolean> executeHourDocumentationTask = new Task<Boolean>()
            {
                @Override
                protected Boolean call() throws Exception
                {
                    try
                    {
                        volunteerModel.executeHourDocumentation();
                        return true;
                    } catch (SQLException ex)
                    {
                        //Alert no connection to database
                        snackBar.show("There was a problem reaching the database. Have you tried turning it on and off?", 5500);
                    }
                    return false;

                }

            };
            executeHourDocumentationTask.setOnSucceeded(e
                    -> 
                    {
                        volunteerModel.getUserHourInput().set(0);

                        if (executeHourDocumentationTask.getValue())
                        {
                            for (VolunteerOneGuildController guildController : guildControllers)
                            {
                                guildController.getBtnGuild().setStyle(null);
                                volunteerModel.setCurrentGuild(null);
                            }

                        }
            }
            );
            exec.execute(executeHourDocumentationTask);
            Platform.runLater(()
                    -> 
                    {
                        snackBar.close();
                        
                        undoSnackbar.show("          Dokumenterede " + hours + " time(r) ved laug " + volunteerModel.getCurrentGuild().getName().get() + " med success!", "Fortryd?", undoChangesHandler);
            });
        }

    }

    public List<VolunteerOneGuildController> getGuildControllers()
    {
        return guildControllers;
    }

    //dsadas
}
