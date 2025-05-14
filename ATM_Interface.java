
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATM_Interface
{
    public double balance = 10000.0;
    public LinkedList<String> transactionHistory = new LinkedList<>();

    //Method to check balance
    public void checkBalance()
    {

        System.out.println("Your Current Balance is: Rs\n" + balance);
    }

    // Method to deposit money
    public void deposit( double amount )
    {
        if(amount <= 0)
        {
            System.out.println("Invalid Deposit Amount");
            return;
        }
        balance=balance+amount;
        addTransaction("Credited Rs ", amount);
        System.out.println("Rs "+ amount + " Deposited Successfully.");
        System.out.println("Available balance is: Rs " + balance);
    }

    //Method to withdraw money
    public void withdraw(double amount)
    {
        if(amount <= 0 || amount > balance )
        {
            System.out.println("Invalid or Insufficient Balance");
            return;
        }
        else
        {
            balance = balance - amount;
            addTransaction("Debited Rs " , amount);
            System.out.println("Rs " + amount + " Withdrawn Successfully." );
            System.out.println("Now Available balance is: Rs " + balance);
        }
    }

    //Method to show mini statement
    public void showMiniStatement()
    {
        if( transactionHistory.isEmpty())
        {
            System.out.println("No Transactions found.");
        }
        else
        {
            System.out.println("\n*** Mini Statement ( Last 5 Transactions ) ***");
            for(String entry : transactionHistory)
            {
                System.out.println(entry);
            }
        }
    }

    //Method to add a transaction to history
    private void addTransaction(String action,double amount)
    {
        String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String entry = action + " Rs " + amount + " on " + timestamp;
        if(transactionHistory.size() == 5)
        {
            transactionHistory.removeFirst();
        }
        transactionHistory.add(entry);
    }

    //Ask user
    public static boolean askToContinue(Scanner s)
    {
        int input = -1;
        while(true)
        {
            System.out.println("\nWould you like to go back to home menu or you want to exit?");
            System.out.println("1: Home Menu");
            System.out.println("2: Exit");
            if(s.hasNextInt())
            {
                input = s.nextInt();
                if(input == 1 || input == 2)
                    break;
            }
            else
            {
                s.next();
            }
            System.out.println("Invalid Input. Please enter 1 or 2.");
        }
        if( input == 2)
        {
            System.out.println("\nThank you for banking with us!.");
            System.out.println("Have a great day ahead. GoodBye!");
        }
        return input == 1;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ATM_Interface atm = new ATM_Interface();

        //Login system
        String ValidAccountNumber = "123456789101";
        String ValidPin= "1234";
        System.out.println("***** Welcome to Anurima's Atm System *****");
        System.out.println("-------------------------------------------");
        System.out.println("Enter User Account Number: ");
        String user = s.nextLine();
        System.out.println("Enter PIN: ");
        String pin = s.nextLine();

        if(!user.equals(ValidAccountNumber) || !pin.equals(ValidPin))
        {
            System.out.println("Invalid Credentials. Access Denied");
            return;
        }
        System.out.println("\nLogin Successful!");
        System.out.println("-------------------");
        System.out.println("Welcome Anurima Goswami");

        boolean continueUsingATM = true;

        while(continueUsingATM)
        {
            System.out.println("\n****** ATM MENU ******");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Mini Statement");
            System.out.println("5. Exit\n");
            System.out.println("Kindly Enter your choice: ");

            while(!s.hasNextInt())
            {
                System.out.println("Please enter a valid number: ");
                s.next();
            }
             int choice = s.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;

                case 2:
                    System.out.println("Enter the amount to deposit: Rs");
                    while (!s.hasNextDouble()) {
                        System.out.println("Enter a valid amount: ");
                        s.next();
                    }
                    double deposit = s.nextDouble();
                    atm.deposit(deposit);
                    break;

                case 3:
                    System.out.println("Enter the amount to withdraw");
                    while (!s.hasNextDouble()) {
                        System.out.println("Enter a valid amount");
                        s.next();
                    }
                    double withdraw = s.nextDouble();
                    atm.withdraw(withdraw);
                    break;

                case 4:
                    atm.showMiniStatement();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. ByeBye!.");
                    continueUsingATM = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choice again.");
                    continue;
            }
            if (choice!=5)
            {
                continueUsingATM = askToContinue(s);
            }
        }
        s.close();
    }
}

