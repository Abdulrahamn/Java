import java.io.*;
import java.util.*;

public class AccountDriver {

	public static void main(String[] args) throws IOException {
		Account[] Accounts = new Account[getLines()];
		creatArray(Accounts);
		
		int input = 0;
		Scanner kb = new Scanner(System.in);
		while (input != 4) {
			try {
				getMenu();
				input = kb.nextInt();
				if (input == 1) {
					displayAccounts();
					pressEnterKeyToContinue();
				} else if (input == 2) {
					deposit(Accounts);
					pressEnterKeyToContinue();
				} else if (input == 3) {
					withdraw(Accounts);
					pressEnterKeyToContinue();
				} else if (input == 4) {
					updateFile(Accounts);
				} else {
					System.out.println("Error: Wrong input.");
					pressEnterKeyToContinue();
				}
			} catch (InputMismatchException e) {
				System.out.print("\n" + e + "\n");
				kb.nextLine();
				pressEnterKeyToContinue();
			} catch ( IllegalArgumentException e) {
				System.out.println("\nError: amount <= 0");
				kb.nextLine();
				pressEnterKeyToContinue();
			}
		}

		kb.close();
	}

	private static void getMenu() {
		System.out.print("1. Display Account Info for all Accounts\n" + "2. Deposit\n" + "3. Withdraw\n" + "4. Exit\n"
				+ "\n" + "Please select your choice: ");
	}

	private static void pressEnterKeyToContinue() {
		Scanner kb = new Scanner(System.in);
		System.out.println("\nPress Enter key to continue...");
		kb.nextLine();
	}

	private static void displayAccounts() throws IOException {
		FileInputStream in = new FileInputStream("accounts.txt");
		Scanner fs = new Scanner(in);
		System.out.println("\nAccountID\tAccount Owner\t\tAccount Balance(SAR)\n");
		for (int i = 0; i < getLines(); i++) {
			int ID = fs.nextInt();
			String fname = fs.next();
			String lname = fs.next();
			String name = fname + " " + lname;
			double balance = fs.nextDouble();
			System.out.printf("  %-13d\t%-30s%-1.2f\n", ID, name, balance);
		}
		fs.close();
	}

	private static int getLines() throws IOException {
		FileInputStream in = new FileInputStream("accounts.txt");
		Scanner fs = new Scanner(in);
		int i = 0;
		while (fs.hasNextLine()) {
			i++;
			fs.nextLine();
		}
		fs.close();
		return i;
	}

	private static void deposit(Account[] accountsArray) throws IOException {
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter accountID: ");
		int inputID = kb.nextInt();
		System.out.print("Please enter deposit amount (SAR): ");
		double inputAmount = kb.nextDouble();
		Account tempAccount = new Account(inputID);
		if (found(inputID)) {
			for (int i = 0; i < accountsArray.length; i++) {
				if (accountsArray[i].equals(tempAccount)) {
					accountsArray[i].deposit(inputAmount);
					if (inputAmount > 0) {
						updateFile(accountsArray);
						System.out.print("\nAccounts file has been updated ...");
					}
				}
			}
		} else
			System.out.println("\nError: Invalid account number");
	}

	private static void withdraw(Account[] accountsArray) throws IOException {
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter accountID: ");
		int inputID = kb.nextInt();
		System.out.print("Please enter withdraw amount (SAR): ");
		double inputAmount = kb.nextDouble();
		Account tempAccount = new Account(inputID);
		if (found(inputID)) {
			for (int i = 0; i < accountsArray.length; i++) {
				if (accountsArray[i].equals(tempAccount)) {
					accountsArray[i].withdraw(inputAmount);
					if (inputAmount < accountsArray[i].getBalance() && inputAmount > 0) {
						updateFile(accountsArray);
						System.out.print("\nAccounts file has been updated ...");
					}
				}
			}
		} else
			System.out.println("\nError: Invalid account number");

	}

	private static void updateFile(Account[] accountsArray) throws IOException {
		PrintWriter fo = new PrintWriter("accounts.txt");
		for (int i = 0; i < accountsArray.length - 1; i++) {
			fo.println(accountsArray[i].toString());
		}
		fo.print(accountsArray[accountsArray.length - 1].toString());
		fo.close();
	}

	private static boolean found(int ID) throws IOException {
		FileInputStream in = new FileInputStream("accounts.txt");
		Scanner fs = new Scanner(in);
		for (int i = 0; i < getLines(); i++) {
			if (fs.nextInt() == ID) {
				fs.close();
				return true;
			}
			fs.nextLine();
		}
		fs.close();
		return false;
	}
	private static void creatArray(Account [] Accounts) throws IOException{
		FileInputStream in = new FileInputStream("accounts.txt");
		Scanner fs = new Scanner(in);
		for (int k = 0; k < Accounts.length; k++) {
			Accounts[k] = new Account(fs.nextInt(), fs.next() + " " + fs.next(), fs.nextDouble());
		}
		fs.close();
	}
}
