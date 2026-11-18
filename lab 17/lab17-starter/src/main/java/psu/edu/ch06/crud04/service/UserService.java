
package psu.edu.ch06.crud04.service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import psu.edu.ch06.crud04.model.User;
import psu.edu.ch06.crud04.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public UserService(
            UserRepository userRepository,
            AuthenticationManager authManager,
            JwtService jwtService,
            PasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    public User register(User user) {
        return userRepository.save(
            new User(
                null,
                user.username(),
                encoder.encode(user.password())
            )
        );
    }

    public String verify(User user) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.username(),
                user.password()
            )
        );

        return jwtService.generateToken(auth.getName());
    }
}
