package edu.eci.arsw.conc;

/**
 *
 * @author hcadavid
 */
public class Account {
    
    private int balance;

    
    public Account(int initialBalance){
        balance=initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        balance+=amount;
    }
    
    public void withdraw(int amount){
        balance-=amount;
    }
    
    public void transferFrom(Account acnt2, int amount){        
        acnt2.withdraw(amount);
        this.deposit(amount);        
    }
    
}
