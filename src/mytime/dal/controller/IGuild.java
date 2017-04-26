/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytime.dal.controller;

import java.sql.SQLException;

/**
 *
 * @author Bruger
 */
public interface IGuild
{
    public void createGuild(String name, String location) throws SQLException;
}
