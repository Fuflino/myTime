/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mytime.be.Group;
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
     *
     * @return
     * @throws SQLException
     */
    public List<Location> getAllLocations() throws SQLException
    {
        return dalFacade.getAllLocations();
    }

    /**
     * Gets a Location with groups and persons by id
     *
     * @param locationId
     * @return
     * @throws SQLServerException
     */
    public Location getSelectedLocation(Location location) throws SQLException
    {
        return dalFacade.getSelectedLocation(location);
    }

    /**
     * Documents volunteer-hours in the database. Method is called to store
     * hours worked by given volunteer-id at given guild-id. The date when this
     * method is called is also saved in the database
     *
     * @param volunteerid
     * @param guildid
     * @param hours
     * @throws SQLException
     */
    public void addHoursForVolunteer(int volunteerid, int guildid, int hours) throws SQLException
    {
        dalFacade.addHoursForVolunteer(volunteerid, guildid, hours);
    }

    /**
     * Returns a list of guilds at a certain location which the given volunteer
     * is a member of
     *
     * @param c
     * @param volunteerid
     * @param locationid
     * @return
     */
    public List<Group> getAMembersGuildsAtLocation(int volunteerid, int locationid) throws SQLException
    {
        return dalFacade.getAMembersGuildsAtLocation(volunteerid, locationid);
    }

    /**
     * @param volunteerid
     * @return the amount of hours one volunteer has worked in total, as an int.
     * The volunteer is defined by id
     * @throws SQLException
     */
    public int getTotalHoursOneVolunteer(int volunteerid) throws SQLException
    {
        return dalFacade.getTotalHoursOneVolunteer(volunteerid);
    }

    /**
     * @param volunteerid
     * @param guildid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException
     */
    public int getHoursWorkedOnOneGuildByVolunteer(int volunteerid, int guildid) throws SQLException
    {
        return dalFacade.getHoursWorkedOnOneGuildByVolunteer(volunteerid, guildid);
    }

    /**
     * @param volunteerid
     * @return a list of all the groups a person is assigned to
     * @throws SQLException
     */
    public List<Group> getAllGroupsForPerson(int volunteerid) throws SQLException
    {
        return dalFacade.getAllGroupsForPerson(volunteerid);
    }

    public void undoLastDocumentedHours() throws SQLException
    {
        dalFacade.undoLastDocumentedHours();
    }

    /**
     * Takes a list and a query. Returns a new list filtered with the query. 
     * @param query
     * @param fullList
     * @return 
     */
    public List<Node> filterList(String query, List<Node> fullList)
    {
        List<Node> filteredList = new ArrayList();

        for (int i = 0; i < fullList.size(); i++)
        {
            Button button = (Button) fullList.get(i);
            if (button.getText().toLowerCase().contains(query.toLowerCase()))
            {
                filteredList.add(fullList.get(i));
            }
        }

        return filteredList;
    }
}
