package sample._02_pattern._05_applicationService._01;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean exists(User user) {
        var optUser = repository.find(user.getName());
        return optUser.isPresent();
    }
}
