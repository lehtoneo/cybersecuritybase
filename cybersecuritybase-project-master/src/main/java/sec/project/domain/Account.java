package sec.project.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class Account extends AbstractPersistable<Long> {

    private String name;
    private String password;

    public Account() {
        super();
    }

    public Account(String name, String address) {
        this();
        this.name = name;
        this.password = address;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
