package com.basicJavaPrograms;

public class diamondPatternWithStars {

    public static void main(String[] args) {
        int n = 5;

        // Upper part of the diamond
        for (int i = 0; i < n; i++) {
            printRow(n, i);
        }

        // Lower part of the diamond
        for (int i = n - 2; i >= 0; i--) {
            printRow(n, i);
        }

        // Print a blank line at the end
        System.out.println();
    }

    private static void printRow(int n, int i) {
        // Print spaces
        for (int j = 0; j < n - i - 1; j++) {
            System.out.print(" ");
        }
        // Print stars
        for (int k = 0; k <= i; k++) {
            System.out.print("* ");
        }
        // Move to the next line
        System.out.println();
    }
}