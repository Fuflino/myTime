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
 * @author Stefan-VpcEB3J1E
 */
public class LocationDAO
{
    
    /**
     * SQL for getting a location with groups and persons.
     * @param con
     * @param id
     * @return 
     */
    public Location getLocationById(Connection c, Location location) throws SQLException
    {
        List<Group> guildlist = new ArrayList();
        Location returnLocation = location;
        
        try(Connection con = c)
        {
            String sql = "SELECT * FROM Guild WHERE locationid = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, location.getId().get());
            ResultSet rs = ps.executeQuery();
             
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int locationid = rs.getInt("locationid");
                Group guild = new Guild(name, id, locationid, description);
                guildlist.add(guild);
            }
                    
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
