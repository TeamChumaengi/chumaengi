package com.Chumaengi.chumaengi.auth.controller;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.exception.AuthErrorCode;
import com.Chumaengi.chumaengi.common.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.exception.MemberErrorCode;
import common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Auth [Controller Layer] -> AuthApiController 테스트")
public class AuthApiControllerTest extends ControllerTest{

    @Nested
    @DisplayName("회원가입 API [Member /signup]")
    class signup {
        private static final String BASE_URL = "/signup";

        @Test
        @DisplayName("중복되는 이메일이 있으면 회원가입에 실패한다")
        void throwExceptionByDuplicateEmail() throws Exception {
            //given
            doThrow(ChumaengiException.type(AuthErrorCode.DUPLICATE_EMAIL))
                    .when(authService)
                    .signup(any());

            //when
            final AuthRequest request = createAuthRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            //then
            final AuthErrorCode expectedError = AuthErrorCode.DUPLICATE_EMAIL;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isConflict(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Signup/Failure/Case1",
                                    applyRequestPreprocessor(),
                                    applyResponsePreprocessor(),
                                    requestFields(
                                            fieldWithPath("name").description("이름"),
                                            fieldWithPath("email").description("이메일"),
                                            fieldWithPath("password").description("비밀번호"),
                                            fieldWithPath("nickname").description("닉네임")
                                    ),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("중복되는 닉네임이 있으면 회원가입에 실패한다")
        void throwExceptionByDuplicateNickname() throws Exception {
            //given
            doThrow(ChumaengiException.type(AuthErrorCode.DUPLICATE_NICKNAME))
                    .when(authService)
                    .signup(any());

            //when
            final AuthRequest request = createAuthRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            //then
            final AuthErrorCode expectedError = AuthErrorCode.DUPLICATE_NICKNAME;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isConflict(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Signup/Failure/Case2",
                                    applyRequestPreprocessor(),
                                    applyResponsePreprocessor(),
                                    requestFields(
                                            fieldWithPath("name").description("이름"),
                                            fieldWithPath("email").description("이메일"),
                                            fieldWithPath("password").description("비밀번호"),
                                            fieldWithPath("nickname").description("닉네임")
                                    ),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("회원가입을 성공한다")
        void success() throws Exception {
            //given
            given(authService.signup(any())).willReturn(true);

            // when
            final AuthRequest request = createAuthRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    )
                    .andDo(
                            document(
                                    "AuthApi/Signup/Success",
                                    applyRequestPreprocessor(),
                                    applyResponsePreprocessor(),
                                    requestFields(
                                            fieldWithPath("name").description("이름"),
                                            fieldWithPath("email").description("이메일"),
                                            fieldWithPath("password").description("비밀번호"),
                                            fieldWithPath("nickname").description("닉네임")
                                    )
                            )
                    );
        }
    }

    @Nested
    @DisplayName("로그인 API [Member /login]")
    class login {
        private static final String BASE_URL = "/login";

        @Test
        @DisplayName("이메일로 회원 정보를 찾을 수 없으면 로그인에 실패한다")
        void throwExceptionByMemberNotFound() throws Exception {
            //given
            doThrow(ChumaengiException.type(MemberErrorCode.MEMBER_NOT_FOUND))
                    .when(authService)
                    .login(any());

            //when
            final AuthRequest request = createAuthRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            //then
            final MemberErrorCode expectedError = MemberErrorCode.MEMBER_NOT_FOUND;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isNotFound(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Login/Failure/Case1",
                                    applyRequestPreprocessor(),
                                    applyResponsePreprocessor(),
                                    requestFields(
                                            fieldWithPath("name").description("이름"),
                                            fieldWithPath("email").description("이메일"),
                                            fieldWithPath("password").description("비밀번호"),
                                            fieldWithPath("nickname").description("닉네임")
                                    ),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("비밀번호가 일치하지 않으면 로그인에 실패한다")
        void throwExceptionByWrongPassword() throws Exception {
            //given
            doThrow(ChumaengiException.type(AuthErrorCode.WRONG_PASSWORD))
                    .when(authService)
                    .login(any());

            //when
            final AuthRequest request = createAuthRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            //then
            final AuthErrorCode expectedError = AuthErrorCode.WRONG_PASSWORD;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isUnauthorized(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Login/Failure/Case2",
                                    applyRequestPreprocessor(),
                                    applyResponsePreprocessor(),
                                    requestFields(
                                            fieldWithPath("name").description("이름"),
                                            fieldWithPath("email").description("이메일"),
                                            fieldWithPath("password").description("비밀번호"),
                                            fieldWithPath("nickname").description("닉네임")
                                    ),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("로그인에 성공한다")
        void success() throws Exception {
        }
    }

    private AuthRequest createAuthRequest() {
        return new AuthRequest("윤선경", "qwe1234@gmail.com","qwe1234","추맹이1");
    }
}
