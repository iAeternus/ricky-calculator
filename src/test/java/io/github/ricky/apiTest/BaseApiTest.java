package io.github.ricky.apiTest;

import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.ricky.core.common.domain.event.DomainEventDao;
import io.github.ricky.core.common.exception.Error;
import io.github.ricky.core.common.exception.ErrorCodeEnum;
import io.github.ricky.core.common.exception.QErrorResponse;
import io.github.ricky.core.common.utils.RcConstants;
import io.github.ricky.core.common.utils.RcObjectMapper;
import io.github.ricky.core.relic.domain.RelicRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className BaseApiTest
 * @desc 接口测试基类
 */
@ActiveProfiles("ci")
@Execution(ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseApiTest {

    @Autowired
    private RcObjectMapper objectMapper;

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    @Autowired
    protected DomainEventDao domainEventDao;

    // add here...

    @LocalServerPort
    protected int port;

    /**
     * 初始化并返回一个配置好的RequestSpecification对象，用于后续的HTTP请求构建
     * <p>
     * 1. ObjectMapper配置：通过配置ObjectMapper，指定了使用自定义的MryObjectMapper来处理JSON序列化和反序列化
     * 这允许在发送请求和接收响应时，根据自定义规则处理JSON数据
     * <p>
     * 2. 编码器配置：禁用了在内容类型未定义时自动向内容类型头部添加默认字符集（如UTF-8）的行为
     * 这有助于在需要精确控制HTTP请求头部时避免不必要的字符集添加
     * <p>
     * 3. 日志配置：启用了在验证失败时记录请求和响应的日志功能
     * 这有助于在测试失败时快速定位问题，查看实际发送的请求和接收的响应内容
     *
     * @return 配置好的RequestSpecification对象，可用于进一步配置和发送HTTP请求
     */
    public static RequestSpecification given() {
        return RestAssured.given()
                .config(RestAssuredConfig.config()
                        .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((type, s) -> new RcObjectMapper()))
                        .encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
                        .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()));
    }

    /**
     * 初始化并返回一个配置好的RequestSpecification对象，用于后续的HTTP请求构建，并可选地添加一个JWT令牌作为Cookie
     * 如果提供的JWT令牌不为空且不是仅由空白字符组成，该令牌将被添加为Cookie到请求中，以便在发送请求时进行身份验证
     *
     * @param jwt JWT令牌字符串，用于身份验证。如果为空或仅包含空白字符，则不会添加任何Cookie
     * @return 配置好的RequestSpecification对象，可能包含JWT令牌作为Cookie，用于进一步配置和发送HTTP请求
     */
    public static RequestSpecification given(String jwt) {
        if (StringUtils.isNotBlank(jwt)) {
            return given().cookie(RcConstants.AUTH_COOKIE_NAME, jwt);
        }

        return given();
    }

    /**
     * 初始化并返回一个配置好的RequestSpecification对象，用于后续的HTTP请求构建，并可选地添加一个Bearer令牌作为HTTP头
     * 如果提供的JWT令牌不为空且不是仅由空白字符组成，该令牌将被格式化为"Bearer 令牌值"的字符串，并作为Authorization HTTP头添加到请求中，以便在发送请求时进行身份验证
     *
     * @param jwt JWT令牌字符串，用于身份验证。如果为空或仅包含空白字符，则不会添加任何Authorization头
     * @return 配置好的RequestSpecification对象，可能包含Bearer令牌作为Authorization头，用于进一步配置和发送HTTP请求
     */
    public static RequestSpecification givenBearer(String jwt) {
        if (StringUtils.isNotBlank(jwt)) {
            return given().header(RcConstants.AUTHORIZATION, String.format("Bearer %s", jwt));
        }

        return given();
    }

    /**
     * 初始化并返回一个配置好的RequestSpecification对象，用于后续的HTTP请求构建，并设置基本的HTTP认证信息
     * 该方法将使用提供的用户名和密码进行HTTP基本认证（Basic Authentication），并配置为预先发送认证信息（preemptive）
     * 这意味着在请求发送之前，客户端将主动发送认证信息到服务器，而不是等待服务器首先请求认证
     *
     * @param username 用于HTTP基本认证的用户名
     * @param password 与用户名对应的密码
     * @return 配置好的RequestSpecification对象，包含了HTTP基本认证信息，用于进一步配置和发送HTTP请求
     */
    public static RequestSpecification givenBasic(String username, String password) {
        return given().auth().preemptive().basic(username, password);
    }

    @BeforeEach
    public void setUp() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        RestAssured.port = port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @AfterEach
    public void cleanUp() {

    }

    public static void assertError(Supplier<Response> apiCall, ErrorCodeEnum expectedCode) {
        Error error = apiCall.get().then().statusCode(expectedCode.getStatus()).extract().as(QErrorResponse.class).getError();
        assertEquals(expectedCode, error.getCode());
    }
}
