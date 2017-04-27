/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mytime.be.Location;
import mytime.be.Volunteer;
import mytime.dal.controller.GuildController;
import mytime.dal.controller.IGuild;
import mytime.dal.controller.ILocation;
import mytime.dal.controller.IVolunteer;
import mytime.dal.controller.LocationController;
import mytime.dal.controller.VolunteerController;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class DALFacade
{

    private final IVolunteer volunteerController;
    private final IGuild guildController;
    private final ILocation locationController;
    private static DALFacade INSTANCE;

    private DALFacade() throws IOException
    {
        volunteerController = new VolunteerController();
        guildController = new GuildController();
        locationController = new LocationController();
    }

    public static DALFacade getInstance() throws IOException
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DALFacade();
        }
        return INSTANCE;
    }

    /**
     * Gets a volunteer by id (Not implemented)
     *
     * @return
     * @throws SQLException
     */
    public List<Volunteer> getVolunteer() throws SQLException
    {
        return volunteerController.getAllVolunteers();
    }

    /**
     * Creates and adds volunteer to the database
     *
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException
     */
    public void createVolunteer(String name, String email, String phonenumber) throws SQLException
    {
        volunteerController.createVolunteer(name, email, phonenumber);
    }

    /**
     * Creates and adds guild to the database
     *
     * @param name
     * @param location
     * @throws SQLException
     */
    public void createGuild(String name, String location) throws SQLException
    {
        guildController.createGuild(name, location);
    }

    /**
     * Returns a list of all locations stored in database
     *
     * @return
     */
    public List<Location> getAllLocations() throws SQLException
    {
        return guildController.getAllLocations();
    }
    /**
     * Gets the location with groups and persons by id
     * @param locationId
     * @return
     * @throws SQLServerException 
     */
    public List<Location> getSelectedLocation(int locationId) throws SQLServerException
    {
       return locationController.getSelectedLocation(locationId);
    }

}
