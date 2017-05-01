/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mytime.dal.DALFacade;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class MyTime extends Application
{

    private String[] sname1 =
    {
        "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "Ph", "St", "Sh", "Of", "Off", "Ed", "Ead", "Aem", "Ch", "Ch", "Ch",
    };
    private String[] sname2 =
    {
        "ee", "ee", "ee", "ae", "oo", "e", "e", "e", "a", "o", "hoo", "hea", "eah", "eeh", "ooh", "eo", "oer", "ar", "oul", "ool", "ollo", "ello", "olle", "ole", "elo", "eko", "ekko", "okke", "alle", "ale", "aes", "en", "ay", "ay", "ay", "o", "ol", "al", "as", "es", "esse", "asse", "an"
    };
    private String[] sname3 =
    {
        "bon", "con", "kon", "shon", "tran", "fan", "sper", "die", "die", "l", "l", "k", "k", "k", "kam", "pan", "ku", "pros", "rion", "win", "rys", "rys", "ras", "ros", "wen", "l", "b", "x", "a", "ila", "illa", "eria", "chan", "i", "y", "li", "ly", "nis", "is", "nila", "nas", "yas"
    };
    private String[] vocal =
    {
        "E", "U", "I", "O", "A", "Æ", "Ø", "Å"
    };

    @Override
    public void start(Stage stage) throws Exception
    {
//        ResourceBundle bundle = ResourceBundle.getBundle("mytime.gui.UIResources", new Locale("da_DK"));
        Parent root = FXMLLoader.load(getClass().getResource("view/ChooseLokation.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        
//        DALFacade facade = DALFacade.getInstance();
//        generatePhoneNumber();
//        int hours = facade.getHoursWorkedOnOneGuildByVolunteer(3, 2);
//        System.out.println("Hours: " + hours);
//        Location location = new Location("Location", 1);
//        location = facade.getSelectedLocation(location);
//        List<Group> groups = location.getGroups();
//        for (Group group : groups)
//        {
//            System.out.println(group.getName().get());
//            List<Person> personlist = group.getPersonlist();
//            for (Person person : personlist)
//            {
//                System.out.println("--"+ person.getName().get());
//                
//            }
//        }
//        
//        List<Location> locations =facade.getAllLocations();
//        for (Location location : locations)
//        {
//            System.out.println(location.getName().getValue());
//        }
//        facade.createVolunteer("Manny", "1337h4X0R@gmail.com", "75181978");
//        facade.createGuild("Bo i vikinge hytte", "Bork");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    
    /**
     * Adds a volunteer with genereted name and phonenumber and adds him
     * to the database. Assigns the added volunteer to 2-8 randombly selected
     * guilds.
     */
    public void addVolunteer()
    {
        DALFacade facade;
        try
        {
            facade = DALFacade.getInstance();
            String name = generateName();
            String phonenumber = generatePhoneNumber();
            int id = facade.createVolunteer(name, name.toLowerCase() + "@" + name.toLowerCase() + ".com", phonenumber);

            Random rando = new Random();
            int amountOfGuilds = rando.nextInt(6) + 2;
            for (int i = 0; i < amountOfGuilds; i++)
            {
                List<Integer> availableGuilds = facade.getArrayOfAvailableGuildsForVolunteer(id);
                //Random rando = new Random();
                int randomIndex = rando.nextInt(availableGuilds.size());

                facade.addVolunteerToGuild(id, availableGuilds.get(randomIndex));
                System.out.println("Added: (" + name + ") succesfully." + "Assaigned (" + name + ") to guild " + availableGuilds.get(randomIndex));

            }

        } catch (IOException ex)
        {
            Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return a generated name as a string.
     */
    public String generateName()
    {
        String name = "";
        Random rando = new Random();
        {
            int num1 = rando.nextInt(sname1.length);
            int num2 = rando.nextInt(sname2.length);
            int num3 = rando.nextInt(sname3.length);

            name += sname1[num1];
            boolean firstLetterVocal = false;
            for (String string : vocal)
            {
                if (sname1[num1].equals(string))
                {
                    firstLetterVocal = true;
                    break;
                }
            }
            if (firstLetterVocal == false)
            {
                name += sname2[num2];
            }
            name += sname3[num3];
        }
        return name;
    }

    /**
     * @return a generated phonenumber
     */
    private String generatePhoneNumber()
    {
        String phonenumber = "";
        for (int i = 0; i < 8; i++)
        {
            Random rando = new Random();
            int randomnumber = rando.nextInt(10);
            System.out.println(randomnumber);
            phonenumber += randomnumber;
        }
        return phonenumber;
    }
}
