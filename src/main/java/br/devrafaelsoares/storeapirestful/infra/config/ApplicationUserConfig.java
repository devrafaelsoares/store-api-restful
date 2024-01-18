package br.devrafaelsoares.storeapirestful.infra.config;

import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import br.devrafaelsoares.storeapirestful.domain.user.User;
import br.devrafaelsoares.storeapirestful.repositories.CartRepository;
import br.devrafaelsoares.storeapirestful.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static br.devrafaelsoares.storeapirestful.domain.user.Role.ADMIN;

@Component
public class ApplicationUserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Value("${spring.application.security.name}")
    private String name;
    @Value("${spring.application.security.username}")
    private String username;
    @Value("${spring.application.security.email}")
    private String email;
    @Value("${spring.application.security.password}")
    private String password;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername(username).isEmpty()) {

            User userAdmin = User
                    .builder()
                        .name(name)
                        .email(email)
                        .username(username)
                        .password(new BCryptPasswordEncoder().encode(password))
                        .role(ADMIN)
                    .build();

            userRepository.save(userAdmin);

            Cart cart = Cart
                    .builder()
                        .user(userAdmin)
                    .build();

            cartRepository.save(cart);
        }

    }
}
