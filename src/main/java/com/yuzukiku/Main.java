package com.yuzukiku;

// C1: skeleton game logic
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int maxNumber   = 100;
        int maxAttempts = 5;

        System.out.println("Welcome to GuessGame!");

        Random rand = new Random();
        int secret  = rand.nextInt(maxNumber) + 1;
        Scanner sc  = new Scanner(System.in);

        for (int i = 1; i <= maxAttempts; i++) {
            System.out.print("Try #" + i + ": ");
            int guess = Integer.parseInt(sc.nextLine());
            if (guess == secret) {
                System.out.println("🎉 Correct!");
                return;
            }
            if (guess < secret) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }

        System.out.println("Game over. The number was: " + secret);

    }
}
