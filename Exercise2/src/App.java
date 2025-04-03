import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Scanner loginScanner = new Scanner(System.in);

        
        // System.out.println("Enter your username:");
        // String username_from_input = loginScanner.nextLine();

        // System.out.println("Enter your password:");
        // String password_from_input = loginScanner.nextLine();

        
        // File myfile = new File("accounts.txt");

        
        // if (!myfile.exists()) {
        //     System.out.println("File not found.");
        //     loginScanner.close(); 
        //     return; 
        // }

        // try (Scanner fileScanner = new Scanner(myfile)) {

        //     boolean accountexists = false;

            
        //     while (fileScanner.hasNextLine()) {
        //         String filedata = fileScanner.nextLine().trim(); 

                
        //         String[] accountData = filedata.split(",");
        //         if (accountData.length != 2) {
        //             continue; 
        //         }

        //         String username_from_file = accountData[0].trim(); 
        //         String password_from_file = accountData[1].trim(); 

                
        //         if (username_from_input.equals(username_from_file) && password_from_input.equals(password_from_file)) {
        //             accountexists = true;
        //             break; 
        //         }
        //     }

            
        //     if (accountexists) {
        //         System.out.println("Login successful");
        //     } else {
        //         System.out.println("Account does not exist");
        //     }

        // } catch (IOException e) {
        //     System.out.println("An error occurred while reading the file: " + e.getMessage());
        // } finally {
        //     loginScanner.close(); 
        // }


             DataScientist dataScientist1 = new DataScientist("John", null, null);

            System.out.println("My name is " + dataScientist1.name);
            System.out.println("His work is " + dataScientist1.getWork());
            System.out.println("He earns " + dataScientist1.getSalary());

            Researcher research1 = new Researcher("Jane", null, null);

            System.out.println("My name is " + research1.name);
            System.out.println("Her work is " + research1.getWork());
            System.out.println("She earns " + research1.getSalary());
    }
        
}

