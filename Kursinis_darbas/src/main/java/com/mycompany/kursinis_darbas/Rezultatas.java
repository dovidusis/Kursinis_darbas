/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kursinis_darbas;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bloga
 */
public class Rezultatas {
    private final SimpleStringProperty data;
    private final SimpleStringProperty SPS;
    
    public Rezultatas(String data, String SPS)
    {
        this.data = new SimpleStringProperty (data);
        this.SPS = new SimpleStringProperty (SPS);
    }
    public String getData()
    {
        return data.get();
    }
    public String getSPS()
    {
        return SPS.get();
    }
}
