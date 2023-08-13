/*Programmer: Christine McIntee
  Date: August 13th 2023
  Program: Household Bill Manager
  Login to program and manage household bills.*/

import java.util.Scanner;
import java.io.*;

public class HouseMain { 

   //Main method
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(System.in);
      ProfileManager profile = new ProfileManager();
      profile.loginMenu(input);
    } //end main method
     
} //end HouseMain class