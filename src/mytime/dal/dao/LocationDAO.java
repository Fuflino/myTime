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
import mytime.be.Person;
import mytime.be.Volunteer;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class LocationDAO
{

    /**
     * SQL for getting a location with groups and persons.
     *
     * @param con
     * @param id
     * @return
     */
    public Location getLocationById(Connection con, Location location) throws SQLException
    {
        List<Group> guildlist = new ArrayList();
        Location returnLocation = location;

        String sql = "SELECT * FROM Guild WHERE locationid = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, location.getId().get());
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int locationid = rs.getInt("locationid");
            String icon = rs.getString("icon");
            if (icon == null)
            {
                icon = "mytime/gui/view/css/notebook.png";
            }
            Group guild = new Guild(name, id, locationid, description, icon);
            guildlist.add(guild);
        }
        System.out.println(guildlist.size());
        for (Group group : guildlist)
        {
            List<Person> personlist = new ArrayList();

            //String sql = "SELECT v.name, v.email, v.phonenumber, v.description, v.id v.profilepicture FROM Volunteer v Join Works_In w ON v.id = w.volunteerid Join Guild g ON w.guildid = g.id WHERE g.id = ?";
            String sql2 = "SELECT Volunteer.name, email, phonenumber, Volunteer.description, Volunteer.id, profilepicture FROM Volunteer Join Works_In w ON id = w.volunteerid Join Guild g ON w.guildid = g.id WHERE g.id = ?";

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, group.getId().get());
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next())
            {
                String name = rs2.getString("name");
                String email = rs2.getString("email");
                String phonenumber = rs2.getString("phonenumber");
                String description = rs2.getString("description");
                String profilepicture = rs2.getString("profilepicture");
                if (profilepicture == null)
                {
                    profilepicture = "https://i.imgsafe.org/3945ecd93f.png";
                }
                int id = rs2.getInt("id");

                Person volunteer = new Volunteer(name, id, email, phonenumber, profilepicture);
                personlist.add(volunteer);

            }
            group.setPersonlist(personlist);
        }

        location.setGroups(guildlist);
        return returnLocation;
    }

//    /**
//     * @param c connection. get from connection manager
//     * @param location the given location
//     * @return a list of all guilds at a given location
//     * @throws SQLException 
//     */
//    public List<Group> getAllGuildsAtLocation(Connection c, Location location) throws SQLException
//    {
//        
//            
//    }
}
