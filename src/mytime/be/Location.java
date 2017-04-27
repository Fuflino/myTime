/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.be;

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

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public IntegerProperty getId()
    {
        return id;
    }

    
    public Location(String name, int id)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
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
     * To string method override
     * @return 
     */
    @Override
    public String toString()
    {
        return this.name.get();
    }

}
