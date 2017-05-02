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
import java.util.ArrayList;
import java.util.List;
import mytime.be.Group;
import mytime.be.Guild;
import mytime.be.Location;

/**
 *
 * @author Bruger
 */
public class GuildDAO
{

    /**
     * Creates and adds a new guild to the database
     *
     * @param c
     * @param name
     * @param location
     * @throws SQLException
     */
    public void createGuild(Connection con, String name, String location) throws SQLException
    {
        System.out.println("Guild: ( " + name + ") created!");

        String sql = "INSERT INTO Guild(name, location)"
                + "VALUES(?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, location);
        ps.executeUpdate();

    }

    /**
     * Returns a list of all locations stored in database
     *
     * @return
     */
    public List<Location> getAllLocations(Connection con) throws SQLException
    {
        List<Location> locations = new ArrayList();

        String sql = "SELECT *FROM Location";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            Location location = new Location(name, id);
            locations.add(location);
        }
        return locations;

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
    public List<Group> getAMembersGuildsAtLocation(Connection con, int volunteerid, int locationid) throws SQLException
    {
        List<Group> guildList = new ArrayList();

        String sql = "SELECT Guild.id, Guild.name, Guild.description, Guild.locationid, Guild.icon FROM Guild Join Works_In w on Guild.id = w.guildid Join Volunteer v on v.id = w.volunteerid WHERE v.id = ? AND Guild.locationid = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, volunteerid);
        ps.setInt(2, locationid);

        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String icon = rs.getString("icon");
            if (icon == null)
            {
                icon = "mytime/gui/view/css/notebook.png";
            }

            Group guild = new Guild(name, id, locationid, description, icon);
            guildList.add(guild);
        }

        return guildList;
    }
    
    /**
     * @param con
     * @param volunteerid
     * @return a list of all the groups a person is assigned to
     * @throws SQLException 
     */
    public List<Group> getAllGroupsForPerson(Connection con, int volunteerid) throws SQLException
    {
        List<Group> guildList = new ArrayList();
        
        String sql = "SELECT Guild.id, Guild.name, Guild.description, Guild.locationid, Guild.icon FROM Guild Join Works_In w on Guild.id = w.guildid Join Volunteer v on v.id = w.volunteerid WHERE v.id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, volunteerid);

        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String icon = rs.getString("icon");
            int locationid = rs.getInt("locationid");
            if (icon == null)
            {
                icon = "mytime/gui/view/css/notebook.png";
            }

            Group guild = new Guild(name, id, locationid, description, icon);
            guildList.add(guild);
        }

        return guildList;
    }

    /**
     * @param c
     * @param volunteerid
     * @return a list of integers which represent the guild-id's of the
     * available guilds for a volunteer
     * @throws SQLException
     */
    public List<Integer> getArrayOfAvailableGuildsForVolunteer(Connection con, int volunteerid) throws SQLException
    {
        List<Integer> allGuilds = new ArrayList();

        String sql = "SELECT id FROM Guild";
        String sql2 = "SELECT guildid FROM Works_In WHERE volunteerid = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            allGuilds.add(rs.getInt("id"));
        }

        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setInt(1, volunteerid);
        ResultSet rs2 = ps2.executeQuery();

        while (rs2.next())
        {
            int guildid = rs2.getInt("guildid");
            System.out.println("FFDSFG id: " + guildid);
            for (int i = 0; i < allGuilds.size(); i++)
            {
                if (allGuilds.get(i) == guildid)
                {
                    allGuilds.remove(i);
                    i--;
                }
            }
        }
        return allGuilds;

    }

}
