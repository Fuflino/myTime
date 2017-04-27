/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mytime.be.Location;
import mytime.dal.DALFacade;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class MyTime extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
//        ResourceBundle bundle = ResourceBundle.getBundle("mytime.gui.UIResources", new Locale("da_DK"));
        Parent root = FXMLLoader.load(getClass().getResource("view/NewVolunteerView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        DALFacade facade = new DALFacade();
        List<Location> locations =facade.getAllLocations();
        for (Location location : locations)
        {
            System.out.println(location.getName().getValue());
        }
//        facade.createVolunteer("Manny", "1337h4X0R@gmail.com", "75181978");
//        facade.createGuild("Bo i vikinge hytte", "Bork");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
