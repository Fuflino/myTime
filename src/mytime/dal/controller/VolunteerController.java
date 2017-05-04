/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import mytime.be.Group;
import mytime.be.Person;
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
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Person> getAllVolunteersInGuild(Group group) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            return dao.getAllVolunteersInGuild(con, group);
        }

    }

    /**
     * Creates and adds a volunteer to the database
     *
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException
     */
    @Override
    public int createVolunteer(String name, String email, String phonenumber) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            return dao.createVolunteer(con, name, email, phonenumber);
        }

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
    @Override
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {

            dao.addHoursForVolunteer(con, volunteerid, guildid, hours);

        }
    }

    /**
     *
     * @param volunteerid
     * @return the amount of hours one volunteer has worked in total. The
     * volunteer is defined by id
     * @throws SQLException
     */
    @Override
    public int getTotalHoursOneVolunteer(int volunteerid) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            return dao.getTotalHoursOneVolunteer(con, volunteerid);
        }

    }

    /**
     * @param volunteerid
     * @param guildid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException
     */
    @Override
    public int getHoursWorkedOnOneGuildByVolunteer(int volunteerid, int guildid) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            return dao.getHoursWorkedOnOneGuildByVolunteer(con, volunteerid, guildid);
        }

    }

    /**
     * Assign a volunteer to a guild
     *
     * @param volunteerid
     * @param guildid
     * @throws SQLException
     */
    @Override
    public void addVolunteerToGuild(int volunteerid, int guildid) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            dao.addVolunteerToGuild(cm.getConnection(), volunteerid, guildid);
        }

    }

    /**
     * Undo the lastest documented hours.
     *
     * @throws SQLException
     */
    @Override
    public void undoLastDocumentedHours() throws SQLException
    {
        try (Connection c = cm.getConnection())
        {
           dao.undoLastChange(c);
        }
    }

}
