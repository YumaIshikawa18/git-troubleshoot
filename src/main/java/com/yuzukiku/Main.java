package com.yuzukiku;

// C1: skeleton game logic
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int maxNumber   = 100;  // ← problem2 で 100 に直す
        int maxAttempts = 5;   // ← problem2 で 5 に直す

        System.out.println("Welcome to GuessGame!"); // ← problem3/problem6 で装飾・追加
        System.out.println("Good luck!");
        System.out.println("⚡ [Hint] The secret is in [1–" + maxNumber + "] ⚡");
        System.out.println("[EXPERIMENTAL] Testing new UI");
        System.out.println("[EXPERIMENTAL] Debug mode enabled");
        System.out.println("[INFO] Enhanced game experience");
        System.out.println("[INFO] Improved user interface");

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
        // ← problem4 で bonus 機能追加
        try (Scanner bonus = new Scanner(new File("bonus.txt"))){
            while (bonus.hasNextLine()) {
                System.out.println(bonus.nextLine());
            }
        } catch (IOException e) {
            System.out.println("No bonus available.");
        }
    }
}
