/*
Solution for the following:
https://open.kattis.com/problems/reseto
 */

package KattisReseto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Integer> allInt = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static int kValue = 0;
    public static int N = 0;
    public static int K = 0;

    public static void main(String[] args) {
        // Input
        System.out.print("");
        String[] userInput = sc.nextLine().split(" ");
        N = Integer.parseInt(userInput[0]);
        K = Integer.parseInt(userInput[1]);

        int[] P = {0, 0};
        int[] allNumbers = new int[N-1];

        // Fill Array
        int j = 0;
        for (int i = 2; i < N+1; i++) {
            allNumbers[j] = i;
            j++;
        }

        // Main loop
        while (true) {
            P = findPrime(allNumbers);
            allNumbers = crossNum(allNumbers, P);

            // Check if all numbers are crossed
            int sum = 0;
            for (int x: allNumbers) {
                sum += x;
            }
            if (sum == 0){
                break;
            }
        }
    }

    static int[] crossNum(int[] inputArray, int[] prime) {
        int arrayLen = inputArray.length;
        for (int i = prime[0]; i < arrayLen; i+= prime[1]) {
            if (inputArray[i] != 0) {
                kValue += 1;
                if (kValue == K){
                    System.out.println(inputArray[i]);
                    System.exit(0);
                }
                inputArray[i] = 0;
            }
        }
        return inputArray;
    }

    static int[] findPrime(int[] inputArray) {
        int P = 0;
        int i = 0;
        while (P == 0) {
            P = inputArray[i];
            i ++;
        }
        return new int[]{i-1, P};
    }
}
