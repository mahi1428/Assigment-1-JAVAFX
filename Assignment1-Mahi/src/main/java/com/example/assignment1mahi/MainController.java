package com.example.assignment1mahi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//I have first defined variables for each and every fxml object in the controller files.
public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private BarChart<String, Number> destinationGraph;

    @FXML
    private TableView<DestinationModel> DataTable;

    @FXML
    private TableColumn<DestinationModel, Number> idColumn;
    @FXML
    private TableColumn<DestinationModel, String> nameColumn;

    @FXML
    private TableColumn<DestinationModel, Number> popularityColumn;

    @FXML
    private Button toggleButton;

    // In order to keep all the information for the Destinations, I have made an observable list.
    private ObservableList<DestinationModel> destinationlist;

//The purpose of the two arrays below is to hold data from the MySQL database, which we will also utilize to fill our previously created observable list of destination model objects.
    static String[] destinationNames = new String[9];
    static int[] destinationPopularity = new int[9];

    static private Connection connection;


  //  In the method below, we will first establish a connection to our database, after which we will extract all of the data and fill our observable list. Finally, we will create our list.
    public static List<DestinationModel> getDestinationList() {
        List<DestinationModel> Destinations = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristdestinations", "root", "202202@Mp");
            System.out.println("Connected to database");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Destinations";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Destination");
                int popularity = resultSet.getInt("popularity");
                System.out.println(id + " " + name + " " + popularity);
                Destinations.add(new DestinationModel(id, name, popularity));
                destinationNames[id - 1] = name;
                destinationPopularity[id - 1] = popularity;
            }
            resultSet.close();
            statement.close();
        }
        // Close the result set and statement
        catch (Exception e) {
            e.printStackTrace();
        }
        return Destinations;
    }

    // Using the two arrays we previously constructed (which we loaded from the database), we will populate the bar graph in the following method.
    private void createBarGraph() {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Most Popular tourist destinations in India in 2023 by Number of tourists");
        for (int i = 0; i < 8; i++) {
            series1.getData().add(new XYChart.Data<>(destinationNames[i], destinationPopularity[i]));
        }
        destinationGraph.getData().add(series1);
    }

//    The tabular view and bar graph can be alternated using the toggle display method,
//    Additionally, this method will alter the toggle button's text to improve the user interface.

    @FXML
    protected void toggleDisplay(){
        boolean isTableVisible = DataTable.isVisible();
        DataTable.setVisible(!isTableVisible);
        destinationGraph.setVisible(isTableVisible);
        toggleButton.setText(isTableVisible ? "Show Table" : "Show Chart");
    }

//    Lastly, we will build up the cell value factories for each column in the table and let them know which model class properties to retrieve the data from in the initialize method.
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        popularityColumn.setCellValueFactory(new PropertyValueFactory<>("popularity"));
        // Getting back the list of all the objects.
        destinationlist = FXCollections.observableArrayList(getDestinationList());

//        setting up our graph
        createBarGraph();
        DataTable.setItems(destinationlist);
//        Initally displaying the table and not the graph.
        destinationGraph.setVisible(false);
    }
}
