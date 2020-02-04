package com.nevercaution.demoinflearnapi.configure;

import com.nevercaution.demoinflearnapi.accounts.Account;
import com.nevercaution.demoinflearnapi.accounts.AccountRepository;
import com.nevercaution.demoinflearnapi.accounts.AccountRole;
import com.nevercaution.demoinflearnapi.accounts.AccountService;
import com.nevercaution.demoinflearnapi.common.AppProperties;
import com.nevercaution.demoinflearnapi.common.BaseControllerTest;
import com.nevercaution.demoinflearnapi.common.TestDescription;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    private AppProperties appProperties;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {

        mockMvc.perform(post("/oauth/token")
                // header 에 정보를 담아서 전달
                .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
                .param("username", appProperties.getUserUsername())
                .param("password", appProperties.getUserPassword())
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;
    }
}