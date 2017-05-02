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
import mytime.be.Location;
import mytime.dal.dao.ConnectionManager;
import mytime.dal.dao.LocationDAO;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class LocationController implements ILocation
{
        private ConnectionManager cm;
        private LocationDAO locationDAO;

    public LocationController() throws IOException
    {
        cm = new ConnectionManager();
        locationDAO = new LocationDAO();
    }
        
        
    /**
     * Method to get a Location by Id.
     * @param location
     * @return
     * @throws SQLServerException 
     */
    @Override
    public Location getSelectedLocation(Location location) throws SQLException
    {
        try(Connection con = cm.getConnection())
        {
            return locationDAO.getLocationById(con, location);
        }
        
    }

}
