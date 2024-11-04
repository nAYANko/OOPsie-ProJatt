package myPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Government {
    public String announcement;

    public void viewFarmer(){
        File FarmerName= new File("farmerName.txt");
        File FarmerID= new File("farmerID.txt");
        try{
            Scanner nameReader = new Scanner(FarmerName);
            Scanner IdReader = new Scanner(FarmerID);
            int count = 1;
            while(nameReader.hasNextLine() && IdReader.hasNextLine()){
                System.out.println("\nDetails of Farmer "+count++);
                System.out.println("Name: "+nameReader.nextLine());
                System.out.println("Farmer ID : "+IdReader.nextLine()+"\n");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("\nFile Not Found!");
            return;
        }         
    }

    public void makeAnnouncement(String announcement){
        this.announcement=announcement;
        
        try( FileWriter announce = new FileWriter("Anouncements.txt",true)){
            announce.write(announcement + "\n");
        }catch( IOException e){
            System.out.println(e);
        }
    }

    public String seeGrievence(){
        StringBuilder grievances = new StringBuilder();
        File grievancesFile = new File("Grivence.txt");
    
        try (Scanner scanner = new Scanner(grievancesFile)) {
            while (scanner.hasNextLine()) {
                grievances.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            grievances.append("Error reading grievances: ").append(e.getMessage()).append("\n");
        }
    
        return grievances.toString();
    }
    public void sendMessage(String FarmerID,String message){
        String filepath = "C:\\Users\\hp\\Desktop\\mini_project-main\\chat\\" + FarmerID + ".txt";
        File file = new File(filepath);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            else{
                try{
                 FileWriter messageWriter = new FileWriter(filepath,true);
                 messageWriter.write("Government: " + message + "\n");
                 messageWriter.close();
                }catch (IOException e){
                 System.out.println(e);
                }
             }
        }catch (Exception e){
            System.out.println(e);
        }
        
    }
    public String seeMessage(String FarmerID) {
        StringBuilder messages = new StringBuilder(); // To store all messages
        try {
            String filepath = "C:\\Users\\hp\\Desktop\\mini_project-main\\chat\\" + FarmerID + ".txt";
            File messageFile = new File(filepath);
            Scanner reader = new Scanner(messageFile);
    
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                messages.append(currentLine).append("\n"); // Append each line to messages
            }
            
            if (messages.length() == 0) {
                return "Farmer " + FarmerID + " has posted nothing as of yet.";
            } else {
                return messages.toString(); // Return all messages as a single string
            }
            
        } catch (Exception e) {
            return "Error: " + e.getMessage(); // Return error message if exception occurs
        }
    }
    
    public String getAnouncement(){
        StringBuilder announcements = new StringBuilder();
        File announcementFile = new File("Anouncements.txt");
    
        try (Scanner scanner = new Scanner(announcementFile)) {
            while (scanner.hasNextLine()) {
                announcements.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            announcements.append("Error reading announcements: ").append(e.getMessage()).append("\n");
        }
    
        return announcements.toString();
    }
}
