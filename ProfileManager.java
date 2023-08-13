/*Programmer: Christine McIntee
  Date: August 13th 2023
  Program: Household Bill Manager
  Allows user to login to existing profile or create new profile.*/
  
import java.util.Scanner;
import java.io.*;

public class ProfileManager { 
   //Fields
   private BillManager home;
   private Scanner input;

   //Construct ProfileManager
   public ProfileManager() {
   } //end constructor
   
   //Ask user to load profile, create profile, or quit
   public static void loginMenu(Scanner input) throws FileNotFoundException {
      menuOptions();
      String option = input.nextLine();
      while (!option.equalsIgnoreCase("q")) {
         //Load profile
         if (option.equalsIgnoreCase("l")) {
            BillManager home = loadProfile(input);
            home.billMenu();
            menuOptions();
            option = input.nextLine();
         //Create profile
         } else if (option.equalsIgnoreCase("c")) {
            BillManager home = createProfile(input);
            home.billMenu();
            menuOptions();
            option = input.nextLine();
         //Invalid option
         } else {
            System.out.println("Invalid option. Try again.");
            option = input.nextLine();
         }
      //Quit program
      }
      System.out.println("Thank you for using our program.");
      System.out.print("Have a nice day!");
   } //end loginMenu method
   
    //Load previous BillManager profile
   public static BillManager loadProfile(Scanner input) 
      throws FileNotFoundException {
      System.out.println("Enter the file name where your profile is stored:");
      String profileS = input.nextLine();
      File profile = new File(profileS);
      //Check for valid file name
      while (!profile.isFile()) {
         System.out.println("Profile not found. \nPlease enter valid .txt file:");
         profileS = input.nextLine();
         profile = new File(profileS);
      }
      Scanner read = new Scanner(profile);
      String name = read.nextLine();
      int numP = read.nextInt();
      BillManager home = new BillManager(numP, name);
      System.out.println("Profile succesfully loaded!");
      return home;
   } //end loadProfile method
   
   //Create new BillManager profile
   public static BillManager createProfile(Scanner input)
      throws FileNotFoundException {
      System.out.println("What is your name?");
      String name = input.nextLine();
      System.out.println("How many people are in your household?");
      int numP = input.nextInt();
      System.out.println("Profile succesfully created!");
      System.out.println("Select an option: \n  s) Save profile");
      System.out.println("  c) Continue without saving");
      String save = input.nextLine();
      save = input.nextLine();
      //Save profile to .txt file
      if (save.equalsIgnoreCase("s")) {
         String fileS = name + ".txt";
         File saveFile = new File(fileS);
         PrintStream fileOut = new PrintStream(saveFile);
         fileOut.println(name);
         fileOut.print(numP);
         System.out.println("Profile succesfully saved as " + fileS + "!");
         fileOut.close();
      }
      BillManager home = new BillManager(numP, name);
      return home;
   } //end createProfile method
   
   //Displays menu command options
   private static void menuOptions() {
      System.out.println("Welcome to your Household Bill Manager!");
      System.out.println("Select an option:");
      System.out.println("  l) Load previous profile \n  c) Create new profile");
      System.out.println("  q) Quit");
   } //end menuOptions method
   
} //end ProfileManager class