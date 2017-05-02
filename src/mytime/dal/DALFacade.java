/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import mytime.be.Group;
import mytime.be.Location;
import mytime.be.Person;
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
<<<<<<< HEAD
     * Gets all volunteers in a given group(guild)
=======
     * Gets a volunteer by id (Not implemented)
     *
>>>>>>> origin/master
     * @return
     * @throws SQLException
     */
    public List<Person> getAllVolunteersInGuild(Group group) throws SQLException
    {
        return volunteerController.getAllVolunteersInGuild(group);
    }

    /**
     * Creates and adds volunteer to the database
     *
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException
     */
    public int createVolunteer(String name, String email, String phonenumber) throws SQLException
    {
        return volunteerController.createVolunteer(name, email, phonenumber);
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
    
//    /**
//     * Returns all guilds at a given location
//     * @param location
//     * @return
//     * @throws SQLException 
//     */
//    public List<Group> getAllGuildsAtLocation(Location location) throws SQLException
//    {
//        return guildController.getAllGuildsAtLocation(location);
//    }

    /**
     * Gets the location with groups and persons by id
     * @param locationId
     * @return
     * @throws SQLServerException 
     */
    public Location getSelectedLocation(Location location) throws SQLException
    {
       return locationController.getSelectedLocation(location);
    }
    
    /**
     * Documents volunteer-hours in the database. Method is called to store 
     * hours worked by given volunteer-id at given guild-id. The date when 
     * this method is called is also saved in the database
     * @param volunteerid
     * @param guildid
     * @param hours
     * @throws SQLException 
     */
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException
    {
        volunteerController.addHoursForVolunteer(volunteerid, guildid, hours);
    }

    
    /**
     * Returns a list of guilds at a certain location which the given volunteer is a member of
     * @param c
     * @param volunteerid
     * @param locationid
     * @return 
     */
    public List<Group> getAMembersGuildsAtLocation(int volunteerid, int locationid) throws SQLException
    {
        return guildController.getAMembersGuildsAtLocation(volunteerid, locationid);
    }
    
    /**
     * @param volunteerid
     * @return the amount of hours one volunteer has worked in total, as an int. The volunteer is defined by id
     * @throws SQLException 
     */
    public int getTotalHoursOneVolunteer(int volunteerid) throws SQLException
    {
        return volunteerController.getTotalHoursOneVolunteer(volunteerid);
    }
    
    /**
     * @param volunteerid
     * @param guildid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException 
     */
    public int getHoursWorkedOnOneGuildByVolunteer(int volunteerid, int guildid) throws SQLException
    {
        return volunteerController.getHoursWorkedOnOneGuildByVolunteer(volunteerid, guildid);
    }
    
    /**
     * 
     * @param volunteerid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException 
     */
    public List<Integer> getArrayOfAvailableGuildsForVolunteer(int volunteerid) throws SQLException
    {
        return guildController.getArrayOfAvailableGuildsForVolunteer(volunteerid);
    }
    
    /**
     * Assign a volunteer to a guild
     * @param volunteerid
     * @param guildid
     * @throws SQLException 
     */
    public void addVolunteerToGuild(int volunteerid, int guildid) throws SQLException
    {
        volunteerController.addVolunteerToGuild(volunteerid, guildid);
    }
    
    /**
     * @param volunteerid
     * @return a list of all the groups a person is assigned to
     * @throws SQLException 
     */
    public List<Group> getAllGroupsForPerson(int volunteerid) throws SQLException
    {
        return guildController.getAllGroupsForPerson(volunteerid);
        
    }
}
