package week_6.q2_lakes;

import static input.InputUtils.*;
import java.util.HashMap;

import java.util.Scanner;




    /**
 
 This is the same question from Lab 4, but for this question, you'll refactor your code to
 use Lake objects to store the data.
 
 You are a runner, and you are in training for a race. You'd like to keep track of all of your
 times for your training runs. You only like to run around lakes. Here's some example data,
 
 Cedar, 45.15
 Cedar, 43.32
 Harriet, 49.34
 Harriet, 44.43
 Harriet, 46.22
 Como, 32.11
 Como, 28.14
 
 Write a program that enables you to enter the names of lakes and times, and store all of this
 data.
 
 You'll need to save every time entered for each lake.
 
 Create a Lake class to store all of the data for one lake. Create it in the same directory as this file.
 Add methods to this class to :
 
  - Adding a new time
  - Find and return the fastest time for this lake
  - Return a list of all of the times for that lake
 
 Save all of your Lake objects in a list or HashMap, so your program has a reference to every Lake.
 
 Your program should still work if you started running around another lake too (e.g. Lake of the Isles, or Lake Phalen).
 
 Your program should be able to analyze the data that you have stored, and print your fastest
 time for each lake you ran around. So, for the data above, your program will display something like
 
 Cedar, 43.32 minutes
 Harriet, 44.43 minutes
 Como, 28.14 minutes
 
 
 Your program should also be able to analyze the data that you have stored, and print your average
 time for each lake you ran around. So, for the data above, your program will display something like
 
 Cedar, 43.74 minutes
 Harriet, 44.43 minutes
 Como, 28.14 minutes
 
 Your program should be case-insensitive. So "Como" is the same lake as "como" or "COMO".
 
 
 Optional: You can write some tests for your Lake class. Finish the incomplete tests in
 In test/java/week_6/q2_lakes/Question_2_Lake_RunningTest.java
 
 Make sure you add your new java file(s) to your git repository so they are uploaded to GitHub.
 Otherwise, I won't get all of your code, and your code won't compile for the autograder.

 */


public class Question_2_Lake_Running {

        // TODO create a global data structure to store all of your Lake objects.
        // You methods will read and write to the Lakes in this data structure.

// create a map of lake name and lake object

        HashMap<String, Lake> lakes = new HashMap<>();

        Scanner keyboard = new Scanner(System.in);


        public void main(String[] args) {

            new Question_2_Lake_Running().running();


        }

        public void running() {




            do {

                String lakeName = stringInput("Enter lake name: ");

                double runTime = doubleInput("Enter time for running lake " + lakeName + ": ");

                addRunTime(lakeName, runTime);

            } while (moreLakes());

// Display fastest time for all lakes

            printFastestTimeForAllLakes();

// Display average time for all lakes

            printAverageTimeForAllLakes();

// Search for the fastest time for a Lake

            String searchFastest = stringInput("Enter a lake name to search for the fastest time");

            double fastest = fastestTimeForLake(searchFastest);

            System.out.println(String.format("The fastest time for %s is %f minutes", searchFastest, fastest));



// Search for the average time for a Lake

            String searchAverage = stringInput("Enter a lake name to calculate for the average time");

            double average = averageTimeForLake(searchAverage);

            System.out.println(String.format("The average time for %s is %f minutes", searchAverage, average));

        }



        private double doubleInput(String string) {

            System.out.print(string);

            double val = 0;

            try {

                val = Double.parseDouble(keyboard.next());

            }
            catch (NumberFormatException e) {

            }

            return val;

        }

        private String stringInput(String string) {

            System.out.print(string);

            return keyboard.next();

        }

        public void printFastestTimeForAllLakes() {

            for(String lakeName: lakes.keySet()) {

                System.out.println(lakeName + ", " + lakes.get(lakeName).getFastest() + " minutes");//getFastest

            }

        }





        public void printAverageTimeForAllLakes() {

            for(String lakeName: lakes.keySet()) {

                System.out.println(lakeName + ", " + lakes.get(lakeName).getAverage() + " minutes");//getAverage

            }

        }



        public double fastestTimeForLake(String lakeName) {

            lakeName = lakeName.toUpperCase();

            if(lakes.containsKey(lakeName)) {

                return lakes.get(lakeName).getFastest();

            }



            return -1;

        }



        public double averageTimeForLake(String lakeName) {

            lakeName = lakeName.toUpperCase();

            if(lakes.containsKey(lakeName)) {

                return lakes.get(lakeName).getAverage();

            }

            return -1;

        }



        public void addRunTime(String lakeName, double time) {

            lakeName = lakeName.toUpperCase();

            Lake lake = null;

            if(lakes.containsKey(lakeName)) {

                lake = lakes.get(lakeName);

            } else {

                lake = new Lake(lakeName);

                lakes.put(lakeName,lake);

            }

            lake.addTime(time);

        }



// You don't need to modify this method.

        private boolean moreLakes() {

            return yesNoInput("More lake data to add? (y/n)");

        }

        private boolean yesNoInput(String string) {

            System.out.print(string);

            boolean result = false;

            if(Character.toLowerCase(keyboard.next().charAt(0)) == 'y') {

                result = true;

            }

            return result;

        }

    }