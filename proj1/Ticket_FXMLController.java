package ticket_finder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ItemEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.collections.FXCollections; 
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Kyle Jackson
 */
public class Ticket_FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ArrayList <String> selectedSeats = new ArrayList<String>();
    private ArrayList <String> selectedTeams = new ArrayList<String>();
    private int teamIndex = 0;
    private String [] itemList  = {"Bobblehead", "Thunder Stick", "Foam Paw", "T-Shirt", "Sweat Shirt", "Cap", "Knit Hat", "Mug", "Pennant"};
    private String [] teamList = {"Redwings","Lions","Tigers","Pistons"}; 
    
    @FXML
    private Label Price;
    
    @FXML
    private ListView<HBox> CartList;
    
     @FXML
    private Button clearButton;
    
    @FXML
    private ImageView firstImage;
    
    @FXML
    private ImageView secondImage;

    @FXML
    private TitledPane MerchPane;

    @FXML
    private ComboBox <String> comboBox = new ComboBox <String>();
    
    @FXML
    private ComboBox <String> secondCombo = new ComboBox <String>();
    
    @FXML
    void clearSelections(ActionEvent event) {
    Price.setText("$0");
    CartList.getItems().clear();
    }

    @FXML
    void select(ActionEvent event) {
    String name = comboBox.getValue();
    int price = 50; 
    addItem(name,price); 
    calculateCartTotal(); 
    }
    
    @FXML
    void secondSelect(ActionEvent event) {
    String name = secondCombo.getValue(); 
    int [] costs = {1,2,3,4,5,6,7,8}; 
    int price = 10; 
    addItem(name,price); 
    calculateCartTotal(); 
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    comboBox.getItems().addAll(teamList);
    
    secondCombo.getItems().addAll(itemList);
     
    }    
    
    @FXML
    public void addARowTicket(ActionEvent event){
         int price = 500;
         
         Button button = (Button) event.getSource();
         String seatID = button.getText();
         
         if(selectedSeats.contains(seatID)){
             removeItem(seatID);
         }else{
             addItem(seatID,price);
         }
         calculateCartTotal();
    }
    
    @FXML public void addBRowTicket(ActionEvent event){
        int price = 100;
        
         Button button = (Button) event.getSource();
         String seatID = button.getText();
         
         if(selectedSeats.contains(seatID)){
             removeItem(seatID);
         }else{
             addItem(seatID,price);
         }
         calculateCartTotal();
    }
    
    @FXML public void addCRowTicket(ActionEvent event){
        int price = 50;
        
         Button button = (Button) event.getSource();
         String seatID = button.getText();
         
         if(selectedSeats.contains(seatID)){
             removeItem(seatID);
         }else{
             addItem(seatID,price);
         }
         calculateCartTotal();
    }
    
    private void addItem(String name, int price){
        
        HBox box = new HBox();
            Label nameLabel = new Label(name);
            Label priceLabel = new Label("$"+price);
        HBox.setHgrow(nameLabel, Priority.ALWAYS);
        HBox.setHgrow(priceLabel, Priority.ALWAYS);
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        
        box.getChildren().addAll(nameLabel, priceLabel);
        
        CartList.getItems().add(box);
        selectedSeats.add(name);
    }
    
    private void removeItem(String name){
        
        CartList.getItems().remove(selectedSeats.indexOf(name));
        selectedSeats.remove(name);
    }
    
    private void calculateCartTotal(){
        int total = 0;
        for(HBox entry:CartList.getItems()){
            Node n = entry.getChildren().get(1);
            String priceText = ((Label) n).getText();
            int price = Integer.parseInt( priceText.substring(1) );
            total = total+price;
        }
        
        Price.setText("$"+total);
    }
}
