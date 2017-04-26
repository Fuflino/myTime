/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import mytime.be.Volunteer;
import mytime.dal.controller.IVolunteer;
import mytime.dal.controller.VolunteerController;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class DALFacade
{
    private final IVolunteer volunteerController;

    public DALFacade() throws IOException
    {
        volunteerController = new VolunteerController();
    }

    /**
     * Gets a volunteer by id (Not implemented)
     * @return
     * @throws SQLException 
     */
    public Volunteer getVolunteer() throws SQLException
    {
        return volunteerController.getVolunteer();
    }
    
    /**
     * Creates and adds volunteer to the database
     * @param name
     * @param email
     * @param phonenumber
     * @throws SQLException 
     */
    public void createVolunteer(String name, String email, String phonenumber) throws SQLException
    {
    }
    
    
    
    
    
}
