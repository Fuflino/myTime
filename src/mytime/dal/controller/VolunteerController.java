/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import mytime.be.Group;
import mytime.be.Person;
import mytime.be.Volunteer;
import mytime.dal.dao.ConnectionManager;
import mytime.dal.dao.VolunteerDAO;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerController implements IVolunteer
{
    private VolunteerDAO dao;
    private ConnectionManager cm;
    
    
    public VolunteerController() throws IOException
    {
        dao = new VolunteerDAO();
        cm = new ConnectionManager();
    }
    
    /**
     * Get all volunteers in a given group(guild)
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Person> getAllVolunteersInGuild(Group group) throws SQLException
    {
        return dao.getAllVolunteersInGuild(cm.getConnection(), group);
    }
    
    /**
     * Creates and adds a volunteer to the database
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException 
     */
    @Override
    public void createVolunteer(String name, String email, String phonenumber) throws SQLException
    {
        dao.createVolunteer(cm.getConnection(), name, email, phonenumber);
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
    @Override
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException
    {
        dao.addHoursForVolunteer(cm.getConnection(), volunteerid, guildid, hours);
    }
    
    
    
    
}
