import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CardOperationsClass {
    private double balance;
    private double maximalOverdraft;
    private final Lock lock = new ReentrantLock();

    public double getMaximalOverdraft() {
        return maximalOverdraft;
    }

    public void setMaximalOverdraft(double maximalOverdraft) {
        this.maximalOverdraft = maximalOverdraft*(-1);
    }
    public CardOperationsClass(double initialBalance) {
        this.balance = initialBalance;
        this.setMaximalOverdraft(100);
    }

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
            } else {
                System.out.println("Not enough balance for " + Thread.currentThread().getName() + " to withdraw " + amount + " euros.\n" +
                        "Dear customer, please pay up your debt to the bank, money ain't free.");
            }
        } finally {
            lock.unlock();
        }
    }
}