package com.yuzukiku;

// C1: skeleton game logic
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int maxNumber   = 100;
        int maxAttempts = 5;

        System.out.println("Welcome to GuessGame!");
        System.out.println("Good luck!");
        System.out.println("⚡ [Hint] The secret is in [1–" + maxNumber + "] ⚡");

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

        try (Scanner bonus = new Scanner(new File("bonus.txt"))){
            while (bonus.hasNextLine()) {
                System.out.println(bonus.nextLine());
            }
        } catch (IOException e) {
            System.out.println("No bonus available.");
        }
    }
}
