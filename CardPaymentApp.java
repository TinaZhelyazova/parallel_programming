public class CardPaymentApp {
    public static void main(String[] args) {
        CardOperationsClass card = new CardOperationsClass(100.0);

        Thread depositThread1 = new Thread(() -> card.depositIntoCard(50.0), "Deposit Thread 1");
        Thread depositThread2 = new Thread(() -> card.depositIntoCard(75.0), "Deposit Thread 2");

        Thread withdrawThread1 = new Thread(() -> card.withdrawFromCard(25.0), "Withdraw Thread 1");
        Thread withdrawThread2 = new Thread(() -> card.withdrawFromCard(75.0), "Withdraw Thread 2");
        Thread withdrawThread3 = new Thread(() -> card.withdrawFromCard(200.0), "Withdraw Thread 3");

        depositThread1.start();
        withdrawThread3.start();
        depositThread2.start();
        withdrawThread2.start();
        withdrawThread1.start();
    }
}