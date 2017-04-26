/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal;

import mytime.dal.controller.IVolunteer;
import mytime.dal.controller.VolunteerController;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class DALFacade
{
    private final IVolunteer volunteerController;

    public DALFacade()
    {
        volunteerController = new VolunteerController();
    }
    
    
    
    
}
