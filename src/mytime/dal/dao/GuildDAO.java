/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bruger
 */
public class GuildDAO
{
    /**
     * Creates and adds a new guild to the database
     * @param c
     * @param name
     * @param location
     * @throws SQLException 
     */
    public void createGuild(Connection c, String name, String location) throws SQLException
    {
        System.out.println("Guild: ( " + name + ") created!");

        try (Connection con = c)
        {
            String sql = "INSERT INTO Guild(name, location)"
                    + "VALUES(?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, location);
            ps.executeUpdate();

        }

    }
    
}
