/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import mytime.be.Location;
import mytime.bll.BLLManager;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class Model
{

    private static Model INSTANCE;
    private BLLManager bllMgr;

    private Model()
    {
        try
        {
            bllMgr = new BLLManager();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static Model getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Model();
        }
        return INSTANCE;

    }
    /**
     * Gets all the locations without the groups and persons
     * @return
     * @throws SQLException 
     */
    public List<Location> getAllLocations() throws SQLException
    {
        return bllMgr.getAllLocations();
    }
    /**
     * Gets a location with groups and persons.
     * @param id
     * @return
     * @throws SQLServerException 
     */
    public List<Location> getSelectedLocation(IntegerProperty id) throws SQLServerException
    {
        return bllMgr.getSelectedLocation(id.get());
    }

}
