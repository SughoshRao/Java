/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print.invoice.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/**
 * Application generates Invoice for 3 purchased items.
 * @author Sughosh
 */
public class PrintInvoiceApp 
{
    /**
    * Main class, accepts 3 items with their price and quantity and prints Invoice.
    * 
    */
    public static void main(String[] args)
    {
        //Declare array variables
        ArrayList<String> item = new ArrayList<>();
        ArrayList<Integer> quant = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();

        //Accept inputs
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();  // getting a String value

        for(int i=1; i<4; i++)
        {
            System.out.println("Input name of item " + i + ":");
            item.add(input.nextLine());

            System.out.println("Input quantity of item " + i + ":");
            while (!input.hasNextInt()) // Discard non-int input and re-prompt
            {        
                input.nextLine();
                System.out.print("Please enter a number for quantity: ");
            }
            quant.add(Integer.parseInt(input.nextLine()));
            
            System.out.println("Input price of item " + i + ":");
            while (!input.hasNextDouble()) // Discard non-numeric input and re-prompt
            {        
                input.nextLine();
                System.out.print("Please enter a number for price: ");
            }
            price.add(Double.parseDouble(input.nextLine()));
        }
        
        //Generate random invoice number
        Random rnd = new Random();
        int invNum = 10000 + rnd.nextInt(90000);
        
        //Generate Date in reqd format
        LocalDateTime date = LocalDateTime.now();
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        //Output invoice
        System.out.println("Invoice for " + name);
        System.out.print(invNum + "\t\t\t\t");
        System.out.print("  \t\t  " + dateFormatted);
        System.out.println("");
        System.out.printf("%-30s%10s%10s%10s","Item","Quantity","Price","Total");
        System.out.println("");
        
        double subTotal=0;
        double totalAmt;
        for (int j=0; j<3; j++)
        {
            System.out.printf("%-30s",item.get(j));
            System.out.printf("%10d",quant.get(j));
            System.out.printf("%10.2f",price.get(j));
            System.out.printf("%10.2f",price.get(j)*quant.get(j));
            subTotal += price.get(j)*quant.get(j);
            System.out.println("");
        }
        System.out.printf("Subtotal %51.2f",subTotal);
        System.out.printf("\n7.25%% Sales Tax %44.2f\n",(subTotal*0.0725));
        totalAmt = subTotal+(subTotal*0.0725);
        System.out.printf("Total %54.2f\n",totalAmt);
    }
}