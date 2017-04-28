/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.model;

import java.sql.SQLException;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import mytime.be.Group;
import mytime.be.Location;
import mytime.be.Person;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerModel
{

    private static VolunteerModel INSTANCE;
    private IntegerProperty userHourInput;
    private Location currentLocation;
    private Person currentVolunteer;
    private Group currentGuild;
    private List<Node> loginPersonNodes;
    

    /**
     * Part of the singleton pattern
     */
    private VolunteerModel()
    {
        userHourInput = new SimpleIntegerProperty(0);
        loginPersonNodes = new ArrayList<>();
    }
    
    

    /**
     * A part of the singleton pattern
     *
     * @return An instance of a VolunteerModel
     */
    public static VolunteerModel getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new VolunteerModel();
        }
        return INSTANCE;
    }

    /**
     *
     * @return The IntegerProperty of the hours that the user have added in the
     * GUI
     */
    public IntegerProperty getUserHourInput()
    {
        return userHourInput;
    }

    /**
     * Adds one up in user hour input
     */
    public void addOneUpInUserHourInput()
    {
        userHourInput.set(userHourInput.get() + 1);
    }

    /**
     * Substracts one from the user hour input
     */
    public void minusOneUpInUserHoursInput()
    {
        if (!userHourInput.isEqualTo(0).get())
        {
            userHourInput.set(userHourInput.get() - 1);
        }
    }

    /**
     * Documents the hours into the database
     *
     */
    public void executeHourDocumentation() throws SQLException
    {
        int hoursToDocumentate = userHourInput.get();
        //Model.getInstance().addHoursForVolunteer(currentVolunteer.getId().get(), currentGuild.getId().get(), hoursToDocumentate);
        userHourInput.set(0);

    }

    /**
     * Sets the location that you choose in the chooselokation view.
     *
     * @param course
     */
    public void setCurrentLocation(Location course)
    {
        this.currentLocation = course;
        
    }
    /**
     * A list of all the persons in the LoginMainView wrapped as nodes.
     * @return 
     */
    public List<Node> getLoginPersonNodes()
    {
        return loginPersonNodes;
    }
    /**
     * Gets the current location.
     * @return 
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    /**
     * Sets the current active volunteer.
     * @param volunteer 
     */
    public void setCurrentVolunteer(Person volunteer)
    {
        currentVolunteer = volunteer;
    }
    /**
     * Gets the guild that the user have choosen
     * @return 
     */
    public Group getCurrentGuild()
    {
        return currentGuild;
    }
    
    

    
    
}
