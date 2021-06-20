package sample._02_pattern._03_domainService._01;

public class User {
    private final UserId id;
    private UserName name;

    public User(UserId id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public boolean exists() {
        return false;
    }
}
