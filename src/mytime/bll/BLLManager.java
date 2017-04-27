/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mytime.be.Location;
import mytime.dal.DALFacade;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class BLLManager
{

    private final DALFacade dalFacade;

    public BLLManager() throws IOException
    {
        dalFacade = DALFacade.getInstance();
    }
    /**
     * Gets all the locations without the groups and persons
     * @return
     * @throws SQLException 
     */
    public List<Location> getAllLocations() throws SQLException
    {
        return dalFacade.getAllLocations();
    }
    /**
     * Gets a Location with groups and persons by id
     * @param locationId
     * @return
     * @throws SQLServerException 
     */
    public Location getSelectedLocation(Location location) throws SQLException
    {
        return dalFacade.getSelectedLocation(location);
    }

}
