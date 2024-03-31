package com.example.task.config;

import com.example.task.entity.User;
import com.example.task.enums.UserRole;
import com.example.task.enums.UserStatus;
import com.example.task.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User worker = new User("Worker", UserStatus.ACTIVE, UserRole.WORKER, "worker", "111");
        User manager = new User("Manager", UserStatus.ACTIVE, UserRole.MANAGER, "manager", "111");
        if (userRepository.existsByLogin(worker.getLogin()) || userRepository.existsByLogin(worker.getLogin())) {
            return;
        }
        userRepository.save(worker);
        userRepository.save(manager);
    }
}