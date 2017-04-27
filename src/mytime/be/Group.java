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
 * @author Stefan-VpcEB3J1E
 */
public abstract class Group
{

    private final IntegerProperty id;
    private StringProperty name;
    private final IntegerProperty locationId;
    private StringProperty description;
    private List<Person> personlist;
    
    public Group(String name, int id, int locationId, String description)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.locationId = new SimpleIntegerProperty(locationId);
        this.description = new SimpleStringProperty(description);
        
        personlist = new ArrayList();
    }
    /**
     * 
     * @return String property of name 
     */
    public StringProperty getName()
    {
        return name;
    }
    /**
     * 
     * @param name The new String value of String property
     */
    public void setName(String name)
    {
        this.name.set(name);
    }
    /**
     * String property of description
     * @return 
     */
    public StringProperty getDescription()
    {
        return description;
    }
    /**
     * Wraps the new String value in a StringProperty
     * @param description 
     */
    public void setDescription(String description)
    {
        this.description.set(description);
    }
    /**
     * The IntegerProperty of ID
     * @return 
     */
    public IntegerProperty getId()
    {
        return id;
    }
    /**
     * IntegerProperty of ID
     * @return 
     */
    public IntegerProperty getLocationId()
    {
        return locationId;
    }
    
    /**
     * Get list of persons in the group
     * @return 
     */
    public List<Person> getPersonlist()
    {
        return personlist;
    }

    /**
     * Set list of persons in the group
     * @param personlist 
     */
    public void setPersonlist(List<Person> personlist)
    {
        this.personlist = personlist;
    }
    
    

}
