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
 * @author Stefan-VpcEB3J1E
 */
public class Volunteer
{

    private StringProperty name;
    private final IntegerProperty id;
    private StringProperty email;
    private StringProperty phonenumber;
    private StringProperty description;

    public Volunteer(String name, int id, String email, String phonenumber)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.phonenumber = new SimpleStringProperty(phonenumber);
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


    /**
     * Get the value of phonenumber
     *
     * @return the value of phonenumber
     */
    public StringProperty getPhonenumber()
    {
        return phonenumber;
    }

    /**
     * Set the value of phonenumber
     *
     * @param phonenumber new value of phonenumber
     */
    public void setPhonenumber(StringProperty phonenumber)
    {
        this.phonenumber = phonenumber;
    }


    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public StringProperty getEmail()
    {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(StringProperty email)
    {
        this.email = email;
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

    
}
