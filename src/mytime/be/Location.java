/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.be;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bruger
 */
public class Location
{
    
    private final StringProperty name;
    
    private final IntegerProperty id;
    private List<Group> groups;
    

    
    public Location(String name, int id)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        groups = new ArrayList();
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public IntegerProperty getId()
    {
        return id;
    }
    
    /**
     * Get list of groups.
     * @return 
     */
    public List<Group> getGroups()
    {
        return groups;
    }
    
    /**
     * Add a group to the group arraylist
     * @param group 
     */
    public void addGroup(Group group)
    {
        groups.add(group);
    }

    /**
     * Set list of groups on this location 
     * @param groups 
     */
    public void setGroups(List<Group> groups)
    {
        this.groups = groups;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public StringProperty getName()
    {
        return name;
    }
    

}
