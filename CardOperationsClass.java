import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CardOperationsClass {
    private double balance;
    private final Lock lock = new ReentrantLock();

    public CardOperationsClass(double initialBalance) {
        this.balance = initialBalance;
    }

    public void depositIntoCard(double amount) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is depositing " + amount + " euros.");
            balance = balance + amount;
            System.out.println("New balance after deposit is " + balance + " euros.");
        } finally {
            lock.unlock();
        }
    }

    public void withdrawFromCard(double amount) {
        lock.lock();
        try {
            if (balance < amount) {
                System.out.println("Not enough balance for " + Thread.currentThread().getName() + " to withdraw " + amount + " euros.");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount + " euros.");
            balance = balance - amount;
            System.out.println("New balance after withdrawal is " + balance + " euros.");
        } finally {
            lock.unlock();
        }
    }
}