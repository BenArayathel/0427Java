package com.example.presentations;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.example.models.Customer;
import com.example.operations.ServicesImpl;
import com.example.operations.UserInput;


public class MainMenu {
	/*This class displays the menu available to customers.
	 * It also performs the vast majority of validation checking.
	 */
	final static Logger superlog = Logger.getLogger(MainMenu.class);
	static ServicesImpl options = new ServicesImpl();
	public static void Menu(Customer c)
	{
		int I;
		do
		{
			System.out.println();
			superlog.setLevel(Level.INFO);
			superlog.info("Select one of the following options:");
			superlog.info("1. Create a new account");
			superlog.info("2. Delete an existing account");
			superlog.info("3. View current accounts and balances");
			superlog.info("4. Update account balances");
			superlog.info("5. Transfer an amount to someone else's account");
			superlog.info("6. Log out");
			String input = UserInput.getInput();
			while (!input.matches("[1-6]{1}"))
			{
				superlog.info("Invalid input, please re-enter 1-6 only.");
				input = UserInput.getInput();
			}
			I = Integer.parseInt(input);
			switch(I)
		{
			
			case 1 :
			
				options.newAccount(c);
				break;
				
			case 2:
				superlog.info("Your accounts are ");
				superlog.info(c.getAccounts());
				String acct;
				superlog.info("Please specify the name of the account you wish to delete.");
				acct = UserInput.getInput();
				options.deleteAccount(acct, c);
				break;
				
			case 3:
			
				superlog.info("Your accounts are: ");
				superlog.info(c.getAccounts());
				break;
				
			case 4:
				superlog.info("Transfer cash from one account to another?");
				input = UserInput.getInput();
				while (!input.equals("yes") && !input.equals("no"))
				{
					superlog.info("Please enter 'yes' or 'no' only.");
					input = UserInput.getInput();
				}
				if (input.equals("yes"))
				{
					superlog.info("Printing your accounts. "
							+ "Specify which one to transfer from");
					superlog.info(c.getAccounts());
					input = UserInput.getInput();
					while(!c.getAccounts().containsKey(input))
					{
						superlog.info("No such user account exists. "
						+ "Please check your account name and try again.");
						input = UserInput.getInput();
					}
					String account1 = input;
					superlog.info("Specify the amount to transfer");
					input = UserInput.getInput();
					while(!input.matches("[0-9]*.[0-9]{2}"))
					{
						superlog.info("Invalid input, please re-enter in 000.00 format.");
						input = UserInput.getInput();
					}
					Double D = Double.parseDouble(input);
					while ( D > c.getBalance(account1))
					{
						superlog.info("Amount to transfer must be lower "
								+ "than current balance");
						superlog.info("Current balance is " + c.getBalance(account1));
						input = UserInput.getInput();
						while(!input.matches("[0-9]*.[0-9]{2}"))
						{
							superlog.info("Invalid input, please re-enter in 000.00 format.");
							input = UserInput.getInput();
						}
						D = Double.parseDouble(input);
					}
					superlog.info("Specify account to transfer to.");
					input = UserInput.getInput();
					while(!c.getAccounts().containsKey(input))
					{
						superlog.info("No such user account exists. "
						+ "Please check your account name and try again.");
						input = UserInput.getInput();
					}
					String account2 = input;
					options.transferBalance(c, account1, account2, D);
					superlog.info("Your new balances are: " + c.getBalance(account1) 
					+ " for " + account1 +  " and " 
					+ c.getBalance(account2) + " for " + account2 + ".");
				}
				else
				{
					superlog.info("Printing your accounts."
					+ " Specify which one to update.");
					superlog.info(c.getAccounts());
					input = UserInput.getInput();
					while(!c.getAccounts().containsKey(input))
					{
						superlog.info("No such user account exists. "
						+ "Please check your account name and try again.");
						input = UserInput.getInput();
					}
					String Daccount = input;
					superlog.info("Enter 'd' to deposit or 'w' to withdraw.");
					input = UserInput.getInput();
					while(!input.equals("d") && !input.equals("w"))
					{
						superlog.info("Enter 'd' to deposit or 'w' to withdraw. Nothing else.");
						input = UserInput.getInput();
					}
					if (input.equals("w"))
					{
					superlog.info("Specify the amount to withdraw");
					input = UserInput.getInput();
					Double D = 0.0;
					while(!input.matches("[0-9].[0-9]{2}*"))
					{
						superlog.info("Invalid input, please re-enter in 000.00 format.");
						input = UserInput.getInput();
					}
					D = Double.parseDouble(input);
					while ( D > c.getBalance(Daccount))
					{
						superlog.info("Amount to transfer must be lower "
								+ "than current balance");
						superlog.info("Current balance is " + c.getBalance(Daccount));
						input = UserInput.getInput();
						while(!input.matches("[0-9]*.[0-9]{2}"))
						{
							superlog.info("Invalid input, please re-enter in 000.00 format.");
							input = UserInput.getInput();
						}
						D = Double.parseDouble(input);
					}
					options.updateAcc(c, Daccount, -D);
					}
					else
					{
						superlog.info("Specify the amount to deposit");
						input = UserInput.getInput();
						Double D = 0.0;
						while (!input.matches("[0-9]*.[0-9]{2}"))
						{
							superlog.info("Invalid input, please re-enter in 000.00 format.");
							input = UserInput.getInput();
						}
						D = Double.parseDouble(input);
						options.updateAcc(c, Daccount, D);
					}
					superlog.info("The new balance of " + Daccount + " is " + c.getBalance(Daccount));
				}
				
				break;
			case 5:
				superlog.info("Printing your accounts. "
						+ "Specify which one to transfer from");
				superlog.info(c.getAccounts());
				input = UserInput.getInput();
				while(!c.getAccounts().containsKey(input))
				{
					superlog.info("No such user account exists. "
					+ "Please check your account name and try again.");
					input = UserInput.getInput();
				}
				String account1 = input;
				superlog.info("Specify the amount to transfer");
				input = UserInput.getInput();
				while(!input.matches("[0-9]*.[0-9]{2}"))
				{
					superlog.info("Invalid input, please re-enter in 000.00 format.");
					input = UserInput.getInput();
				}
				Double D = Double.parseDouble(input);
				while ( D > c.getBalance(account1))
				{
					superlog.info("Amount to transfer must be lower "
							+ "than current balance");
					superlog.info("Current balance is " + c.getBalance(account1));
					input = UserInput.getInput();
					while(!input.matches("[0-9]*.[0-9]{2}"))
					{
						superlog.info("Invalid input, please re-enter in 000.00 format.");
						input = UserInput.getInput();
					}
					D = Double.parseDouble(input);
				}
				superlog.info("Specify account to transfer to.");
				String account2 = UserInput.getInput();
				c = options.transferTo(c, account1, account2, D);
				superlog.info("If account exists, amount will be transferred. "
						+ "There will be no charge otherwise.");
				superlog.info("The new balance of " +account1+ "is " + c.getBalance(account1));
				break;
			case 6:
				
				superlog.info("Logging out, thank you for visiting.");
				break;
				
			default:
			
				superlog.info("Please choose 1-6 only");
				break;
		}
			
		}while (I !=6 );
	}
}
