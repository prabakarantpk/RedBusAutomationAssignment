package com.basicJavaPrograms;

public class pramidPatternWithStars {

    public static void main(String[] args) {
        int n = 5;

        // Outer loop for number of rows
        for (int i = 0; i < n; i++) {
            // Inner loop for spaces
            for (int j = n - i; j > 1; j--) {
                System.out.print(" ");
            }
            // Inner loop for stars
            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }
            // Move to the next line after each row
            System.out.println();
        }

        // Print a blank line at the end

        System.out.println(" ");
    }
}
