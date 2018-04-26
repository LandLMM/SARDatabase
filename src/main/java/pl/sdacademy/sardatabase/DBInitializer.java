package pl.sdacademy.sardatabase;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInitializer {
    private UserRepository userRepository;

    public DBInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void initializeDB() {
        userRepository.save(new User("user1", "password1"));
        userRepository.save(new User("user2", "password2"));
    }

}
