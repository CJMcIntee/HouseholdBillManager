/*Programmer: Christine McIntee
  Date: August 13th 2023
  Program: Household Bill Manager
  Allows user to add/subtract bills, view total balance,
  view amount due per person, and print statement.*/

import java.util.*;
import java.io.*;
import java.time.LocalDate;

class BillManager {
   //Fields
   private String name; //name
   private int numP; //number of people in household
   private double bal; //total balance due
   private double duePer; //amount due per person
   private ArrayList<String> bills; //list of bill names
   
   //Construct BillManager with number of people and name
   public BillManager(int numP, String name) {
      this.numP = numP;
      this.name = name;
      bills = new ArrayList<String>();
   } //end constructor
   
   //View and make changes to bills and balance
   public void billMenu() throws FileNotFoundException {
      char option;
      Scanner input = new Scanner(System.in);
      line();
      if (numP - 1 == 1) {
         System.out.println("Welcome " + name + " and 1 other!");
      } else {
         System.out.println("Welcome " + name + " and " + (numP - 1) + " others!");
      }
      line();
      do {
         menuOptions();
         option = input.next().charAt(0);
         switch (option) {
            //Total balance
            case 't':
               totalBalance();
               break;
            //Due per person
            case 'd':
               duePerPerson();
               break;
            //Add bill
            case 'a':
               addBill(input);
               break;
            //Subtract bill
            case 's':
               subBill(input);
               break;
            //Print statement
            case 'p':
               printStatement();
               break;
            //Reset household data
            case 'r':
               resetData(input);
               break;
            //Logout and return to loginMenu method of ProfileManager
            case 'l':
               line();
               System.out.println("Goodbye " + name + "!");
               System.out.println("You have been logged out.");
               line();
               break;
            //Invalid commmand
            default:
               line();
               System.out.println("Invalid option. Try again.");
               line();
               break;
         }
      } while (option != 'l');
   } //end billMenu method
   
   //View total household balance due
   private void totalBalance() {
      line();
      if (bills.size() == 1) {
         System.out.println("There is 1 bill due this month:");
      } else {
         System.out.println("There are " + bills.size() + " bills due this month: ");
      }
      System.out.println(bills.toString());
      System.out.printf("Household Total Due: $%.2f %n", bal);
      duePer = bal / numP;
      line();
   } //end totalBalance method
   
   //Reset all household bill data
   private void resetData(Scanner input) {
      line();
      //Warn user that thier data will be erased and verify thier choice
      System.out.println("WARNING! This option will erase all ");
      System.out.println("bills you have added and reset your total ");
      System.out.println("household balance to zero. Enter \"yes\" to ");
      System.out.println("proceed or press any key to keep data: ");
      String reset = input.next();
      if (reset.equals("yes")) {
         bills.clear();
         bal = 0;
         System.out.println("Your household data has been rest!");
      }
      line(); 
   } //end resetData method
   
   //Calculate amount due per person 
   private void duePerPerson() {
      line();
      System.out.printf("$%.2f ", bal);
      System.out.printf("divided by %d people = ", numP);
      duePer = bal / numP;
      System.out.printf("$%.2f %n", duePer);
      line();
   } //end duePerPerson method
   
   //Add new bill
   private void addBill(Scanner input) {
      line();
      System.out.println("Enter bill name: ");
      String billName = input.next();
      System.out.println("Enter the total amount due: ");
      System.out.print("$ ");
      String amtBill = input.next();
      //Catch user input error
      try {
         double amtB = Double.parseDouble(amtBill);
         bal += amtB;
         bills.add(billName);
         System.out.println(billName + " added!");
      } catch (NumberFormatException w) {
         System.out.println("Error! Incorrect format.");
         System.out.println("Input amount as 0000.00");
      }
      line();
      duePer = bal / numP;
   } //end addBill method   
   
   //Subtract bill
   private void subBill(Scanner input) {
      line();
      if (bills.size() == 0) {
         System.out.println("There are no bills to delete.");
      //Remove bill name from array and amount from total balance
      } else {
         System.out.println("Enter bill name to be deleted: ");
         String billName = input.next();
         if (!bills.contains(billName)) {
            System.out.println("Bill with this name does not exist.");
            System.out.println("Current list of household bills: ");
            System.out.println(bills.toString());
            System.out.println("Enter bill name again (case sensitive): ");
            billName = input.next();
         } 
         System.out.println("Enter the bill amount to be deleted: ");
         System.out.print("$ ");
         String amtSub = input.next();
         //Catch user input error
         try {
            double amtS = Double.parseDouble(amtSub);
            bal -= amtS;
            bills.remove(billName);
            System.out.println(billName + " deleted!");
         } catch (NumberFormatException d) {
            System.out.println("Error! Incorrect format.");
            System.out.println("Input amount as 0000.00");
         }
      }
      line();
      duePer = bal / numP;
   } //end subBill method  
   
   //Save and print statement with ascii art to file
   private void printStatement() throws FileNotFoundException {
      line();
      File statement = new File("HouseBills.txt");
      PrintStream out = new PrintStream(statement);
      LocalDate date = LocalDate.now();
      out.println("   (Statement generated by " + name + " on " + date + ")");
      out.println("--------------------------------------------------------");
      out.printf("Bills due this month: %s %n", bills.toString());
      out.printf("Total household balance = $%5.2f %n", bal);
      out.printf("Amount due per person   = $%5.2f %n", duePer);
      out.println("--------------------------------------------------------");
      out.println("                         /                               ");
      out.println("                        ((                               ");
      out.println("                ________|_|__                            ");
      out.println("               /_)_)_)_)_)_)_\\                          ");
      out.println("              /_)_)_)_)_)_)_)_\\                         ");
      out.println("             /_)_)_)_)_)_)_)_)_\\                        ");
      out.println("              |    __   _____ |                          ");
      out.println("              |   |  |  |_|_| |        ___               ");
      out.println("              |   | .|        |       |_-_|              ");
      out.println("_______www____|___|__|________|__ww_____|________________");
      out.close();
      System.out.println("Statement printed!");
      System.out.println("Saved as HouseBills.txt");
      line();
   } //end printStatement method
   
   //Display command options
   private void menuOptions() {
      System.out.println("Select an option: ");
      System.out.println("  t) Total balance");
      System.out.println("  d) Due per person");
      System.out.println("  a) Add bill");
      System.out.println("  s) Subtract bill");
      System.out.println("  p) Print statement");
      System.out.println("  r) Reset data");
      System.out.println("  l) Logout");
   } //end menuOptions method
   
   //Prints a dotted line
   private void line() {
      System.out.println(".........................................");
   } //end line method
    
} //end BillManager class