package testing.example.bank;

public class BankAccount {

	private int id;
	private double balance = 0;
	private static int lastId = 0;

	public BankAccount() {
		this.id = ++lastId;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount: " + amount);
		}
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount: " + amount);
		}
		if (balance - amount < 0) {
			throw new IllegalArgumentException
				("Cannot withdraw " + amount + " from " + balance);
		}
		balance -= amount;
	}
}
