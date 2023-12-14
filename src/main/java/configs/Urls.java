package configs;

import io.restassured.RestAssured;

public class Urls {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String AUTH_LOGIN_URL = "api/auth/login";
    public static final String AUTH_REGISTER_URL = "api/auth/register";
    public static final String AUTH_USER_URL = "api/auth/user";
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final String RECOVERY_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public static String getBaseUri() {
        return BASE_URL;
    }

    public static void start() {
        RestAssured.baseURI = Urls.getBaseUri();
    }
}
