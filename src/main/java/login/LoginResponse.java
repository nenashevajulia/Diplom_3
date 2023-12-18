package login;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static configs.Urls.AUTH_LOGIN_URL;
import static io.restassured.RestAssured.given;

public class LoginResponse {
    public ValidatableResponse login(Login login) {
        return given().contentType(ContentType.JSON).body(login).when().post(AUTH_LOGIN_URL).then();

    }
}
