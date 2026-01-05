package pl.edu.pja.gden.tpopros30182.Auth;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDTOMapper {

    public static UserDTO map(User user) {
        UserDTO dto = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet())
        );

        return dto;
    }
}
