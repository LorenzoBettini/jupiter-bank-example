package testing.example.bank;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private Bank bank;

	@Before
	public void setup() {
		bank = new Bank();
	}

	@Test
	public void testOpenNewAccountShouldReturnAPositiveId() {
		int newAccountId = bank.openNewBankAccount(0);
		assertTrue("Unexpected non positive id: " + newAccountId, newAccountId > 0);
	}

	@Test
	public void testDepositWhenAccountIsNotFoundShouldThrow() {
		thrown.expect(NoSuchElementException.class);
		thrown.expectMessage("No account found with id: 1");
		bank.deposit(1, 10);
	}

	@Test
	public void testDepositWhenAccountIsFoundShouldNotThrow() {
		int newAccountId = bank.openNewBankAccount(10);
		bank.deposit(newAccountId, 5);
	}

	@Test
	public void testWithdrawWhenAccountIsNotFoundShouldThrow() {
		thrown.expect(NoSuchElementException.class);
		thrown.expectMessage("No account found with id: 1");
		bank.withdraw(1, 10);
	}

	@Test
	public void testWithdrawWhenAccountIsFoundShouldNotThrow() {
		int newAccountId = bank.openNewBankAccount(10);
		bank.withdraw(newAccountId, 5);
	}
}