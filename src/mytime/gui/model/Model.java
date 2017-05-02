/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import mytime.be.Group;
import mytime.be.Location;
import mytime.bll.BLLManager;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class Model
{

    private static Model INSTANCE;
    private BLLManager bllMgr;

    private Model()
    {
        try
        {
            bllMgr = new BLLManager();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static Model getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Model();
        }
        return INSTANCE;

    }

    /**
     * Gets all the locations without the groups and persons
     *
     * @return
     * @throws SQLException
     */
    public List<Location> getAllLocations() throws SQLException
    {
        return bllMgr.getAllLocations();
    }

    /**
     * Gets a location with groups and persons.
     *
     * @param id
     * @return
     * @throws SQLServerException
     */
    public Location getSelectedLocation(Location location) throws SQLException
    {
        return bllMgr.getSelectedLocation(location);
    }

    /**
     * Documents volunteer-hours in the database. Method is called to store
     * hours worked by given volunteer-id at given guild-id. The date when this
     * method is called is also saved in the database
     *
     * @param volunteerid
     * @param guildid
     * @param hours
     * @throws SQLException
     */
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException
    {
        bllMgr.addHoursForVolunteer(volunteerid, guildid, hours);
    }

    /**
     * Returns a list of guilds at a certain location which the given volunteer
     * is a member of
     *
     * @param c
     * @param volunteerid
     * @param locationid
     * @return
     */
    public List<Group> getAMembersGuildsAtLocation(int volunteerid, int locationid) throws SQLException
    {
        return bllMgr.getAMembersGuildsAtLocation(volunteerid, locationid);
    }

    public BLLManager getBllManager()
    {
        return bllMgr;
    }
    /**
     * Gets a List of all the groups a person document hours into.
     * @return
     * @throws SQLException 
     */
    public List<Group> getAllGroupsForPerson() throws SQLException
    {
        return null;
    }

}
