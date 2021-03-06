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

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public interface IVolunteer
{
    public List<Person> getAllVolunteersInGuild(Group group) throws SQLException;
    
    public int createVolunteer(String name, String email, String phonenumber) throws SQLException;
    
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException;
    
    public int getTotalHoursOneVolunteer(int volunteerid) throws SQLException;
    
    public int getHoursWorkedOnOneGuildByVolunteer(int volunteerid, int guildid) throws SQLException;
    
    public void addVolunteerToGuild(int volunteerid, int guildid) throws SQLException;
    
    public void undoLastDocumentedHours() throws SQLException;
            
}
