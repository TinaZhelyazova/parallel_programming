import java.util.Scanner;

class Bank
{
    // Initial custom balance
    int total = 100;

    // Money payment method. Pay only if total money are more or equal to the money needed for payment
    void payment(String name, int payment)
    {

        if (total >= payment)
        {
            System.out.println(name + " paid " + payment);
            total = total - payment;

            System.out.println(total);

        }

        // Else if the money requested for payment is more than the balance then deny transaction
        else {

            System.out.println(name + " you can not pay " + payment);
            System.out.println("your balance is: " + total);
            System.exit(0);

        }
    }
}

// Method - Withdraw method
// Called from ThreadWithdrawal class
// using the object of Bank class passed
// from the main() method
class ThreadPay extends Thread {

    Bank object;
    String name;
    int leva;

    // Constructor of this method
    ThreadPay(Bank ob, String name, int money)
    {
        this.object = ob;
        this.name = name;
        this.leva = money;
    }

    // run() method for thread
    public void run() { object.payment(name, leva); }
}
// Deposit method is called from ThreadDeposit class
// using the object of Bank class passed
// from the main method


    //public void run() { object.deposit(name, dollar); }

// Class 2
// Main class
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        //Scanner myInput = new Scanner( System.in );
        // Declaring an object of Bank class and passing the
        // object along with other parameters to the
        // ThreadWithdrawal and ThreadDeposit class. This
        // will be required to call withdrawn and deposit
        // methods from those class

        // Creating an object of class1
        Bank obj = new Bank();

        ThreadPay t1
                = new ThreadPay(obj, "Tina", 40);
        ThreadPay t2
                = new ThreadPay(obj, "Tina", 40);
        ThreadPay t3
                = new ThreadPay(obj, "Yanaki", 10);
        ThreadPay t4
                = new ThreadPay(obj, "Tina", 40);

        // When a program calls the start() method, a new
        // thread is created and then the run() method is
        // executed.

        // Starting threads created above
        t1.start();
        t2.start();
        t3.start();
        //t5.start();
    }
}
