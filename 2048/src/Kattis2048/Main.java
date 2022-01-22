/*
Solution for the following:
https://open.kattis.com/problems/2048
 */

package Kattis2048;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] userInput = new String[5];

    public static void main(String[] args) {
        // Get input
        for (int i = 0; i < 5; i++) {
            userInput[i] = readInput();
        }


        // Check movement, Left, Up, Right, Down
        String[] result = new String[4];
        if (userInput[4].equals("0")) {
            result = getRows(userInput, false);
        } else if (userInput[4].equals("1")) {
            result = getColumns(userInput, false);
        } else if (userInput[4].equals("2")) {
            result = getRows(userInput, true);
        } else if (userInput[4].equals("3")) {
            result = getColumns(userInput, true);
        }

        // Output result
        for (String s: result) {
            System.out.println(s);
        }
    }

    public static String[] getRows(String[] userInput, boolean reverse) {

        ArrayList<String> rowResult = new ArrayList<>();
        String[] result = new String[4];

        for (int i = 0; i < 4; i++) {
            String[] row = userInput[i].split(" ");
            ArrayList<String> userInputRow = new ArrayList<>(Arrays.asList(row));
            if (reverse) {
                Collections.reverse(userInputRow);
                rowResult = calcRow(userInputRow);
                Collections.reverse(rowResult);
            } else {
                rowResult = calcRow(userInputRow);
            }

            // Convert to string
            result[i] = String.join(" ", rowResult);;
        }
        return result;

    }

    public static String[] getColumns(String[] userInput, boolean reverse) {

        ArrayList<String> columnResult = new ArrayList<>();
        ArrayList<ArrayList<String>> resultArray = new ArrayList<>();
        String[] result = new String[4];

        // Transpose array
        for (int i = 0; i < 4; i++) {
            ArrayList<String> userInputCol = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                userInputCol.add(userInput[j].split(" ")[i]);
            }
            if (reverse) {
                Collections.reverse(userInputCol);
                columnResult = calcRow(userInputCol);
                Collections.reverse(columnResult);
            } else {
                columnResult = calcRow(userInputCol);
            }
            resultArray.add(columnResult);
        }

        // Revert transpose
        for (int i = 0; i < 4; i++) {
            String temp = "";
            for (int j = 0; j < 4; j++) {
                if (j < resultArray.size()) {
                    temp += resultArray.get(j).get(i) + " ";
                } else {
                    temp += resultArray.get(j).get(i);
                }
            }
            result[i] = temp;
        }

        return result;

    }

    public static ArrayList<String> calcRow(ArrayList<String> inputRow){
        // Remove all empty cells
        while (inputRow.contains("0")) {
            inputRow.remove("0");
        }

        // If two neighbouring cells are equal, calculate new cell value
        for (int i = 0; i < inputRow.size()-1; i++) {
            if (inputRow.get(i).equals(inputRow.get(i+1))){
                int currentNum = Integer.parseInt(inputRow.get(i));
                int log2Value = (int) (Math.log(currentNum) / Math.log(2));
                inputRow.set(i, String.valueOf((int) Math.pow(2, log2Value + 1)));
                inputRow.remove(i+1);
            }
        }

        // Reconstruct rest of row with 0's
        while (inputRow.size() < 4) {
            inputRow.add("0");
        }

        return inputRow;
    }

    public static String readInput() {
        System.out.print("");
        String result = sc.nextLine();
        return result;
    }
}
