package com.nevercaution.demoinflearnapi.configure;

import com.nevercaution.demoinflearnapi.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(AccountService accountService,
                          PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * custom 한 authenticationManager 를 걸어주기 위해 overriding 해주면서
     * @Bean 으로 등록해준다.
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * WebSecurity, HttpSecurity 둘다 선언이 되어 있는 경우엔
     * WebSecurity 가 우선순위가 높다.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/docs/index.html");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * docs 나 정적 리소스는 WebSecurity 에서 걸러주는게 좋다.
     * httpSecurity 에서 거를 경우 이미 인증안으로 들어오기 때문에 더 많은 일을 수행한다.
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .anonymous()
//                .and()
//            .formLogin()
//                .and()
//            .authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/api/**").anonymous()
//                .anyRequest().authenticated()
//        ;
//    }
}
