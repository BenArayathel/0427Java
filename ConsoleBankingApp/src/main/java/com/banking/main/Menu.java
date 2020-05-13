package com.banking.main;

import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;
import com.banking.models.Employee;
import com.banking.models.Transaction;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import com.banking.service.EmployeeService;
import com.banking.service.TransactionService;
import com.banking.service.impl.AccountServiceImpl;
import com.banking.service.impl.CustomerServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.banking.service.impl.EmployeeServiceImpl;
import com.banking.service.impl.TransactionServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Menu {

    boolean quit = false;
    final static Logger loggy = Logger.getLogger(Menu.class);

    private static void displayBalance(Customer customer) throws NullPointerException{

        List<Account> accountList = customer.getAccounts();
        for (int i = 0; i < accountList.size();i++){
            Account account = accountList.get(i);
            loggy.info(customer.getUsername() + " balance for account: " + account.getId() + " is currently: $" + account.getBalance());
        }

    }

    private static void displayDeposit(Customer customer) throws BusinessException {
        loggy.info("\n------------");
        loggy.info("Deposit Menu");
        loggy.info("------------");
        loggy.info("How much would you like to deposit?");
        Scanner kb = new Scanner(System.in);
        Account account = customer.getAccounts().get(0);
        try{
            double amount = Double.parseDouble(kb.nextLine());
            account.deposit(amount);
            AccountService service = new AccountServiceImpl();
            account = service.updateBalance(account);
            customer.setAccount(account);
            displayBalance(customer);
        } catch(NumberFormatException e){
            loggy.info("Wrong input format.");
        }

    }

    private static void displayCustomerMenu(){
        loggy.info("\n--------------");
        loggy.info("Customer Menu");
        loggy.info("--------------");
        loggy.info("1) Check Balance");
        loggy.info("2) Deposit");
        loggy.info("3) Withdraw");
        loggy.info("4) Transfer funds to another M & B Customer");
//        loggy.info("5) Transfer funds to another Account");
//        loggy.info("6) Create new account")
        loggy.info("0) Log out");
    }

    private static void displayEmployeeLogin() throws BusinessException {
        loggy.info("\n--------------");
        loggy.info("Employee Login");
        loggy.info("--------------\n");
        loggy.info("Please enter your username");
        Scanner kb = new Scanner(System.in);
        Employee employee = new Employee();

        employee.setUsername(kb.nextLine());

        loggy.info("Please enter your password");

        employee.setPassword(kb.nextLine());
        EmployeeService service = new EmployeeServiceImpl();
        employee = service.employeeLogin(employee);

        // if successful take them to displayCustomerMenu
        if(employee.getId() == null){
            throw new BusinessException("Log in credentials incorrect.");
        }
    }

    private static Customer displayCustomerLogin() throws BusinessException {
        loggy.info("--------------");
        loggy.info("Customer Login");
        loggy.info("--------------\n");
        loggy.info("Please enter your username");
        Scanner kb = new Scanner(System.in);
        Customer customer = new Customer();

        customer.setUsername(kb.nextLine());

        loggy.info("Please enter your password");
        customer.setPassword(kb.nextLine());
        try {
            CustomerService service = new CustomerServiceImpl();
            customer = service.customerLogin(customer);
            if(customer == null){
                throw new BusinessException("Log in credentials incorrect.");
            }

            return customer;
        } catch(BusinessException e){
            loggy.error(e.getMessage());
        }
        // if successful take them to displayCustomerMenu
        throw new BusinessException("Log in credentials incorrect. Please try again.");
    }

    private static void displayWithdrawalMenu(Customer customer) throws BusinessException {
        loggy.info("------------");
        loggy.info("Withdraw Menu");
        loggy.info("------------");
        loggy.info("How much would you like to withdraw?");
        Scanner kb = new Scanner(System.in);
        Account account = customer.getAccounts().get(0);
        try{
            double amount = Double.parseDouble(kb.nextLine());
            account.withdraw(amount);
            AccountService service = new AccountServiceImpl();
            account = service.updateBalance(account);
            customer.setAccount(account);
            displayBalance(customer);
        } catch(NumberFormatException e){
            loggy.info("Wrong input format.");
        }

    }

    private static void displayCustomerSignUpMenu() throws BusinessException {
        // Make a new pendingCustomer account with the customer's details

        Scanner kb = new Scanner(System.in);
        Customer customer = new Customer();
        CustomerService service = new CustomerServiceImpl();


        loggy.info("----------------------");
        loggy.info("New Customer Sign Up");
        loggy.info("----------------------");

        // Enter your personal details please.
        loggy.info("Please enter your desired username.");

        customer.setUsername(kb.nextLine());

        loggy.info("Please enter your desired password");
        customer.setPassword(kb.nextLine());

        Account account = new Account();
        account.setBalance(0.0);
        customer.setAccount(account);

        //TODO Validate inputs

        Customer pendingCustomer = service.createCustomer(customer);
        if(pendingCustomer.getId() != null){
            loggy.info("Thank you for your application. See a Monet & Bagges employee for more information on the approval process.");
        }

    }

    private static void displayEmployeeMenu(){
        loggy.info("--------------");
        loggy.info("Employee Menu");
        loggy.info("--------------");
        loggy.info("1) Look up customer account by account ID");
        loggy.info("2) Look up a customer account by username");
        loggy.info("3) View all customer accounts");
        loggy.info("4) View all transactions");
//        loggy.info("4) Delete a customer's account");


    }

    private static void displayGreeting(){
        loggy.info("--------------------------------------");
        loggy.info("Welcome to Monet & Bagges LLC Inc ATM");
        loggy.info("--------------------------------------");
    }

    private static void displayLoginMain(){
        loggy.info("Please choose your login.");
        loggy.info("1) Customer Login");
        loggy.info("2) Employee Login");
        loggy.info("3) Sign up for a new customer account");
        loggy.info("0) To exit application at any time");
    }

    private static void displayLogout(){
        loggy.info("Would you like to log out?");
        loggy.info("1) Yes");
        loggy.info("2) No");

    }
    private static void displayHomeMenu(){
        displayGreeting();
        displayLoginMain();
    }

    private int getInput(int numOfChoices){
        int choice = -1;
        Scanner kb = new Scanner(System.in);
        while(choice < 0 || choice > numOfChoices ){
            try {
                System.out.print("\nPlease enter your selection: ");
                choice = Integer.parseInt(kb.nextLine());
            }
            catch (NumberFormatException e){
                loggy.info("Invalid selection. Please try again.");
            }
        }
        return choice;
    }
    public void runCustomerMenu(Customer customer) throws BusinessException {
        while(!quit){
            displayCustomerMenu();
            int choice = getInput(6);
            handleCustomerMenu(choice, customer);
        }
    }
    public void runEmployeeMenu(){
        while(!quit){
            displayEmployeeMenu();
            int choice = getInput(5);
            handleEmployeeMenu(choice);
        }
    }
    public void runHomeMenu(){
        while(!quit){
            displayHomeMenu();
            int choice = getInput(4);
            handleHomeMenu(choice);
        }
    }

    public void handleEmployeeMenu(int choice){
        //Logic for the Employee Sub-menu options

        switch(choice){
            case 0:
                //exit options
                logoutMenu();
                break;
            case 1:
                //Check customer account by user Id
                try {
                    displayFindCustomerByIdMenu();
                } catch (BusinessException e) {
                    loggy.error(e.getMessage());
                }
                break;
            case 2:
                //Check customer account by username
                try{
                    displayFindCustomerByUsernameMenu();
                } catch(BusinessException e){
                    loggy.error(e.getMessage());
                }
                break;
            case 3:
                //Display all Customer accounts
                try {
                    displayAllCustomerAccounts();
                }catch (BusinessException e){
                loggy.error(e.getMessage());
            }
                break;
            case 4:
                //View all transactions
                try{
                    displayAllTransactionsMenu();
                }catch(BusinessException e){
                    loggy.error(e.getMessage());
                }
                break;
            default:
                loggy.info("Invalid selection, please try again.");
                break;
        }


    }

    private void displayAllTransactionsMenu() throws BusinessException {

        loggy.info("---------------------------------");
        loggy.info("All Monet & Bagges Transactions");
        loggy.info("---------------------------------");

        TransactionService service = new TransactionServiceImpl();
        List<Transaction> transactionList = service.getAllTransactions();


        for (Transaction transaction : transactionList) {
            loggy.info("Transaction ID: " + transaction.getId() + " | Sender ID: " + transaction.getSender().getId() +
                    " | Receiver ID: " + transaction.getReceiver().getId() + " | Amount: $" + transaction.getAmount());
        }


    }

    private void displayAllCustomerAccounts() throws BusinessException{
        loggy.info("--------------------------");
        loggy.info("All Monet & Bagges Users");
        loggy.info("--------------------------");

        try{

            CustomerService service = new CustomerServiceImpl();
            List<Customer> customerList = service.getAllCustomers();


            for (Customer customer : customerList) {
                loggy.info("Username: " + customer.getUsername() + " | UserID: " + customer.getId() +
                        " | Account: " + customer.getAccounts().get(0).getId() + " | Balance: " +
                        customer.getAccounts().get(0).getBalance());
            }


        } catch(NumberFormatException | BusinessException e){
            loggy.error(e.getMessage());
        }



    }

    private void displayFindCustomerByUsernameMenu() throws BusinessException {

        loggy.info("---------------------------");
        loggy.info("Find Customer By Username");
        loggy.info("---------------------------");
        loggy.info("Please enter the Username of the account you wish to find: ");
        Scanner kb = new Scanner(System.in);
        Customer customer = null;

        try{
            String username = kb.nextLine();

            CustomerService service = new CustomerServiceImpl();
            customer = service.getCustomerByUsername(username);
            displayBalance(customer);

        } catch(NullPointerException | NumberFormatException | BusinessException e){
            loggy.error(e.getMessage());
        }

    }

    private void displayFindCustomerByIdMenu() throws BusinessException {

        loggy.info("------------");
        loggy.info("Find Customer By Id");
        loggy.info("------------");
        loggy.info("Please enter the UserID of the account you wish to find: ");
        Scanner kb = new Scanner(System.in);
        Customer customer = null;

        try{
            String id = kb.nextLine();

            CustomerService service = new CustomerServiceImpl();
            customer = service.getCustomerById(id);
            displayBalance(customer);

        } catch(NullPointerException| NumberFormatException | BusinessException e){
            loggy.error(e.getMessage());
        }
    }

    private void handleCustomerMenu(int choice, Customer customer) throws BusinessException {

        //Logic for the Customer Sub-menu options

        switch(choice){
            case 0:
                //exit options
                logoutMenu();
                break;
            case 1:
                //Check Balance
                displayBalance(customer);
                break;
            case 2:
                //Deposit
                try {
                    displayDeposit(customer);
                } catch (BusinessException e) {
                    loggy.error(e.getMessage());
                }
                break;
            case 3:
                //Withdraw
                try{
                    displayWithdrawalMenu(customer);
                } catch(BusinessException e) {
                loggy.error(e.getMessage());
                }
                break;

            case 4:
                //Transfer funds to another customer account
                try{
                    displayCustTransferMenu(customer);
                } catch(BusinessException e){
                    loggy.error(e.getMessage());
                }
                break;
            case 5:
                //Transfer funds to an account attached to the same customer
                break;
            default:
                loggy.info("Invalid selection, please try again.");
                break;
        }
    }

    private void displayCustTransferMenu(Customer customer) throws BusinessException{

        Scanner kb = new Scanner(System.in);
        Customer recipient = new Customer();

        loggy.info("------------------------------------");
        loggy.info("Transfer Funds to Another Customer");
        loggy.info("------------------------------------\n");
        loggy.info("Please enter the recipient's username: ");


        recipient.setUsername(kb.nextLine());


        loggy.info("Please enter the amount you'd like to transfer: ");
        double amount = Double.parseDouble(kb.nextLine());




        try {
            CustomerService cService = new CustomerServiceImpl();
            TransactionService tService = new TransactionServiceImpl();
            AccountService aService = new AccountServiceImpl();
            recipient = cService.getCustomerByUsername(recipient.getUsername());

            Transaction transaction = customer.getAccounts().get(0).transfer(recipient, amount);

            tService.createTransaction(transaction);
            loggy.info("Transaction successfully created.");
            aService.updateBalance(recipient.getAccounts().get(0));
            aService.updateBalance(customer.getAccounts().get(0));

            displayBalance(customer);
        } catch(BusinessException e){
            loggy.error(e.getMessage());
        }
    }

    private void handleHomeMenu(int choice){

        //Logic for the main menu options

        switch(choice){
            case 0:
                //exit options
                logoutMenu();
                break;
            case 1:
                // login customer login
                try {
                    Customer customer = displayCustomerLogin();
                    runCustomerMenu(customer);
                } catch (BusinessException e){
                    loggy.error(e.getMessage());
                }
                break;
            case 2:
                // employee login
                try {
                    displayEmployeeLogin();
                    runEmployeeMenu();
                } catch (BusinessException e){
                    loggy.error(e.getMessage());
                }
                break;
            case 3:
                // new customer sign up
                try {
                    displayCustomerSignUpMenu();
                } catch (BusinessException e) {
                    loggy.error(e.getMessage());
                }
                break;
            default:
                loggy.info("Invalid selection, please try again.");
                break;
        }
    }
    private void handleLogout(int choice) {
        switch(choice){
            case 1:
                quit = true;
                break;
            case 2:
                break;
            default:
                loggy.info("Invalid selection, please try again.");
                break;

        }

    }

    public void logoutMenu(){
        displayLogout();
        int choice = getInput(2);
        handleLogout(choice);
    }





}
