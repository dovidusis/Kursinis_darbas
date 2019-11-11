package com.mycompany.kursinis_darbas;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainApp extends Application {
    static Stage pagrindinisSteidzas;
    static Scene pagrindineScena;
    String pavadinimas = "DD Key Count";
    File rezultatuFailas = new File("D:\\Random stuff\\VGTU\\Programų sistemų projektavimas\\Kursinis\\Kursinis_darbas\\src\\main\\java\\com\\mycompany\\kursinis_darbas\\rezultatai.txt");
    
    @Override
    public void start(Stage stage) throws Exception {
        pagrindinisSteidzas = stage;
        pagrindinisSteidzas.setTitle(pavadinimas);
        BorderPane langoDestymas = new BorderPane(); // Pagrindinis langas
        pagrindineScena = new Scene (langoDestymas, 600, 550);
        langoDestymas.setBackground(new Background(new BackgroundFill(Color.rgb(206, 204, 207), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label pagrindinioTop = new Label (pavadinimas);
        pagrindinioTop.setFont ( new Font( "Edwardian Script ITC", 50));
        pagrindinioTop.setTextFill(Color.DARKVIOLET);
        pagrindinioTop.setPadding( new Insets(0, 0, 0, 0));
        StackPane meniuPasirinkimai = new StackPane();
        meniuPasirinkimai.getChildren().add(pagrindinioTop);
        langoDestymas.setTop(meniuPasirinkimai);
        
        //Mygtuku sąrašas (kairysis)
        
        VBox navigacija = new VBox(2);
        navigacija.setPadding(new Insets(10));

        
        //Mygtukai
        
        Button rezultatuMygtukas = new Button ("Rezultatai");
        rezultatuMygtukas.setPrefSize(100,20);
        rezultatuMygtukas.setOnAction(e ->
        {
            BorderPane rezultatuIstorija = new BorderPane();
            Scene rezultatuScena = new Scene(rezultatuIstorija, 600, 550);
            rezultatuIstorija.setBackground(new Background(new BackgroundFill(Color.rgb(206, 204, 207), CornerRadii.EMPTY, Insets.EMPTY)));
            VBox rezultatuNavigacija = new VBox(2);
            rezultatuNavigacija.setPadding(new Insets(10));
            rezultatuIstorija.setLeft(rezultatuNavigacija);
            Button mygtukasAtgal = new Button ("Atgal");
            mygtukasAtgal.setPrefSize(100,20);
            mygtukasAtgal.setOnAction(e2 ->
            {
               pagrindinisSteidzas.setScene(pagrindineScena); 
            });
            rezultatuNavigacija.getChildren().add(mygtukasAtgal);
            
            //Rezultatų istorija
        
            Text text1 = new Text("Data");
            Text text2 = new Text("Rezultatas");
            TableView rezultatuLentele = new TableView();
            TableColumn datosStulpelis = new TableColumn("Data");
            datosStulpelis.setMinWidth(240);
            TableColumn rezultatoStulpelis = new TableColumn("Rezultatas");
            rezultatoStulpelis.setMinWidth(240);
            rezultatuLentele.setPlaceholder(new Text ("Sužaiskite bent vieną kartą, kad rezultatai būtų rodomi!"));
            datosStulpelis.setCellValueFactory(new PropertyValueFactory<Rezultatas,String>("data"));
            rezultatoStulpelis.setCellValueFactory(new PropertyValueFactory<Rezultatas,String>("SPS"));
            ObservableList<Rezultatas> rezultatai = FXCollections.observableArrayList();
            try
            {
                BufferedReader skaitytuvas = new BufferedReader(new FileReader(rezultatuFailas));
                String rezultatas = skaitytuvas.readLine();
                while(rezultatas != null)
                {
                    String [] rezultatoDalys = rezultatas.split(",");
                    rezultatai.add(new Rezultatas(rezultatoDalys[0],rezultatoDalys[1]));
                    rezultatas = skaitytuvas.readLine();
                }
            }
            catch(FileNotFoundException e3)
            {
                System.err.println("Failas nerastas!");
            } catch (IOException ex) {
                System.err.println("Pasiekta failo pagaiba!");
            }
            rezultatuLentele.setItems(rezultatai);
            rezultatuLentele.getColumns().addAll(datosStulpelis, rezultatoStulpelis);
            rezultatuIstorija.setCenter(rezultatuLentele);
            pagrindinisSteidzas.setScene(rezultatuScena);
            });
        //Pagrindinis meniu
        
        Button pradetiZaidima = new Button("Pradėti žaidimą");
        pradetiZaidima.setOnAction(e->
        {
           new ZaidimoKunas();
        });
        navigacija.getChildren().add(pradetiZaidima);
        navigacija.getChildren().add(rezultatuMygtukas);
        navigacija.setAlignment(Pos.CENTER);
        
        StackPane centravimas = new StackPane();
        centravimas.getChildren().add(navigacija);
        
        
        langoDestymas.setCenter(centravimas);
        pagrindinisSteidzas.setScene(pagrindineScena);
        pagrindinisSteidzas.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
