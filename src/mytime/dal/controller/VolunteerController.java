/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.controller;

import mytime.dal.dao.VolunteerDAO;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class VolunteerController implements IVolunteer
{
    private VolunteerDAO dao;

    public VolunteerController()
    {
        dao = new VolunteerDAO();
        
    }
    
    
    
    
    
    
}
