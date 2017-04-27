/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bruger
 */
public class Guild {

    private final IntegerProperty id;
    private StringProperty name;
    private IntegerProperty locationId;
    private StringProperty description;

    public Guild(IntegerProperty id, StringProperty name, IntegerProperty locationId, StringProperty description) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.description = description;
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
     * Get the value of name
     *
     * @return the value of name
     */
    public StringProperty getName() 
    {
        return name;
    }
    
    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(StringProperty name) 
    {
        this.name = name;
    }
    
    /**
     * Get the value of locationId
     *
     * @return the value of locationId
     */
    public IntegerProperty getLocationId() 
    {
        return locationId;
    }
    
    /**
     * Set the value of locationId
     *
     * @param locationId new value of locationId
     */
    public void setLocationId(IntegerProperty locationId) 
    {
        this.locationId = locationId;
    }
    
    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public StringProperty getDescription() 
    {
        return description;
    }
    
    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(StringProperty description) 
    {
        this.description = description;
    }

    
    
    
}
