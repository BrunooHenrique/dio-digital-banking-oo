import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount implements IBankAccount {
    private static int DEFAULT_AGENCY = 1;
    private static int SEQUENTIAL = 1;

    protected List<String> extract = new ArrayList<>();
    private int agency;
    private int accountNumber;
    private double balance;


    public BankAccount() {
        this.agency = DEFAULT_AGENCY;
        this.accountNumber = SEQUENTIAL++;
    }

    @Override
    public void deposit(double value) {
        balance += value;
        this.extract.add("+" + value);
    }

    @Override
    public void withdraw(double value) {
        if (this.getBalance() >= value) {
            this.withdraw(value);
            this.extract.add("-" + value);
        }
    }

    @Override
    public void transfer(double value, BankAccount destinationAccount) {
        if (this.getBalance() >= value) {
            this.withdraw(value);
            destinationAccount.deposit(value);
            this.extract.add("-" + value);
        }
    }

    protected void printCommonInfo() {
        System.out.println("-----------------------");
        System.out.println(String.format("Agency: %d", getAgency()));
        System.out.println(String.format("Account Number: %d", getAccountNumber()));
        System.out.println(String.format("Balance: %.2f", getBalance()));
        extract.forEach(System.out::println);
    }

    public int getAgency() {
        return agency;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

}
