/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXSpinner;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import mytime.be.Group;
import mytime.be.Location;
import mytime.be.Person;
import mytime.gui.model.Model;
import mytime.gui.model.VolunteerModel;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class LoginMainViewController implements Initializable, ChangeListener<String>
{

    @FXML
    private JFXMasonryPane masonryPane;
    @FXML
    private ScrollPane scrollPane;

    private VolunteerModel volunteerModel;

    private Executor exec, exec2, exec3;
    @FXML
    private JFXSpinner jfxSpinner;
    @FXML
    private Label lblGreeting;
    @FXML
    private HBox ButtomHbox;
    private Model model;

    @FXML
    private BorderPane borderpane;

    private BooleanProperty bp;


    /**
     * Fetches all the volunteers and loads it in the Tileview we have.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = Model.getInstance();
        exec = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });
        exec2 = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });
        exec3 = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });
//        spinner = new JFXSpinner();
//        masonryPane.getChildren().add(spinner);

        volunteerModel = VolunteerModel.getInstance();
        //volunteerModel.getBool().addListener(this);

        volunteerModel.getSearchQuery().addListener(this);

        masonryPane.setCellHeight(80);
        masonryPane.setCellWidth(150);
        volunteerModel.getIsTextFieldRdy().set(true);
        if (!volunteerModel.getLoginPersonNodes().isEmpty())
        {
            System.out.println("here");
            Location locationToLoadAgain = volunteerModel.getCurrentLocation();

            Task<Location> loadAgain = new Task<Location>()
            {
                @Override
                protected Location call() throws Exception
                {
                    try
                    {

                        return model.getSelectedLocation(locationToLoadAgain);
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;

                }

            };
            loadAgain.setOnSucceeded(p
                    -> 
                    {
                        Location lokation = loadAgain.getValue();
                        volunteerModel.setCurrentLocation(lokation);
                        System.out.println("lokation:" + lokation.hashCode());

                        loadAllPersonsIntoListAsNodes();

            });
            exec3.execute(loadAgain);

        } else
        {
            loadAllPersonsIntoListAsNodes();
        }
    }

    /**
     * Method for getting a volunteer as a node, so that we can load it in our
     * view as a JAVAFX component.
     *
     * @param volunteer
     * @return
     * @throws IOException
     */
    private Node getNodeForVolunteer(Person volunteer) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytime/gui/view/LoginOneVolunteer.fxml"));
        Node node = loader.load();
        Button button = (Button) node;
        LoginOneVolunteerController controller = loader.getController();
        controller.setVolunteer(volunteer);
        // load the image
        Image image = new Image(volunteer.getProfilePicture().get());

        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
//
        iv1.setFitWidth(70);
        iv1.setFitHeight(50);
        Circle clip = new Circle(iv1.getFitHeight() / 2, iv1.getFitWidth() / 3, 26);

        iv1.setClip(clip);
        iv1.setImage(image);

        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        button.setPrefHeight(40);
        button.setPrefWidth(150);
        button.getStyleClass().add("LoginVolunteerBtn");

        String name = volunteer.getName().get();

        String[] names = name.split(" ");

        if (names.length > 1)
        {
            button.setText(String.format("%s\n" + "%s", names[0], names[names.length - 1]));
        } else
        {
            button.setText(name);
        }

        button.setScaleX(0);
        button.setScaleY(0);
        button.setGraphic(iv1);
        button.setContentDisplay(ContentDisplay.LEFT);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(240), new KeyValue(button.scaleXProperty(), 1, Interpolator.EASE_BOTH),
                new KeyValue(button.scaleYProperty(), 1, Interpolator.EASE_BOTH)));
        animation.setDelay(Duration.millis(100 * 2 + (2000)));
        animation.play();

        return node;
    }

    /**
     * Loads all the persons with the same Location ID in as nodes in a list in
     * the Volunteer Model.
     */
    private void loadAllPersonsIntoListAsNodes()
    {
        int locationId = volunteerModel.getCurrentLocation().getId().get();
        Task<List<Person>> loadPersonAsGUIComponentsTask = new Task<List<Person>>()
        {
            @Override
            protected List<Person> call() throws Exception
            {
                List<Person> personsToLoadInAsNodes = new ArrayList<>();

                for (Group groups : volunteerModel.getCurrentLocation().getGroups())
                {
                    if (groups.getLocationId().get() == locationId)
                    {
                        for (Person personGroup : groups.getPersonlist())
                        {

                            if (!personsToLoadInAsNodes.stream().anyMatch(x -> Objects.equals(x.getId().get(), personGroup.getId().get())))
                            {
                                personsToLoadInAsNodes.add(personGroup);
                            }

                        }

                    }
                }

                return personsToLoadInAsNodes;
            }

        };
        loadPersonAsGUIComponentsTask.setOnSucceeded(e
                -> 
                {
                    if (loadPersonAsGUIComponentsTask.getValue() != null)
                    {
                        try
                        {
                            List<Person> perrsons = loadPersonAsGUIComponentsTask.get();

                            Task<Boolean> updateGUITask = new Task<Boolean>()
                            {
                                @Override
                                protected Boolean call() throws Exception
                                {
                                    volunteerModel.getLoginPersonNodes().clear();
                                    for (Person personsToLoadInAsNode : perrsons)
                                    {
                                        try
                                        {
                                            volunteerModel.getLoginPersonNodes().add(getNodeForVolunteer(personsToLoadInAsNode));
                                        } catch (IOException ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                    }

                                    return true;
                                }

                            };
                            updateGUITask.setOnSucceeded(j
                                    -> 
                                    {
                                        Task<Void> anotherTask = new Task<Void>()
                                        {
                                            @Override
                                            protected Void call() throws Exception
                                            {

//                                        
                                                Platform.runLater(()
                                                        -> 
                                                        {
                                                            ButtomHbox.getChildren().clear();
                                                            masonryPane.requestFocus();
                                                            masonryPane.requestLayout();
                                                            masonryPane.getChildren().setAll(volunteerModel.getLoginPersonNodes());
//                                                            masonryPane.getChildren().addAll(volunteerModel.getLoginPersonNodes());
                                                            masonryPane.requestLayout();
                                                            masonryPane.requestFocus();
                                                            scrollPane.requestLayout();
                                                }
                                                );

                                                return null;
                                            }

                                        };
                                        anotherTask.setOnSucceeded(o
                                                -> 
                                                {
                                                    Platform.runLater(() -> scrollPane.requestFocus());
                                                    volunteerModel.getIsTextFieldRdy().set(false);
                                        });
                                        exec3.execute(anotherTask);

                            });
                            exec2.execute(updateGUITask);
                        } catch (InterruptedException ex)
                        {
                            Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex)
                        {
                            Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        });
        exec.execute(loadPersonAsGUIComponentsTask);

    }

    /**
     * This method is called whenever the value of the search-area is changed.
     * The volunteers in the masonrypane is filtered with the new value of the
     * search-area
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
    {
        
            VolunteerModel vmodel = VolunteerModel.getInstance();
            //String query = textFieldFilter.getText();
            List<Node> filteredList = vmodel.filterList(newValue);
            vmodel.getLoginPersonNodesFiltered().clear();
            vmodel.getLoginPersonNodesFiltered().addAll(filteredList);
            System.out.println(newValue + " old: " + oldValue);

            masonryPane.getChildren().setAll(volunteerModel.getLoginPersonNodesFiltered());
        

    }

}
