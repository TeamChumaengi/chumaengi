package com.Chumaengi.chumaengi.common;

import com.Chumaengi.chumaengi.auth.controller.AuthApiController;
import com.Chumaengi.chumaengi.auth.security.JwtProvider;
import com.Chumaengi.chumaengi.auth.service.AuthService;
import com.Chumaengi.chumaengi.global.config.SecurityConfig;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@ImportAutoConfiguration(SecurityConfig.class)
@WebMvcTest(AuthApiController.class)
@AutoConfigureRestDocs
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected JwtProvider jwtProvider;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected MemberFindService memberFindService;

    protected OperationRequestPreprocessor applyRequestPreprocessor() {
        return preprocessRequest(prettyPrint());
    }

    protected OperationResponsePreprocessor applyResponsePreprocessor() {
        return preprocessResponse(prettyPrint());
    }

    protected String convertObjectToJson(Object data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
