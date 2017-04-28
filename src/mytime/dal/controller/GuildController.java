/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import mytime.be.Group;
import mytime.be.Location;
import mytime.dal.dao.ConnectionManager;
import mytime.dal.dao.GuildDAO;
import mytime.dal.dao.VolunteerDAO;

/**
 *
 * @author Bruger
 */
public class GuildController implements IGuild
{
    
    private ConnectionManager cm;
    private GuildDAO dao;
    
    
    public GuildController() throws IOException
    {
        dao = new GuildDAO();
        cm = new ConnectionManager();
    }
    

    /**
     * Creates and adds a guild to the database
     * @param name
     * @param location
     * @throws SQLException 
     */
    @Override
    public void createGuild(String name, String location) throws SQLException
    {
        dao.createGuild(cm.getConnection(), name, location);
    }
    
    /**
     * Returns a list of all locations stored in database
     * @return 
     */
    @Override
    public List<Location> getAllLocations() throws SQLException
    {
        return dao.getAllLocations(cm.getConnection());
    }
    
//    /**
//     * Gets all guilds at a given location
//     * @param location
//     * @return
//     * @throws SQLException 
//     */
//    @Override
//    public List<Group> getAllGuildsAtLocation(Location location) throws SQLException
//    {
//        return dao.getAllGuildsAtLocation(cm.getConnection(), location);
//    }

    /**
     * Returns a list of guilds at a certain location which the given volunteer is a member of
     * @param c
     * @param volunteerid
     * @param locationid
     * @return 
     */
    @Override
    public List<Group> getAMembersGuildsAtLocation(int volunteerid, int locationid) throws SQLException
    {
        return dao.getAMembersGuildsAtLocation(cm.getConnection(), volunteerid, locationid);
    }
    
    
}
