package user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static configs.Urls.AUTH_REGISTER_URL;
import static configs.Urls.AUTH_USER_URL;
import static io.restassured.RestAssured.given;

public class UserResponse {
    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given().contentType(ContentType.JSON).body(user).when().post(AUTH_REGISTER_URL).then();

    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given().header("authorization", accessToken).contentType(ContentType.JSON).when().delete(AUTH_USER_URL);
    }


}
