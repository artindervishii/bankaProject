# Bank System Spring Boot Application


# Introduction
This Spring Boot application simulates a bank system with multiple user accounts capable of performing transactions. It leverages the Spring framework to provide a RESTful web service for interacting with the bank system. The application is designed to showcase object-oriented programming principles, exception handling, and basic transaction functionalities.

# Features

Create a bank with necessary details.

Create user accounts within the bank.

Perform transactions:

Transfer funds between accounts with both flat and percent transaction fees.

Withdraw funds from an account.

Deposit funds into an account.

View transaction history for any account.

Check account balance.

List all bank accounts.

View total transaction fee and transfer amounts for the bank.

# Requirements

Java JDK 8 or higher. 

Maven.

Git installed on your system.

# How to Use

Clone this repository to your local machine:

git clone https://github.com/artindervishii/bank-system.git

After cloning from GitHub, navigate to src/main/java/resources/application.properties. Here lies the database configuration; ensure you update the information to match your local MySQL setup, including username and password. Once adjusted, proceed to MySQL and create a database with the same name specified in application.properties, which I've designated as 'bankalink'. After creating this database in MySQL, run your IDE. The tables will be generated automatically, and you're now prepared to test the API.

# RESTful Endpoints

To test the APIs, you'll need to use Postman or another application designed for API testing.

--------------------------
  # Banks
--------------------------
  
POST "/banks/CreateBank: Create a new bank. 

It request body:

{

        "name": "test",
        "totalTransactionFeeAmount": 0.0,
        "totalTransferAmount": 0.0,
        "flatFeeAmount": 10.0,
        "percentFeeValue": 5.0

}

--------------------------


GET "/banks/getAllBanks"/: Get a list of all banks.

--------------------------

GET "/banks/getBankById/{id}"/: Get a bank by id.  --"(Change {id} to an existing id}"--

--------------------------

GET "/banks/{bankId}/total-transaction-fee"/: Get total transaction fee -(Didn't fix it , it returns always 0)   --"(Change {id} to an existing id}"--

--------------------------

GET "/banks/{bankId}/total-transfer-amount"/: Get total transaction fee -(Didn't fix it , it returns always 0)   --"(Change {id} to an existing id}"--

--------------------------
     # Accounts
--------------------------
     
POST /accounts/createAccount: Create a new account.

It request body:

{

    "name": "test",
    "balance": 1300,
    "bank": {
        "id": 5
    }
    
}

--------------------------


GET /accounts/getAllAccounts: Get a list of all accounts.

--------------------------

GET /accounts/getAccountById/{id} : Get a account by id, "(Change {id} to an existing id}"

--------------------------

    # Transactions

--------------------------

POST /transactions/performTransaction: Perform transaction from one account to another accound.

It Request 2 params , flatFee , and percentFee "?flatFee=10&percentFee=5"

and body:

{

  "amount": 100,
  "originatingAccount": {
  
    "id": 1
    
  },
  "resultingAccount": {
  
    "id": 2
    
  },
  "reason": "Payment",
  
  "isFlatFee": true
  
}

--------------------------

POST /transactions/deposit: Deposit money to your account.

It request 2 params , accountId and amount " ?accountId=2&amount=50 "

--------------------------

POST /transactions/withdraw: Withdraw money from your account.

It request 2 params , accountId and amount " ?accountId=2&amount=50 "

--------------------------

GET /transactions/getTransactionsForAccount/{id} : to get transactions for a specific account --"(Change {id} to an existing id}"--

--------------------------

GET /transactions/checkAccountBalance/{id} : Get the balance of a specific account. --"(Change {id} to an existing id}"--


--------------------------

# Contributor
# Artin Dervishi

