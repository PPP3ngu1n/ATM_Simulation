# ATM Simulation

## Description
A simple ATM like console application written in Java, allowing users to create and log into account.
Whilst logged into their accounts users are allowed to Deposit, Withdraw money.

Account information is stored in a .txt for persistence.

## Features
- User Authentication
  - Create new accounts
  - Log into existing Accounts
- Account Management
  - View current Balances
  - Deposit
  - Withdraw
- Persistent Storage
  - Account details (Username, Password, Balance) are all saved in .txt file
  - Updates are made in real time

## Test Cases & Use Examples

Welcome to the Account Management
Do you have an Account? 
Yes/No or Exit to quit
> No

Please Enter in a Username:
> alice
Please Enter in a Password:
> 1234
Your account has been added

====== ATM Menu ======
1. View balance
2. Deposit Money
3. Withdraw Money
4. Exit

## IMPORTANT NOTES:
- Passwords are stored in PLAIN TEXT, this is for the simulation ONLY
- File operations overwrite existing balances, so manual editing of Accounts.txt may cause errors
- The simulation is intended for EDUCATIONAL PURPOSES
