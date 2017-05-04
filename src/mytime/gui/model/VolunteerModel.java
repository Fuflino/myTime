/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import mytime.be.Group;
import mytime.be.Location;
import mytime.be.Person;
import mytime.bll.BLLManager;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerModel {

    private static VolunteerModel INSTANCE;
    private IntegerProperty userHourInput;
    private Location currentLocation;
    private Person currentVolunteer;
    private Group currentGuild;
    private List<Node> loginPersonNodes;
    private BLLManager bllMgr;
    private BooleanProperty justExecuted, isTextFieldRdy, cameFromVolunteerView;


    private Locale locale;

    private StringProperty searchQuery;
    private List<Node> loginPersonNodesFiltered;
    


    /**
     * Part of the singleton pattern
     */
    private VolunteerModel() {
        userHourInput = new SimpleIntegerProperty(0);
        justExecuted = new SimpleBooleanProperty(false);
        loginPersonNodes = new ArrayList<>();
        loginPersonNodesFiltered = new ArrayList<>();
        searchQuery = new SimpleStringProperty();
        isTextFieldRdy = new SimpleBooleanProperty(false);
        cameFromVolunteerView = new SimpleBooleanProperty();
    }

    /**
     * A part of the singleton pattern
     *
     * @return An instance of a VolunteerModel
     */
    public static VolunteerModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VolunteerModel();
        }
        return INSTANCE;
    }

    /**
     *
     * @return The IntegerProperty of the hours that the user have added in the
     * GUI
     */
    public IntegerProperty getUserHourInput() {
        return userHourInput;
    }

    /**
     * Adds one up in user hour input
     */
    public void addOneUpInUserHourInput() {
        userHourInput.set(userHourInput.get() + 1);
    }

    /**
     * Substracts one from the user hour input
     */
    public void minusOneUpInUserHoursInput() {
        if (!userHourInput.isEqualTo(0).get()) {
            userHourInput.set(userHourInput.get() - 1);
        }
    }

    /**
     * Documents the hours into the database
     *
     */
    public void executeHourDocumentation() throws SQLException {
        int hoursToDocumentate = userHourInput.get();
        Model.getInstance().addHoursForVolunteer(currentVolunteer.getId().get(), currentGuild.getId().get(), hoursToDocumentate);
        justExecuted.set(true);
    }

    /**
     * Sets the location that you choose in the chooselokation view.
     *
     * @param course
     */
    public void setCurrentLocation(Location course) {
        this.currentLocation = course;

    }

    /**
     * A list of all the persons in the LoginMainView wrapped as nodes.
     *
     * @return
     */
    public List<Node> getLoginPersonNodes() {
        return loginPersonNodes;
    }

    /**
     * Gets the current location.
     *
     * @return
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current active volunteer.
     *
     * @param volunteer
     */
    public void setCurrentVolunteer(Person volunteer) {
        currentVolunteer = volunteer;
    }

    /**
     * Sets the currently selected guild, to add hours to.
     *
     * @param currentGuild
     */
    public void setCurrentGuild(Group currentGuild) {
        this.currentGuild = currentGuild;
    }

    /**
     * Returns the currently selected guild to add hours to.
     *
     * @return
     */
    public Group getCurrentGuild() {
        return currentGuild;
    }

    /**
     * Returns the selected volunteer
     *
     * @return
     */
    public Person getCurrentVolunteer() {
        return currentVolunteer;
    }

    /**
     * @param volunteerid
     * @return the amount of hours one volunteer has worked in total, as an int.
     * The volunteer is defined by id
     * @throws SQLException
     */

    

    public int getTotalHoursOneVolunteer() throws SQLException
    {
        return bllMgr.getTotalHoursOneVolunteer(currentVolunteer.getId().get());

    }

    /**
     * @param volunteerid
     * @param guildid
     * @return amount of hours one person worked on one guild, as an int.
     * @throws SQLException
     */
    public int getHoursWorkedOnOneGuildByVolunteer(int guildid) throws SQLException {
        return bllMgr.getHoursWorkedOnOneGuildByVolunteer(currentVolunteer.getId().get(), guildid);
    }

    /**
     * Sets the bllMgr
     *
     * @param bllMgr
     */
    public void setBllManager(BLLManager bllMgr) {
        this.bllMgr = bllMgr;
    }

    /**
     * Gets the boolean property when you execute hour documentation
     *
     * @return
     */
    public BooleanProperty getJustExecuted() {
        return justExecuted;
    }

    /**
     * Undoes the last documented hours a user had had pressed execute on.
     *
     * @throws SQLException
     */
    public void undoLastChanges() throws SQLException {
        bllMgr.undoLastDocumentedHours();
        justExecuted.set(true);
    }


    public void setCurrentLocale(Locale locale) 
    {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }


    /** 
     * @param query
     * @return a filtered list based on the given query
     */
    public List<Node> filterList(String query)
    {
        return bllMgr.filterList(query, loginPersonNodes);
    }

    /**
     * @return the filtered list of nodes.  
     */
    public List<Node> getLoginPersonNodesFiltered()
    {
        return loginPersonNodesFiltered;
    }

    /**
     * @return the value of the searchfield 
     */
    public StringProperty getSearchQuery()
    {
        return searchQuery;
    }
    /**
     * For the textfield in the login top view
     * @return 
     */
    public BooleanProperty getIsTextFieldRdy()
    {
        return isTextFieldRdy;
    }

    public BooleanProperty getCameFromVolunteerView()
    {
        return cameFromVolunteerView;
    }
        

}
