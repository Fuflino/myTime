/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.model;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import mytime.be.Location;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerModel
{

    private static VolunteerModel INSTANCE;
    private IntegerProperty userHourInput;
        private Location currentLocation;

    /**
     * Part of the singleton pattern
     */
    private VolunteerModel()
    {
        userHourInput = new SimpleIntegerProperty(0);
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
    public void executeHourDocumentation()
    {
        int hoursToDocumentate = userHourInput.get();
        userHourInput.set(0);

    }
    /**
     * Sets the location that you choose in the chooselokation view.
     * @param course 
     */
    public void setCurrentLocation(Location course)
    {
        this.currentLocation = course;
    }

}
