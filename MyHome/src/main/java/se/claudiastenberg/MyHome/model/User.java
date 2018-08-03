package se.claudiastenberg.MyHome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private int accountName;
    private boolean status;

    protected User(){}

    public User(int password, boolean status) {
        this.accountName = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public int getAccountName() {
        return accountName;
    }

    public boolean isStatus() {
        return status;
    }
}
