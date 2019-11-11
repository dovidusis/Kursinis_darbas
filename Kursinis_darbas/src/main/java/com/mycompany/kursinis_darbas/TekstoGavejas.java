/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kursinis_darbas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author bloga
 */
public class TekstoGavejas {
    
    File failas;
    BufferedReader skaitytuvas;
    File rezultatuFailas = new File("D:\\Random stuff\\VGTU\\Programų sistemų projektavimas\\Kursinis\\Kursinis_darbas\\src\\main\\java\\com\\mycompany\\kursinis_darbas\\rezultatai.txt");
    String eilute = "";
    String zodis;
    
    public TekstoGavejas(File failas)
    {
        this.failas = failas;
        try
        {
            skaitytuvas = new BufferedReader(new FileReader(failas));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Failas nerastas!");
        }
        catch(IOException e)
        {
            System.err.println("Failas nerastas!");
        }
    }
    public String gautiZodi()
    {  
        if(eilute.length()<=0)
        {
            eilute = gautiEilute();
        }
        if(eilute == null)
        {
            return null;
        }
        if(eilute.contains(" "))
        {
            zodis = eilute.substring(0,eilute.indexOf(" "));
            zodis = zodis.trim();
            eilute = eilute.substring(eilute.indexOf(" "),eilute.length());
            eilute = eilute.trim();
        }
        else
        {
            zodis = eilute;
            eilute = "";
        }
        return zodis;
    }
    private String gautiEilute()
    {
        String eilute = "";
        
        try
        {
            while (eilute.length() <= 0)
            {
                eilute = skaitytuvas.readLine().trim();
            }
            
        }
        catch ( Exception e )
        {
            eilute = null;
            try {
                if(skaitytuvas!=null)
                {
                skaitytuvas.close();
                skaitytuvas = null;
                }
            } catch (IOException ex) {
                System.err.println("Nenumatyta klaida!");
            }
            System.err.println("Pasiekta failo pabaiga!");
            
        }
        return eilute;
    }
    public void issaugotiRezultata(String rezultatas)
    {
        try {
            BufferedWriter saugotuvas = new BufferedWriter (new FileWriter(rezultatuFailas,true));
            saugotuvas.write(rezultatas);
            saugotuvas.newLine();
            saugotuvas.close();
        } catch (IOException ex) {
            System.err.println("Nepavyko išsaugoti!");
        }
    }
}
