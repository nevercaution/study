package com.nevercaution.demoinflearnapi.configure;

import com.nevercaution.demoinflearnapi.accounts.Account;
import com.nevercaution.demoinflearnapi.accounts.AccountRole;
import com.nevercaution.demoinflearnapi.accounts.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            private AccountService accountService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Account account = Account.builder()
                        .email("nevercaution@email.com")
                        .password("teddy")
                        .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                        .build();
                accountService.saveAccount(account);
            }
        };
    }
}
