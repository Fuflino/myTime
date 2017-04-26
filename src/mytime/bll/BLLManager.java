/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.bll;

import java.io.IOException;
import mytime.dal.DALFacade;

/**
 *
 * @author Stefan-VpcEB3J1E
 */
public class BLLManager
{
    private final DALFacade dalFacade;

    public BLLManager() throws IOException
    {
        dalFacade = new DALFacade();
    }
    
    
    
    
}
