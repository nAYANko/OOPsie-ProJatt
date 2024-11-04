import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import myPackage.Farmer;
import myPackage.Government;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


// Load DM Serif Text font at the beginning of start method



public class FarmManagementAppAdmin extends Application {

    private Government government = new Government();

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Farm Management System - Admin");
        Font.loadFont(getClass().getResourceAsStream("\"C:\\Users\\hp\\Downloads\\DM_Serif_Text,Wallpoet\\DM_Serif_Text\\DMSerifText-Regular.ttf\""), 14);
        // Main layout with white background
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-image: url('bg-image3.png'); " +
                     "-fx-background-size: cover; " +
                     "-fx-background-position: center; " +
                     "-fx-background-repeat: no-repeat;");


        // Top left quadrant - Logo and announcements section
        VBox topLeft = new VBox(10);
        topLeft.setPadding(new Insets(10));

        // Logo (Placeholder, minimal size)
        // Load the image from the file
        Image logoImage = new Image("file:satyamev jayate.png"); // Ensure the path is correct

        // Create an ImageView with the specified dimensions
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(75);
        logoImageView.setFitHeight(75);
        logoImageView.setPreserveRatio(true); // Maintain the aspect ratio

        // Add the ImageView to the topLeft container
        topLeft.getChildren().add(logoImageView);


        // Post Announcements
        Button postAnnouncementButton = new Button("Post Announcements");
        postAnnouncementButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        
        TextField announcementInput = new TextField();
        announcementInput.setPromptText("Type announcement here...");
        announcementInput.setStyle("-fx-border-color: orange; -fx-background-color: white;");

        // Display existing announcements
        Label announcementsLabel = new Label("Existing Announcements:");
        announcementsLabel.setStyle("-fx-background-color: white; -fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        TextArea announcementsBox = new TextArea();
        announcementsBox.setEditable(false);
        announcementsBox.setStyle("-fx-border-color: orange; -fx-background-color: white;");

        Button reloadAnnouncementsButton = new Button("Reload Announcements");
        reloadAnnouncementsButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        topLeft.getChildren().addAll(postAnnouncementButton, announcementInput, announcementsLabel, announcementsBox, reloadAnnouncementsButton);

        // Bottom left quadrant - Grievances
        VBox bottomLeft = new VBox(10);
        bottomLeft.setPadding(new Insets(10));
        
        Label grievancesLabel = new Label("View Grievances:");
        grievancesLabel.setStyle("-fx-background-color: white; -fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");
        
        TextArea grievancesBox = new TextArea();
        grievancesBox.setEditable(false);
        grievancesBox.setStyle("-fx-border-color: orange; -fx-background-color: white;");

        Button reloadGrievancesButton = new Button("Reload Grievances");
        reloadGrievancesButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        bottomLeft.getChildren().addAll(grievancesLabel, grievancesBox, reloadGrievancesButton);

        // Top right quadrant - Stats
        VBox topRight = new VBox(10);

        // Set padding with a top margin of 75px, and 10px for other sides
        topRight.setPadding(new Insets(75, 10, 10, 10));

        Label statsLabel = new Label("Stats:");
        statsLabel.setStyle("-fx-background-color: white; -fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");

        TextArea statsBox = new TextArea("Resources allocated to each farmer\n- Farmer 1: Seeds\n- Farmer 2: Fertilizer");
        statsBox.setEditable(false);
        statsBox.setStyle("-fx-border-color: orange; -fx-background-color: white;");

        topRight.getChildren().addAll(statsLabel, statsBox);


        // Layout organization
        BorderPane.setMargin(topLeft, new Insets(10));
        BorderPane.setMargin(bottomLeft, new Insets(10));
        BorderPane.setMargin(topRight, new Insets(10));
        mainLayout.setLeft(new VBox(topLeft, bottomLeft));
        

        // Button actions
        postAnnouncementButton.setOnAction(e -> {
            String announcement = announcementInput.getText();
            government.makeAnnouncement(announcement);
            announcementInput.clear();
            announcementsBox.appendText("Posted: " + announcement + "\n");
        });

        reloadAnnouncementsButton.setOnAction(e -> {
            // Clear existing text in announcementsBox
            announcementsBox.clear();
            // Retrieve and display announcements
            announcementsBox.setText(government.getAnouncement());
        });
        
        reloadGrievancesButton.setOnAction(e -> {
            // Clear existing text in grievancesBox
            grievancesBox.clear();
            // Retrieve and display grievances
            grievancesBox.setText(government.seeGrievence());
        });

        // Scene settings
        Scene scene = new Scene(mainLayout, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

       // Bottom right quadrant - Messaging
        VBox bottomRight = new VBox(10);
        bottomRight.setPadding(new Insets(10));

        // Label for the Farmer ID input
        Label farmerIdLabel = new Label("Enter Farmer ID:");
        farmerIdLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");

        // TextField to input Farmer ID
        TextField farmerIdInput = new TextField();
        farmerIdInput.setPromptText("Farmer ID");

        // Label for Messages section
        Label messagesLabel = new Label("Messages:");
        messagesLabel.setStyle("-fx-text-fill: orange; -fx-font-family: 'DM Serif Text'; -fx-font-size: 14px; -fx-font-weight: bold;");

        // TextArea to display messages
        TextArea messagesBox = new TextArea();
        messagesBox.setEditable(false);
        messagesBox.setStyle("-fx-border-color: orange; -fx-background-color: white;");

        // TextField for typing new message
        TextField newMessageField = new TextField();
        newMessageField.setPromptText("Type your message here...");

        // Button to send the message
        Button sendMessageButton = new Button("Send Message");
        sendMessageButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        // Button to reload messages
        Button reloadMessagesButton = new Button("Reload Messages");
        reloadMessagesButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        // Reload messages when the button is pressed
        reloadMessagesButton.setOnAction(e -> {
            messagesBox.clear(); // Clear current messages

            // Fetch messages from the backend for the specified Farmer ID
            String farmerID = farmerIdInput.getText();
            if (!farmerID.isEmpty()) {
                String fetchedMessages = government.seeMessage(farmerID); // Call backend method
                messagesBox.appendText(fetchedMessages); // Display fetched messages
            } else {
                messagesBox.appendText("Please enter a Farmer ID.\n");
            }
        });

        // Send a message when the button is pressed
        sendMessageButton.setOnAction(e -> {
            String farmerID = farmerIdInput.getText();
            String message = newMessageField.getText();
            if (!farmerID.isEmpty() && !message.isEmpty()) {
                government.sendMessage(farmerID, message); // Call backend method to send message
                newMessageField.clear(); // Clear the input field after sending
                messagesBox.appendText("Message sent.\n"); // Optional confirmation in messages box
            } else {
                messagesBox.appendText("Please enter both Farmer ID and a message.\n");
            }
        });

        // Add all components to the bottom right quadrant
        bottomRight.getChildren().addAll(farmerIdLabel, farmerIdInput, messagesLabel, messagesBox, newMessageField, sendMessageButton, reloadMessagesButton);
        

        

        // Adding the bottomRight quadrant to the layout
        mainLayout.setRight(new VBox(topRight,bottomRight));

    }
    
}
