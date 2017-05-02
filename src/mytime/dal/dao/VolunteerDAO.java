/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mytime.be.Group;
import mytime.be.Guild;
import mytime.be.Person;
import mytime.be.Volunteer;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerDAO
{

    /**
     *
     * @param c the connection to the database
     * @param group the guild whose members you want to get
     * @return a list of all volunteers in a given guild
     * @throws SQLException
     */
    public List<Person> getAllVolunteersInGuild(Connection con, Group group) throws SQLException
    {
        List<Person> volunteers = new ArrayList();

        //String sql = "SELECT v.name, v.email, v.phonenumber, v.description, v.id v.profilepicture FROM Volunteer v Join Works_In w ON v.id = w.volunteerid Join Guild g ON w.guildid = g.id WHERE g.id = ?";
        String sql = "SELECT Volunteer.name, email, phonenumber, Volunteer.description, Volunteer.id, profilepicture FROM Volunteer Join Works_In w ON id = w.volunteerid Join Guild g ON w.guildid = g.id WHERE g.id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, group.getId().get());
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phonenumber = rs.getString("phonenumber");
            String description = rs.getString("description");
            String profilepicture = rs.getString("profilepicture");
            if (profilepicture == null)
            {
                profilepicture = "https://i.imgsafe.org/3945ecd93f.png";
            }
            int id = rs.getInt("id");

            Person volunteer = new Volunteer(name, id, email, phonenumber, profilepicture);
            volunteers.add(volunteer);

        }

        return volunteers;
    }

    /**
     * Creates and adds a volunteer to the database.
     *
     * @param c
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException
     */
    public int createVolunteer(Connection con, String name, String email, String phonenumber) throws SQLException
    {
        System.out.println("Volunteer get");

        String sql = "INSERT INTO Volunteer(name, email, phonenumber)"
                + "VALUES(?, ?, ?)";

        String sql2 = "SELECT id FROM Volunteer WHERE name = ? AND email = ? AND phonenumber = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phonenumber);
        ps.executeUpdate();

        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setString(1, name);
        ps2.setString(2, email);
        ps2.setString(3, phonenumber);
        ResultSet rs2 = ps2.executeQuery();
        if (rs2.next())
        {
            return rs2.getInt("id");
        } else
        {
            return -1;
        }

    }

    /**
     * Assign a volunteer to a guild
     *
     * @param c
     * @param volunteerid
     * @param guildid
     * @throws SQLException
     */
    public void addVolunteerToGuild(Connection con, int volunteerid, int guildid) throws SQLException
    {
        String sql = "INSERT INTO Works_In(volunteerid, guildid) VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, volunteerid);
        ps.setInt(2, guildid);
        ps.executeUpdate();
    }

    /**
     * Adds a hour-transaction to the database. example: (Date: 24-06-2017,
     * Hours added: 6, to guild(id): 2, By volunteer(id): 1)
     *
     * @param vol
     * @param guild
     * @param hours
     */
    public void addHoursForVolunteer(Connection con, int volunteerid, int guildid, int hours) throws SQLException
    {
        String sql = "INSERT INTO HoursTransaction(hours, volunteerid, guildid, date)"
                + "VALUES(?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, hours);
        ps.setInt(2, volunteerid);
        ps.setInt(3, guildid);
        LocalDate ld = LocalDate.now();
        Date date = java.sql.Date.valueOf(ld);
        ps.setDate(4, (java.sql.Date) date);
        ps.executeUpdate();
    }

    /**
     *
     * @param c
     * @param volunteerid
     * @return the amount of hours a volunteer has worked in total
     */
    public int getTotalHoursOneVolunteer(Connection con, int volunteerid) throws SQLException
    {

        String columnname = "hours";
        String sql = "SELECT SUM(Hours) AS " + columnname + " FROM HoursTransaction WHERE volunteerid = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, volunteerid);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            return rs.getInt(columnname);
        } else
        {
            return -1;
        }
    }

    /**
     * @param c
     * @param volunteerid
     * @param guildid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException
     */
    public int getHoursWorkedOnOneGuildByVolunteer(Connection con, int volunteerid, int guildid) throws SQLException
    {
        String columnname = "hours";
        String sql = "SELECT SUM(Hours) AS " + columnname + " FROM HoursTransaction WHERE volunteerid = ? AND guildid = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, volunteerid);
        ps.setInt(2, guildid);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            return rs.getInt(columnname);
        } else
        {
            System.out.println("Something went wrong");
            return -1;
        }
    }

}
