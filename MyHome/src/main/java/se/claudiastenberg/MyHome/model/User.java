package se.claudiastenberg.MyHome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private Long accountName;
    private int controllerId;

    protected User(){}

    public User(Long accountName, int controllerId) {
        this.accountName = accountName;
        this.controllerId = controllerId;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountName() {
        return accountName;
    }

    public int getControllerId() {
        return controllerId;
    }
}
