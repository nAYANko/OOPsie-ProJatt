import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import myPackage.Farmer;
import myPackage.Government;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class NormalApplication extends Application {

    private Government government = new Government();
    private Farmer farmer = new Farmer();
    private TextArea announcementsArea;
    private TextArea grievancesArea;
    private TextField farmerNameInput;
    private TextField farmerIdInput;

    @Override
    public void start(Stage primaryStage) {
        // Main layout setup
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-image: url('bg-normal.png'); " +
                     "-fx-background-size: cover; " +
                     "-fx-background-position: center; " +
                     "-fx-background-repeat: no-repeat;");

        // Top orange margin
        Label topMargin = new Label();
        topMargin.setMinHeight(75);
        topMargin.setStyle("-fx-background-color: orange;");
        Image logoImage = new Image(getClass().getResourceAsStream("/satyamev jayate.png"));

        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(75);
        logoImageView.setFitHeight(75);
        logoImageView.setPreserveRatio(true); // Set background to transparent
        root.setTop(topMargin);

        // Announcements box in top left
        VBox announcementsBox = new VBox(10);
        announcementsBox.setPadding(new Insets(10));
        
        Label announcementsLabel = new Label("Announcements:");
        announcementsLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        announcementsArea = new TextArea();
        announcementsArea.setEditable(false);
        announcementsArea.setStyle("-fx-border-color: orange; -fx-background-color: white;");

         // Create a Timeline for flashing text
         Timeline flashTimeline = new Timeline(
            new KeyFrame(Duration.seconds(0.25), e -> announcementsArea.setStyle("-fx-text-fill: red; -fx-font-weight: bold;")),
            new KeyFrame(Duration.seconds(1.0), e -> announcementsArea.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold;"))
        );
        flashTimeline.setCycleCount(Timeline.INDEFINITE); // Loop indefinitely
        flashTimeline.play();



        
        Button reloadAnnouncementsButton = new Button("Reload Announcements");
        reloadAnnouncementsButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        
        
        announcementsBox.getChildren().addAll(logoImageView, announcementsLabel, announcementsArea, reloadAnnouncementsButton);
        root.setLeft(announcementsBox);
        
        reloadAnnouncementsButton.setOnAction(e -> {
            announcementsArea.clear();
            announcementsArea.appendText(government.getAnouncement());
        });

        // Login/Register box in top right
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(100,10,10,10));
        
        Label registerLabel = new Label("Login:");
        registerLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        farmerNameInput = new TextField();
        farmerNameInput.setPromptText("Farmer Name");

        // Set preferred width and height
        farmerNameInput.setPrefWidth(450); // Adjust width as needed
        farmerNameInput.setPrefHeight(30); // Adjust height as needed

        farmerIdInput = new TextField();
        farmerIdInput.setPromptText("Farmer ID");

        farmerIdInput.setPrefWidth(450); // Adjust width as needed
        farmerIdInput.setPrefHeight(30); 
        
        Button registerFarmerButton = new Button("Register Farmer");
        registerFarmerButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        
        registerFarmerButton.setOnAction(e -> {
            farmer.setFarmerName(farmerNameInput.getText());
            farmer.setFarmerID(farmerIdInput.getText());
            farmer.Register();
            showAlert("Farmer Registered", "Farmer " + farmer.getFarmerName() + " successfully registered!");
        });
        
        loginBox.getChildren().addAll(registerLabel, farmerNameInput, farmerIdInput, registerFarmerButton);
        root.setRight(loginBox);

        // GridPane for bottom section (Grievances on the left, Stats on the right)
        GridPane bottomGrid = new GridPane();
        bottomGrid.setPadding(new Insets(10));
        bottomGrid.setHgap(300);
        bottomGrid.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(200);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(200);
        bottomGrid.getColumnConstraints().addAll(col1, col2);

        // Grievances box in bottom left
        VBox grievancesBox = new VBox(10);
        
        Label grievancesLabel = new Label("Grievances:");
        grievancesLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        grievancesArea = new TextArea();
        grievancesArea.setEditable(false);
        grievancesArea.setStyle("-fx-border-color: orange; -fx-background-color: white;");
        
        Button reloadGrievancesButton = new Button("Reload Grievances");
        reloadGrievancesButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        
        Button addGrievanceButton = new Button("Add Grievance");
        addGrievanceButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        grievancesBox.getChildren().addAll(grievancesLabel, grievancesArea, reloadGrievancesButton, addGrievanceButton);
        bottomGrid.add(grievancesBox, 0, 0);

        reloadGrievancesButton.setOnAction(e -> {
            grievancesArea.clear();
            grievancesArea.appendText(government.seeGrievence());
        });

        addGrievanceButton.setOnAction(e -> {
            TextInputDialog grievanceDialog = new TextInputDialog();
            grievanceDialog.setTitle("Add Grievance");
            grievanceDialog.setHeaderText("Submit a New Grievance");
            grievanceDialog.setContentText("Please enter your grievance:");
        
            grievanceDialog.showAndWait().ifPresent(grievanceText -> {
                if (!grievanceText.trim().isEmpty()) {
                    farmer.putGrivence(grievanceText);
                    showAlert("Grievance Added", "Your grievance has been successfully submitted.");
                } else {
                    showAlert("Input Required", "Grievance text cannot be empty.");
                }
            });
        });

        // Statistics box in bottom right
        VBox statsBox = new VBox(10);
        
        Label statsLabel = new Label("Your Stats:");
        statsLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        TextArea statsArea = new TextArea("Resources allocated to you:\n- Seeds\n- Fertilizer");
        statsArea.setEditable(false);
        statsArea.setStyle("-fx-border-color: orange; -fx-background-color: white;");
        
        statsBox.getChildren().addAll(statsLabel, statsArea);
        bottomGrid.add(statsBox, 1, 0);

        // Set the GridPane at the bottom of the BorderPane
        root.setBottom(bottomGrid);

        // Scene and Stage setup
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Normal Application - Farmer Dashboard");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
