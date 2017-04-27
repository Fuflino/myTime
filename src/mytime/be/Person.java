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
public abstract class Person
{

    private final StringProperty name;
    private final IntegerProperty id;
    private StringProperty email;
    private StringProperty phonenumber;
    private StringProperty description;
    private final StringProperty profilePicture;

    public Person(String name, int id, String email, String phonenumber, String profilePicture)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.phonenumber = new SimpleStringProperty(phonenumber);
        this.profilePicture = new SimpleStringProperty(profilePicture);
    }
    /**
     * 
     * @return StringProperty of email
     */
    public StringProperty getEmail()
    {
        return email;
    }
    /**
     * Sets the Stringproperty value
     * @param email New String value
     */
    public void setEmail(String email)
    {
        this.email.set(email);
    }
    /**
     * StringProperty of Phonenumber
     * @return Stringproperty
     */
    public StringProperty getPhonenumber()
    {
        return phonenumber;
    }
    /**
     * Sets the Stringproperty value
     * @param phonenumber New String value
     */
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber.set(phonenumber);
    }
    /**
     * If description is null, it returns a new StringProperty with the value "N/A"
     * @return 
     */
    public StringProperty getDescription()
    {
        if (description == null)
        {
            description = new SimpleStringProperty("N/A");
        }
        return description;
    }
    /**
     * Sets the StringProperty with a new String value
     * @param description New String value
     */
    public void setDescription(String description)
    {
        this.description.set(description);
    }
    /**
     * 
     * @return StringProperty of name
     */
    public StringProperty getName()
    {
        return name;
    }
    /**
     * Gets the IntegerProperty of the ID
     * @return IntegerProperty
     */
    public IntegerProperty getId()
    {
        return id;
    }
    /**
     * Gets the profile picture as a URL string wrapped in a StringProperty
     * @return 
     */
    public StringProperty getProfilePicture()
    {
        return profilePicture;
    }

}
