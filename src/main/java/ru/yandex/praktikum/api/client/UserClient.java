package ru.yandex.praktikum.api.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.api.client.base.BurgerRestClient;
import ru.yandex.praktikum.api.model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends BurgerRestClient {
    private static final String AUTH_URI = "auth/";

    @Step("Создание {user}")
    public ValidatableResponse create(User user) {
        ValidatableResponse response = given()
                .spec(getBaseReqSpec())
                .body(user)
                .post(AUTH_URI + "register")
                .then();
        user.setToken(response.extract().path("accessToken"));

        return response;
    }

    @Step("Логин {user}")
    public ValidatableResponse login(User user) {
        ValidatableResponse response = given()
                .spec(getBaseReqSpec())
                .body(user)
                .post(AUTH_URI + "login")
                .then();
        user.setToken(response.extract().path("accessToken"));

        return response;
    }

    @Step("Удаление {user}")
    public ValidatableResponse delete(User user) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization", user.getToken())
                .delete(AUTH_URI + "user")
                .then();
    }
}
