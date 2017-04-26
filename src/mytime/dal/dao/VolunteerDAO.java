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
import mytime.be.Guild;
import mytime.be.Volunteer;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerDAO
{

    public Volunteer getVolunteer(Connection c) throws SQLException
    {
        
        return null;
    }
    
    /**
     * Creates and adds a volunteer to the database. 
     * @param c
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException 
     */
    public void createVolunteer(Connection c, String name, String email, String phonenumber) throws SQLException
    {
        System.out.println("Volunteer get");

        try (Connection con = c)
        {
            String sql = "INSERT INTO Volunteer(name, email, phonenumber)"
                    + "VALUES(?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phonenumber);
            ps.executeUpdate();

        }

    }
    
    /**
     * Adds a hour-transaction to the database. example: (Date: 24-06-2017, Hours added: 6, to guild(id): 2, By volunteer(id): 1) 
     * @param vol
     * @param guild
     * @param hours 
     */
    public void addHoursForVolunteer(Volunteer vol, Guild guild, int hours)
    {
        
    }
}
