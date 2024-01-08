import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankingSystemTest {

    private Bank bank;

    @Before
    public void setUp() {
        // Ініціалізуємо банк перед кожним тестом
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() {
        // Тест для перевірки створення рахунку
        BankAccount account = bank.createAccount("John", 100.0);
        assertNotNull(account);
        assertEquals("John", account.getAccountName());
        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    public void testTransferMoney() {
        // Тест для перевірки переказу грошей між рахунками
        BankAccount fromAccount = bank.createAccount("Alice", 200.0);
        BankAccount toAccount = bank.createAccount("Bob", 0.0);

        assertTrue(bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 100.0));

        assertEquals(100.0, fromAccount.getBalance(), 0.01);
        assertEquals(100.0, toAccount.getBalance(), 0.01);
    }

    @Test(expected = AccountNotFoundException.class)
    public void testTransferMoneyWithNonExistentAccount() {
        // Тест для перевірки переказу грошей з неіснуючого рахунку
        BankAccount fromAccount = bank.createAccount("Alice", 200.0);

        bank.transferMoney(fromAccount.getAccountNumber(), 999, 100.0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testTransferMoneyWithInsufficientFunds() {
        // Тест для перевірки переказу грошей з недостатньою сумою на рахунку
        BankAccount fromAccount = bank.createAccount("Alice", 50.0);
        BankAccount toAccount = bank.createAccount("Bob", 0.0);

        bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 100.0);
    }
}
