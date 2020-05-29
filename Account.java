
public class Account {

	private int accountID;
	private String name;
	private double balance;

	public Account(int accountID) {
		this.accountID = accountID;
	}

	public Account(int accountID, String name, double balance) {
		this.accountID = accountID;
		this.name = name;
		this.balance = balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return this.balance;
	}

	public int getAccountID() {
		return this.accountID;
	}

	public String getName() {
		return this.name;
	}

	public void deposit(double amount) throws IllegalArgumentException {
		if (amount <= 0)
			throw new IllegalArgumentException("");
		else {
			System.out.printf("\nBalance before deposit : %,.2f Saudi Riyals", getBalance());
			System.out.printf("\nAmount deposited to Account: %,d Saudi Riyals\n", (int) amount);
			this.balance = this.balance + amount;
			System.out.printf("New Balance: %,.2f Saudi Riyals\n", getBalance());

		}

	}

	public void withdraw(double amount) throws IllegalArgumentException {
		if (amount <= 0)
			throw new IllegalArgumentException("");
		else if (amount > getBalance()) {
			System.out.printf("\nError: Insufficient balance. Balance available is only %,.2f SAR\n", getBalance());
		} else {
			System.out.printf("\nBalance before withdrawal : %,.2f Saudi Riyals", getBalance());
			System.out.printf("\nAmount withdrawn to Account: %,d Saudi Riyals\n", (int) amount);
			this.balance = this.balance - amount;
			System.out.printf("New Balance: %,.2f Saudi Riyals\n", getBalance());
		}
	}

	public String toString() {
		return accountID + "\t" + name + "\t\t" + balance;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (this.getClass() != obj.getClass())
			return false;
		else {
			Account acc = (Account) obj;
			return this.getAccountID() == acc.getAccountID();
		}

	}

}