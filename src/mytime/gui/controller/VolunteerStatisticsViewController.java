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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VolunteerStatisticsViewController implements Initializable
{

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBoxGuilds;
    private VolunteerModel volunteerModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        volunteerModel = VolunteerModel.getInstance();
        ArrayList<Node> elements = new ArrayList();

//        try
//        {
//            Person currentVolunteer = volunteerModel.getCurrentVolunteer();
//
//            //List<Group> guildsAtLocation = VolunteerModel.getInstance().getCurrentLocation().getGroups();
//            List<Group> guildsAtLocation = null;
//            try
//            {
//                guildsAtLocation = Model.getInstance().getAMembersGuildsAtLocation(currentVolunteer.getId().get(), volunteerModel.getCurrentLocation().getId().get());
//            } catch (SQLException ex)
//            {
//                Logger.getLogger(VolunteerMainViewController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            for (int i = 0; i < guildsAtLocation.size(); i++)
//            {
//                Group guild = guildsAtLocation.get(i);
//                elements.add(getNodeForGuild(guild.getIconUrl(), guild.getName(), 00));
//
//            }
//        } catch (IOException ex)
//        {
//            Logger.getLogger(LoginMainViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

    private void loadGuildStatisticsInAsNodes()
    {

    }

}
