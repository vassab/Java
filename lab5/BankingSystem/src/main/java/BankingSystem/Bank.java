import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(String accountName, double initialDeposit) {
        int accountNumber = generateAccountNumber();
        BankAccount account = new BankAccount(accountNumber, accountName, initialDeposit);
        accounts.put(accountNumber, account);
        return account;
    }

    public BankAccount findAccount(int accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        return account;
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount) {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    private int generateAccountNumber() {
        return accounts.size() + 1;
    }
}
