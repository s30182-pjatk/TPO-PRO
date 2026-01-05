package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.gden.tpopros30182.Auth.*;

import java.util.*;

@Service
public class UserService {
    private final Random random = new Random();
    private final Map<String, PasswordEncoder> encoderMap;
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserRoleRepository userRoleRepository;

    public UserService(Map<String, PasswordEncoder> encoderMap, UserRepository userRepository, PasswordConfig passwordConfig, UserRoleRepository userRoleRepository) {
        this.encoderMap = encoderMap;
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(encodeWithRandom(userRegisterDTO.getPassword()));
        Optional<UserRole> userRole = userRoleRepository.findByName("USER");
        userRole.ifPresentOrElse(role -> user.getRoles().add(role), NoSuchElementException::new);
        userRepository.save(user);
    }



    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDTOMapper::map);
    }

    public Set<String> findRolesByEmail(String email) {
        Optional<UserDTO> user = getUserByEmail(email);
        if (user.isPresent()) {
            return user.get().getRoles();
        }

        return Set.of();
    }

    public List<String> findAllUsersEmails() {
        return userRepository.findAllUsersByRoles_Name("User")
                .stream().map(User::getEmail).toList();
    }

    public Optional<Long> getUserIdByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email).map(User::getId).orElseThrow(NoSuchElementException::new));
    }

    @Transactional
    public void deleteUser(String email) {
        if(isCurrentUserAdmin()){
            userRepository.deleteByEmail(email);
        }
    }



    private String encodeWithRandom(String rawPassword) {
        List<String> ids = passwordConfig.encoderIds();
        String chosenId = ids.get(random.nextInt(ids.size()));
        System.out.println("🔒 Chosen encoder: " + chosenId);  // Debug print
        PasswordEncoder encoder = encoderMap.get(chosenId);
        return "{" + chosenId + "}" + encoder.encode(rawPassword);
    }

    private boolean isCurrentUserAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
