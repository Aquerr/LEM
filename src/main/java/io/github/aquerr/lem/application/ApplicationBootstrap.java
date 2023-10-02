package io.github.aquerr.lem.application;

import io.github.aquerr.lem.domain.user.model.LemAuthority;
import io.github.aquerr.lem.domain.user.model.LemUser;
import io.github.aquerr.lem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class ApplicationBootstrap implements CommandLineRunner
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception
    {
        if (!System.getProperty("os.name").endsWith("ux"))
        {
            log.warn("This application supports Linux only and will not work properly on other operating systems! Sorry! :(");
        }

        log.info("Running with system properties: " + System.getProperties());

        final LemUser user = this.userRepository.findByUsername("lem");
        if (user == null)
        {
            UUID password = UUID.randomUUID();

            log.info("\n\nYour initial password for 'lem' user is: " + password + "\n");

            LemUser lemUser = new LemUser(null, "lem", passwordEncoder.encode(password.toString()), Arrays.stream(LemAuthority.values())
                    .map(Enum::name)
                    .collect(Collectors.toSet()));

            this.userRepository.save(lemUser);
        }
    }
}
