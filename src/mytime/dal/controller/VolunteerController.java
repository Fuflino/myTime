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
     * Not yet implemented
     * @return
     * @throws SQLException 
     */
    @Override
    public Volunteer getVolunteer() throws SQLException
    {
        return dao.getVolunteer(cm.getConnection());
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
    
    
    
    
    
    
}
