/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kursinis_darbas;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author bloga
 */
public class ZaidimoKunas {
    File failas ;
    TekstoGavejas tekstoGavejas;
    String einamasZodis;
    int einamaRaide;
    Scene zaidimoScena;
    VBox destymas = new VBox(2);
    FlowPane raidziuRinkinys = new FlowPane();
    long laikoStartas;
    int simboliuSkaicius;
    ZaidimoKunas(){
        Random rand = new Random();
        int failoNumeris = rand.nextInt(4);
        failas = new File("D:\\Random stuff\\VGTU\\Programų sistemų projektavimas\\Kursinis\\PVZ\\Kursinis_darbas\\src\\main\\java\\com\\mycompany\\kursinis_darbas\\rinkinys"+failoNumeris+".txt");
        laikoStartas = System.currentTimeMillis();
        tekstoGavejas = new TekstoGavejas(failas);
        einamasZodis = tekstoGavejas.gautiZodi();
        simboliuSkaicius += einamasZodis.length();
        destymas.setAlignment(Pos.CENTER);
        piestiZodi(true);
        raidziuRinkinys.setAlignment(Pos.CENTER);
        destymas.getChildren().add(raidziuRinkinys);
        zaidimoScena = new Scene(destymas, 600, 550);
        zaidimoScena.setOnKeyTyped((KeyEvent e) ->
        {
            char paspaudimas = e.getCharacter().charAt(0);
            patikrintiRaide(paspaudimas);
        });
        MainApp.pagrindinisSteidzas.setScene(zaidimoScena);
        
        
    
}
    private void piestiZodi (boolean pirmasBandymas)
    {
        int raidesIndeksas = 0;
        String zodis = einamasZodis;
        raidziuRinkinys.getChildren().clear();
        for ( String raide : zodis.split(""))
        {
            Label raidesEtikete = new Label(raide);
            if (raidesIndeksas<einamaRaide)
            {
                raidesEtikete.setTextFill(Color.GREEN);
            }
            else if (raidesIndeksas==einamaRaide)
            {
                if (pirmasBandymas)
                {
                    raidesEtikete.setTextFill(Color.BLUE);
                }
                else
                {
                    raidesEtikete.setTextFill(Color.RED);
                }
            }
            else
            {
                raidesEtikete.setTextFill(Color.DARKGRAY);
            }
            raidesEtikete.setPrefWidth(20);
            raidesEtikete.setAlignment(Pos.CENTER);
            raidesEtikete.setFont(new Font ("Calibri", 24));
            raidziuRinkinys.getChildren().add(raidesEtikete);
            raidesIndeksas++;
        }
    }
    
    private void patikrintiRaide (char raide)
    {
        if (raide != einamasZodis.charAt(einamaRaide))
        {
            piestiZodi(false);
        }
        else
        {
            einamaRaide++;
            piestiZodi(true);
            if(einamaRaide == einamasZodis.length())
            {
                einamaRaide = 0;
                einamasZodis = tekstoGavejas.gautiZodi();
                if(einamasZodis == null)
                {
                    double laikas = (System.currentTimeMillis()-laikoStartas);
                    Double SPS = simboliuSkaicius/(laikas/1000);
                    Double tikslusSPS = BigDecimal.valueOf(SPS).setScale(2,RoundingMode.HALF_UP).doubleValue();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Rezultatas");
                    alert.setHeaderText(null);
                    alert.setContentText("Jūsų rezultatas yra : " + tikslusSPS + " SPS.");

                    alert.show();
                    tekstoGavejas.issaugotiRezultata(new Timestamp(System.currentTimeMillis()).toString() + "," + tikslusSPS);
                    MainApp.pagrindinisSteidzas.setScene(MainApp.pagrindineScena);
                    
                    //System.out.println(laikas);
                    //System.out.println(simboliuSkaicius);
                    //System.out.println(simboliuSkaicius/(laikas/1000));
                }
                else
                {
                piestiZodi(true);
                simboliuSkaicius+=einamasZodis.length();
                }
            }
        }
    }
    
}
