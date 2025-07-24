import java.io.*;
import java.util.Scanner;


public class Main {
    static File file = new File("/Users/owen/Library/CloudStorage/OneDrive-UWEBristol/Personal Projects/ATM_Simulations/src/Accounts.txt");

    // Make the Account accessible and stay logged in
    static class Account {
        String username;
        String password;
        double balance;

        Account(String username, String password, double balance) {
            this.username = username;
            this.password = password;
            this.balance = balance;
        }
    }

    static void updateAccountInFile(Account updatedAccount) {
        try {
            File tempFile = new File("/Users/owen/Library/CloudStorage/OneDrive-UWEBristol/Personal Projects/ATM_Simulations/src/temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(" ");
                if (splits.length >= 3 && splits[0].equals(updatedAccount.username)) {
                    writer.write(updatedAccount.username + " " + updatedAccount.password + " " + updatedAccount.balance + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            reader.close();
            writer.close();

            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("Failed to rename temp file.");
                }
            } else {
                System.out.println("Failed to delete original file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // Looping the login process
        while (true) {

            System.out.println("Welcome to the Account Management");
            System.out.println("Do you have an Account? \nYes/No or Exit to quit");
            String AccountWith = scanner.nextLine();

            boolean account = false;

            // Exiting out of the loop instantly
            if (AccountWith.equalsIgnoreCase("Exit")) {
                System.out.println("Goodbye");
                break;
            }

            Account currentUser = null;

            // Finding Existing Accounts
            if (AccountWith.equalsIgnoreCase("Yes")) {
                System.out.println("Please Enter in your Username:");
                String username = scanner.nextLine();
                System.out.println("Please Enter in your Password:");
                String password = scanner.nextLine();
                System.out.println("Checking your details...");

                boolean foundUser = false;

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] splits = line.split(" ");
                        if (splits.length >= 3 && splits[0].equals(username)) {
                            if (splits[1].equals(password)) {
                                double balance = Double.parseDouble(splits[2]);
                                currentUser = new Account(username, password, balance);
                                foundUser = true;
                                break;
                            }
                        }
                    }
                    reader.close();

                    if (!foundUser) {
                        System.out.println("User not found \n");
                    } else {
                        System.out.println("Your account has been found \n");
                        account = true;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // Creating a New Account
            else if (AccountWith.equalsIgnoreCase("No")) {
                System.out.println("Please Enter in a Username:");
                String username = scanner.nextLine();
                System.out.println("Please Enter in a Password:");
                String password = scanner.nextLine();
                int value = 0;

                try {
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(username + " " + password + " " + value + "\n");
                    writer.close();
                    currentUser = new Account(username, password, value);
                    System.out.println("Your account has been added");
                    account = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Invalid input, please put Yes, No or Exit");
                continue;
            }


            // Account is Found
            if (account) {
                while (true) {

                    System.out.println("\n====== ATM Menu ======");
                    System.out.println("1. View balance +" +
                            "\n2. Deposit Money" +
                            "\n3. Withdraw Money" +
                            "\n4. Exit");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.printf("Your current Balance: £%.2f%n", currentUser.balance);
                            break;
                        case 2:
                            System.out.println("How much do you want to deposit?");
                            double deposit = scanner.nextDouble();
                            currentUser.balance += deposit;
                            updateAccountInFile(currentUser);
                            System.out.println("Deposit Successful");
                            break;
                        case 3:
                            System.out.println("How much do you want to withdraw?");
                            double withdraw = scanner.nextDouble();
                            if (withdraw > currentUser.balance) {
                                System.out.println("Insufficient Balance");
                            } else {
                                currentUser.balance -= withdraw;
                                updateAccountInFile(currentUser);
                                System.out.println("Withdraw Successful");
                            }
                            break;
                        case 4:
                            System.out.println("Goodbye");
                            return;

                        default:
                            System.out.println("Invalid choice");
                    }
                }
            }
        }
    }

}
