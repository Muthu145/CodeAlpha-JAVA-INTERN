import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class StudentGradeTracker{
    private static ArrayList<Integer> grades = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getChoice();
            handleChoice(choice);
        } while (choice != 6);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Student Grade Tracker ---");
        System.out.println("1. Add a student grade");
        System.out.println("2. Compute average grade");
        System.out.println("3. Find highest grade");
        System.out.println("4. Find lowest grade");
        System.out.println("5. Display all grades");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number between 1 and 6: ");
            scanner.next(); // Discard invalid input
        }
        return scanner.nextInt();
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addGrade();
                break;
            case 2:
                computeAverage();
                break;
            case 3:
                findHighestGrade();
                break;
            case 4:
                findLowestGrade();
                break;
            case 5:
                displayAllGrades();
                break;
            case 6:
                System.out.println("Exiting the program. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please select a number between 1 and 6.");
        }
    }

    private static void addGrade() {
        System.out.print("Enter the grade to add (0-100): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter an integer between 0 and 100: ");
            scanner.next(); // Discard invalid input
        }
        int grade = scanner.nextInt();
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
            System.out.println("Grade added successfully!");
        } else {
            System.out.println("Grade must be between 0 and 100.");
        }
    }

    private static void computeAverage() {
        if (grades.isEmpty()) {
            System.out.println("No grades available to compute average.");
            return;
        }
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum / grades.size();
        System.out.printf("Average grade: %.2f%n", average);
    }

    private static void findHighestGrade() {
        if (grades.isEmpty()) {
            System.out.println("No grades available to find the highest grade.");
            return;
        }
        int highest = Collections.max(grades);
        System.out.println("Highest grade: " + highest);
    }

    private static void findLowestGrade() {
        if (grades.isEmpty()) {
            System.out.println("No grades available to find the lowest grade.");
            return;
        }
        int lowest = Collections.min(grades);
        System.out.println("Lowest grade: " + lowest);
    }

    private static void displayAllGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades to display.");
            return;
        }
        System.out.println("Student Grades: " + grades);
    }
}