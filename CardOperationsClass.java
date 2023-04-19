import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CardOperationsClass {
    private double balance;
    private double maximalOverdraft;
    private final Lock lock = new ReentrantLock();

    /*
     Getter fo the maximum overdraft.
     */
    public double getMaximalOverdraft() {
        return maximalOverdraft;
    }

    /*
     Setter for the maximum overdraft.
     - maximalOverdraft is the new amount of the overdraft.
     */
    public void setMaximalOverdraft(double maximalOverdraft) {
        this.maximalOverdraft = maximalOverdraft*(-1);
    }

    /*
     Class constructor
     */
    public CardOperationsClass(double initialBalance) {
        this.balance = initialBalance;
        this.setMaximalOverdraft(100);
    }

    /*
     The following method deposits money into the card for the threads to share.
     An Easter Egg has been added in case a thread wants to deposit a sus amount of cash.
     - amount is the amount of money to be deposited.
     */
    public void depositIntoCard(double amount) {
        lock.lock();
        try {
            if (amount >= 10000) {
                System.out.println("We are sorry but we will have to report you to the IRS so you can get SWAT-ed.\n" +
                        "Dear " + Thread.currentThread().getName() + ", your deposit has been blocked.");
            } else {
                System.out.println(Thread.currentThread().getName() + " is depositing " + amount + " euros.");
                balance = balance + amount;
                System.out.println("New balance after deposit is " + balance + " euros.");
            }
        } finally {
            lock.unlock();
        }
    }

    /*
     The following method withdraws money from the card based on 3 scenarios.
     The first one is if the balance is bigger than the amount,
     the second one is if we enter an overdraft
     and the third one is if the spending exceeds the maximum overdraft allowed.
     - amount is the amount of money to be withdrawn from the account
     */
    public void withdrawFromCard(double amount) {
        lock.lock();
        try {
            if (balance > amount) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount + " euros.");
                balance = balance - amount;
                System.out.println("New balance after withdrawal is " + balance + " euros.");
            } else if (balance - amount > -100 && balance - amount < 0) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount + " euros but is in overdraft.");
                balance = (double)Math.round((balance - amount)*105) / 100;
                System.out.println("New balance after withdrawal is " + balance + " euros and is accounted for the 5% tax");
            } else if (balance > 0) {
                System.out.println("Not enough balance for " + Thread.currentThread().getName() + " to withdraw " + amount + " euros.");
            } else {
                System.out.println("Not enough balance for " + Thread.currentThread().getName() + " to withdraw " + amount + " euros.");
                System.out.println("Dear customer, please pay up your debt to the bank, money ain't free.");
            }
        } finally {
            lock.unlock();
        }
    }
}