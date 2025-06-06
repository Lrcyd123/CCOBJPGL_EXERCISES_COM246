package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HomeController implements Initializable{

    ObservableList<User> mylist = FXCollections.observableArrayList();

    @FXML
    private Button btncreate;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<User, String> usernamecolum;

    @FXML
    private TableColumn<User, String> passwordcolum;

    @FXML
    private TableColumn<User, String> statuscolum;

    @FXML
    private TableColumn<User, String> accountcolum;

    @FXML
    private TableView<User> statuschoicesbox;

     @FXML
    private TableView<User> tabletab;

    @FXML
    Label usernamelabel;

    @FXML
    Label usernameLabel;

    @FXML
    TextField usernametextfield;

    @FXML
    TextField passwordtextfield;

    @FXML
    Label statustextfield;

    @FXML
    ChoiceBox<String> statuschoicebox;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String filename = "accounts.txt";

    @Override
    public void initialize(URL url, ResourceBundle rb){

        String username  = LoginController.user.getUsername();
        usernamelabel.setText(username);

        initializeCol();
        loadData();

        
        statuschoicebox.getItems().addAll("Active", "Inactive");

        tabletab.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            
            if (newSelection != null) {
                usernametextfield.setText(newSelection.getUsername());
                passwordtextfield.setText(newSelection.getPassword());
                statuschoicebox.setValue(newSelection.getAccountstatus());
            }
        });
    }

    private void initializeCol(){

        usernamecolum.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordcolum.setCellValueFactory(new PropertyValueFactory<>("password"));
        accountcolum.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        statuscolum.setCellValueFactory(new PropertyValueFactory<>("accountstatus"));
    }

    private void loadData(){

        mylist.clear();

        try {
            
            File myFile = new File("accounts.txt");

            
            if (myFile.exists()) {

                Scanner filescanner = new Scanner(myFile);

                while (filescanner.hasNextLine()) {

                    String data = filescanner.nextLine();
        
                    String username = data.split(",")[0];
                    String password = data.split(",")[1];
                    String dcreated = data.split(",")[2];
                    String status = data.split(",")[3];

                    mylist.add(new User(username, password, dcreated, status));
                } 
                
                tabletab.setItems(mylist);

                filescanner.close();
            }
            else {
                System.out.println(myFile.getName() + " does not exist!");
            }
        } catch (Exception e) {
            System.out.println("There is an error");
        } 
    }

    @FXML
    private boolean createuser(ActionEvent event) {

        String username = usernametextfield.getText();

        String password = passwordtextfield.getText();

        String status = statuschoicebox.getValue();
        System.out.println(status);
        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no username provided");
            return false;
        }

        if(password.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no password provided");
            return false;
        }

        
        LocalDate today = LocalDate.now();

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDate = today.format(formatter);

        User user = new User(username, password, formattedDate, status);

        try {

            BufferedWriter myWriter = new BufferedWriter(new FileWriter("accounts.txt", true));
      
            
            myWriter.newLine(); 
            myWriter.write(user.getUsername() + "," + user.getPassword() + "," + user.getAccountcreated() + "," + user.getAccountstatus());

            
            myWriter.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("This is the header");
            alert.setContentText("This is an alert message!");
            alert.showAndWait();
            loadData();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return true;
    }
    
    @FXML  
    public boolean deleteuser(ActionEvent event) {

        User user = tabletab.getSelectionModel().getSelectedItem();

        String username = (user.getUsername());

        System.out.println(username);

        String userToDelete = username;

        List<String> updatedLines = new ArrayList<>();

        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { 
                    String[] parts = line.split(",");
                    if (!parts[0].equalsIgnoreCase(userToDelete)) {
                        updatedLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + userToDelete + "' has been deleted (if existed).");
        alert.showAndWait();
        loadData();
        
        return true;
    }
    
    @FXML
    public boolean updateuser(ActionEvent event) {

        User user = tabletab.getSelectionModel().getSelectedItem();

        String username = usernametextfield.getText();

        String password = passwordtextfield.getText();

        String newUsername = username;

        String status = statuschoicebox.getValue();

        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }

        if(password.length() == 0)
        {
            System.out.println("No password!");
            return false;
        }

        
        String targetUsername = user.getUsername();
        String newPassword = password;
        String newStatus = status;

        List<String> updatedLines = new ArrayList<>();

        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");

                    if (parts.length == 4 && parts[0].equalsIgnoreCase(targetUsername)) {
                        updatedLines.add(newUsername + "," + newPassword + "," + user.getAccountcreated() + "," + newStatus);
                    } else {
                        updatedLines.add(line);
                    }

                    
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + targetUsername + "' has been updated.");
        alert.showAndWait();
        loadData();
        return true;
    }

}