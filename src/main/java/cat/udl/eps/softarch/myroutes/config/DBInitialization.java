package cat.udl.eps.softarch.myroutes.config;
import cat.udl.eps.softarch.myroutes.domain.Admin;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import cat.udl.eps.softarch.myroutes.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public DBInitialization(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setId("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        if (!adminRepository.existsById("root")) {
            Admin admin = new Admin();
            admin.setEmail("root@myroutes.app");
            admin.setId("root");
            admin.setPassword(defaultPassword);
            admin.encodePassword();
            adminRepository.save(admin);
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setId("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }
        }
    }
}
