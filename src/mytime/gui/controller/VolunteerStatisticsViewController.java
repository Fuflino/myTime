/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.controller;

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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import mytime.be.Group;
import mytime.be.Person;
import mytime.gui.model.Model;
import mytime.gui.model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerStatisticsViewController implements Initializable, ChangeListener<Boolean>
{
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBoxGuilds;
    private VolunteerModel volunteerModel;
    private Executor exec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        exec = Executors.newCachedThreadPool(runnable
                -> 
                {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
        });
        volunteerModel = VolunteerModel.getInstance();
        loadGuildStatisticsInAsNodes();
        volunteerModel.getJustExecuted().addListener(this);

//        for (int i = 0; i < 5; i++)
//        {
//            try
//            {
//                vBoxGuilds.getChildren().add(getNodeForGuild("mytime/gui/view/css/notebook.png", "Bork TekstilLaug", 55));
//            } catch (IOException ex)
//            {
//            }
//
//        }
    }

    /**
     * Method for getting a guild as a node, so that we can load it in our view
     * as a JAVAFX component.
     *
     * @param volunteer
     * @return
     * @throws IOException
     */
    private Node getNodeForGuild(String iconUrl, String guildName, int guildHours) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytime/gui/view/VolunteerStatisticsOneGuild.fxml"));
        Node node = loader.load();
        VolunteerStatisticsOneGuildController controller = loader.getController();
        controller.setGuildHours(guildHours);
        controller.setGuildIcon(iconUrl);
        controller.setGuildName(guildName);

//        Timeline animation = new Timeline(new KeyFrame(Duration.millis(240), new KeyValue(button.scaleXProperty(), 1, Interpolator.EASE_BOTH),
//                new KeyValue(button.scaleYProperty(), 1, Interpolator.EASE_BOTH)));
//        animation.setDelay(Duration.millis(100 * 2 + (2000)));
//        animation.play();
        return node;
    }
    /**
     * Runs a thread and load the guild statistics in as Nodes.
     */
    private void loadGuildStatisticsInAsNodes()
    {
        ArrayList<Node> elements = new ArrayList();
        Task<List<Node>> getNodes = new Task<List<Node>>()
        {
            @Override
            protected List<Node> call() throws Exception
            {
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
                        Group guild = guildsAtLocation.get(i);
                        try
                        {
                            int hours = volunteerModel.getHoursWorkedOnOneGuildByVolunteer(guild.getId().get());
                            elements.add(getNodeForGuild(guild.getIconUrl().get(), guild.getName().get(), hours));
                        } catch (SQLException ex)
                        {
                            Logger.getLogger(VolunteerStatisticsViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                } catch (IOException ex)
                {
                    Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return elements;
            }
            
        };
        getNodes.setOnSucceeded(e
                -> 
                {
                    if (getNodes.getValue() != null)
                    {
                        Platform.runLater(() -> vBoxGuilds.getChildren().addAll(getNodes.getValue()));
                    }
        });
        exec.execute(getNodes);
        
    }
    /**
     * Gets called when the booleanProperty on the VolunteerModel get sets to True.
     * Loads in the new statistics, after you document hours on a guild.
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    {
        if (newValue)
        {
            Platform.runLater(()
                    -> 
                    {
                        vBoxGuilds.getChildren().clear();
//                        vBoxGuilds.getChildren().addAll(loadGuildStatisticsInAsNodes());
                        loadGuildStatisticsInAsNodes();
                        volunteerModel.getJustExecuted().set(false);
            });
            
        }
    }
    
}
