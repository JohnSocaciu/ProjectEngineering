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
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * FXML Controller class
 *
 * @author Kyle Jackson
 */
public class Ticket_FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */

    private HashMap<String, Integer> merchandiseList = new HashMap<String, Integer>();
    private String selectedTeam;

    @FXML
    private Label Price;

    @FXML
    private Button closeButton;

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
    private ComboBox<String> comboBox = new ComboBox<String>();

    @FXML
    private ComboBox<String> secondCombo = new ComboBox<String>();

    @FXML
    private Label TeamSeatSelectionText;

    @FXML
    private Button seasonPassBtn;

    @FXML
    void clearSelections(ActionEvent event) {
        Price.setText("$0");
        selectedTeam = null;
        CartList.getItems().clear();
    }

    @FXML
    void select(ActionEvent event) {
        selectedTeam = comboBox.getValue();
        TeamSeatSelectionText.setText(selectedTeam);
    }

    @FXML
    void secondSelect(ActionEvent event) {
        String name = secondCombo.getValue();
        int value = merchandiseList.get(name);
        addItem(name, "Merchandise", value);
        // calculateCartTotal();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Add team names to comboBox from Teams file
        Scanner infile;
        try {
            infile = new Scanner(new File("ProjectEngineering/proj1/src/Teams.txt"));
            while (infile.hasNext()) {
                String teamName = infile.nextLine();
                comboBox.getItems().add(teamName);
                // System.out.println(teamName);

            }
            infile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file.");
        }

        // Add merchanise and pricing from merchandise file. Must follow -> Name,Price
        try {
            infile = new Scanner(new File("ProjectEngineering/proj1/src/Merchandise.txt"));
            while (infile.hasNext()) {
                String[] merchandise = infile.nextLine().split(",");

                String merchandiseName = merchandise[0];
                int MerchandiseCost = Integer.parseInt(merchandise[1]);
                merchandiseList.put(merchandiseName, MerchandiseCost);

                secondCombo.getItems().add(merchandiseName);
            }
            infile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file.");
        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addARowTicket(ActionEvent event) {
        if (selectedTeam == null) {
            return;
        }

        int price = 100;

        Button button = (Button) event.getSource();
        String seatID = button.getText();
        String ticket_name = seatID + "-" + selectedTeam;

        if (!cartHas(ticket_name)) {
            addItem(ticket_name, "Seat", price);
        }
    }

    @FXML
    public void addBRowTicket(ActionEvent event) {
        if (selectedTeam == null) {
            return;
        }
        int price = 50;

        Button button = (Button) event.getSource();
        String seatID = button.getText();
        String ticket_name = seatID + "-" + selectedTeam;

        if (!cartHas(ticket_name)) {
            addItem(ticket_name, "Seat", price);
        }
    }

    @FXML
    public void addCRowTicket(ActionEvent event) {
        if (selectedTeam == null) {
            return;
        }
        int price = 25;

        Button button = (Button) event.getSource();
        String seatID = button.getText();
        String ticket_name = seatID + "-" + selectedTeam;

        if (!cartHas(ticket_name)) {
            addItem(ticket_name, "Seat", price);
        }
    }

    @FXML
    private void addSeasonPass(ActionEvent event) {
        if (selectedTeam == null) {
            return;
        }
        int price = 500;
        String ticket_type = "Season Pass";
        if (!cartHas(selectedTeam)) {
            addItem(selectedTeam, ticket_type, price);
        }
    }

    private void addItem(String name, String type, int price) {

        HBox box = new HBox();
        Label nameLabel = new Label(name);
        Label typeLabel = new Label(type);
        Label priceLabel = new Label("$" + price);
        HBox.setHgrow(nameLabel, Priority.ALWAYS);
        HBox.setHgrow(typeLabel, Priority.ALWAYS);
        HBox.setHgrow(priceLabel, Priority.ALWAYS);
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        typeLabel.setMaxWidth(Double.MAX_VALUE);

        box.getChildren().addAll(nameLabel, typeLabel, priceLabel);

        CartList.getItems().add(box);
        calculateCartTotal();
    }

    // Searches the cart by the name field and returns true or false if found or
    // not.
    private boolean cartHas(String name) {
        boolean hasValue = false;

        for (HBox entry : CartList.getItems()) {
            Node n = entry.getChildren().get(0);
            String item_name = ((Label) n).getText();

            if (item_name.equals(name)) {
                hasValue = true;
            }
        }
        return hasValue;
    }

    private void calculateCartTotal() {
        int total = 0;
        for (HBox entry : CartList.getItems()) {
            Node n = entry.getChildren().get(2);
            String priceText = ((Label) n).getText();
            int price = Integer.parseInt(priceText.substring(1));
            total = total + price;
        }

        Price.setText("$" + total);
    }
}