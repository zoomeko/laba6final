package week_6.q3_coffee;

/**
 *
 *
 This is the same question as week 5, but you'll refactor your program to
 use Drink objects.
 
 Write a program that creates a sales report for a coffee shop.
 The coffee shop will use this at the end of every day to calculate sales, expenses, and profit.
 
 The coffee shop sells 12 different drinks. The name of each drink, the price the shop
 charges the customer, and how much it costs to make each drink, are saved in the file
 coffee_price_data.txt. It's in the root directory of this project.
 
 The data is in the format
 
 name;cost to make;price charged
 
 As in this example,
 
 Cappuccino;1.56;3.50
 
 So the cappuccino drink costs the coffee shop $1.56 to make, and they charge the customer $3.50.
 
 The file coffee_sales_data.txt contains the sales data for one day. This file is in the format
 
 name;number sold
 
 As in this example,
 
 Cappuccino;100
 
 The coffee shop sold 100 cappuccino drinks.
 
 Your program should read this data from coffee_price_data.txt, and coffee_sales_data.txt, and
 store it all of the data for each drink in one Drink object. One Drink object
 should store that drink's name, price, number sold, cost to make one drink, price one drink sold for.
 
 A Drink object should be able to calculate the total expenses to make all the drinks of that type sold;
 the total price all of that drink of that type sold for, and profit for that type of drink.
 
 You should deal with any file-related exceptions properly.
 
 Once you have gathered all the data, generate a report that will be written out to a new file called
 daily_sales_report.txt. For each drink, record the number of drinks sold, the total that it cost to
 make the total quantity of those drinks (expenses), and the total amount (revenue) spent by
 customers on that drink.
 
 So, for example, if the coffee shop sold 100 cappuccinos today, you'll write a line that says
 
 Cappuccino: Sold 100, Expenses $150.60, Revenue $350.00, Profit $190.40
 
 perhaps using this String formatting template...
 
 "%s: Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f"
 
 
 And a similar line for each of the drinks. The autograder is looking for this exact format.
 
 Write the drink data in the *same order* as found in the data files.
 
 At the bottom of the file, write the total expenses, total revenue, and total profit for all drinks,
 for example, like this,
 
 All Drinks: Total Sold 1000, Expenses $1000, Revenue $2500, Profit $1500
 
 You should use try-with-resources exception handling for both file reading, and file writing.
 
 Use methods to organize your code. The autograder will call the salesReport() method, and will examine
 the output file your program creates.   The instructor will assess the quality of your code and solution.
 
 You should probably write some extra helper methods for the sub-tasks of this problem.
 
 Optional: You can write your own unit tests for your Drink class methods.
 
 Test and comment your code.
 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Question_3_Coffee_Shop {
    
        public class Drink{
            String name;
            double profit;
            int number_sold;
            double cost_for_one;
            double one_sold_for;
            double expense;
            double revenue;
            public Drink(String Name,double cost,double sold) //class to store details of a drink
            {
                name=Name;
                cost_for_one=cost;
                one_sold_for=sold;
                profit=0;
                number_sold=0;
                expense=0;
                revenue=0;
            }
            public void calculateExpense() //method to calculate expense, revenue and profit for each drink
            {
                expense= cost_for_one*number_sold;
                revenue=one_sold_for*number_sold;
                profit= revenue-expense;
            }
        }

        public static void main(String[] args) {
            Question_3_Coffee_Shop q7 = new Question_3_Coffee_Shop();
            q7.salesReport();
        }



        public String price_data_file = "coffee_price_data.txt";
        public String sales_data_file = "coffee_sales_data.txt";

        public String output_report_file = "daily_sales_report.txt";

        public void salesReport() {

// Suggested outline of program.


            ArrayList<Drink> allDrinkData = readCoffeeDataFiles(price_data_file, sales_data_file); // TODO replace Object with the type of your data structure

            calculateExpensesForDrinks(allDrinkData);

            writeReportFile(allDrinkData, output_report_file);

        }

        public void calculateExpensesForDrinks(ArrayList<Drink> drinkData)
        {
            for(int i=0;i<drinkData.size();i++)
            {
                drinkData.get(i).calculateExpense();
            }
        }

        public ArrayList<Drink> readCoffeeDataFiles(String dataFile, String salesFile) {

            ArrayList<Drink> drinks= new ArrayList<>();
            BufferedReader br = null;
            FileReader fr = null;
            try {
                fr = new FileReader(dataFile);
                br = new BufferedReader(fr);
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    String[] arr= sCurrentLine.split(";");
                    Drink d= new Drink(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]));
                    drinks.add(d);
                }
                fr = new FileReader(salesFile);
                br = new BufferedReader(fr);
                while ((sCurrentLine = br.readLine()) != null) {
                    String[] arr= sCurrentLine.split(";");
                    for(int i=0;i<drinks.size();i++)
                    {
                        if(arr[0].equals(drinks.get(i).name))
                        {
                            drinks.get(i).number_sold=Integer.parseInt(arr[1]);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            return drinks;

        }

        public void writeReportFile(ArrayList<Drink> drinkData, String filename) {

// TODO finish this method.

// You may find this format String helpful
            String reportLineTemplate = "%s: Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f";
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(filename));
                int totalSold=0;
                double totalExpenses=0,totalRevenue=0,totalProfit=0;
                for(int i=0;i<drinkData.size();i++)
                {
                    totalSold+=drinkData.get(i).number_sold;
                    totalExpenses+=drinkData.get(i).expense;
                    totalRevenue+=drinkData.get(i).revenue;
                    totalProfit+=drinkData.get(i).profit;
                    out.write(String.format(reportLineTemplate, drinkData.get(i).name,drinkData.get(i).number_sold,drinkData.get(i).expense, drinkData.get(i).revenue,drinkData.get(i).profit));
                    out.newLine();
                }

                out.write(String.format("All Drinks: Total Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f",totalSold,totalExpenses,totalRevenue,totalProfit));

            }
            catch (IOException e)
            {
                System.out.println("Exception ");
            }
            finally
            {
                try {
                    if (out != null)
                        out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


        }


    }

