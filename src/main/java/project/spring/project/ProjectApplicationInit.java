package project.spring.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.spring.project.user.model.RoleEntity;
import project.spring.project.user.model.UserEntity;
import project.spring.project.user.repository.UserRepository;

import java.util.List;

@Component
public class ProjectApplicationInit implements CommandLineRunner {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ProjectApplicationInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            // admin
            UserEntity admin = new UserEntity();
            admin.setEmail("admin");
            admin.setPasswordHash(passwordEncoder.encode("admin"));

            RoleEntity adminAdminRole = new RoleEntity();
            adminAdminRole.setRole("ROLE_ADMIN");

            RoleEntity adminUserRole = new RoleEntity();
            adminUserRole.setRole("ROLE_USER");

            admin.setRoles(List.of(adminAdminRole, adminUserRole));

            userRepository.save(admin);

            // user
            UserEntity user = new UserEntity();
            user.setEmail("user");
            user.setPasswordHash(passwordEncoder.encode("user"));

            RoleEntity userUserRole = new RoleEntity();
            userUserRole.setRole("ROLE_USER");

            user.setRoles(List.of(userUserRole));

            userRepository.save(user);
        }
    }
}
