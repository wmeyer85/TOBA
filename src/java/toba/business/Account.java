package toba.business;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import toba.util.AccountType;

@Entity
public class Account {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
	public AccountType acctType = AccountType.SAVING;
	public BigDecimal balance = new BigDecimal(0);
        
        //@ManyToOne
	public User usr;
        
        User user = null;
        
        
       // @OneToMany(fetch=EAGER, cascade=CascadeType.PERSIST)
       // private List<LineItem> lineItems;
        
	public Account() {
	}
	
	public Account(User usr, BigDecimal balance, AccountType acctType) {
		this.usr = usr;
		this.balance = balance;
		this.acctType = acctType;
	}
        
	public void credit( BigDecimal creditAmount) {
            
            this.balance = this.balance.add( creditAmount);
            
	}
	public void debit( BigDecimal debitAmount) {
            	
            this.balance = this.balance.subtract( debitAmount);
            
	}
	
	public AccountType getAccountType() {
		return acctType;
	}
	public void setAccountType( AccountType acctType) {
		this.acctType = acctType;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	public void setBalance( BigDecimal balance) {
		this.balance = balance;
	}
	
	public User getUser() {
		return this.usr;
	}
	public void setUser( User usr) {
		this.usr = usr;
	}
}

