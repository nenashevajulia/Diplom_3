package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserData {
    public static String EMAIL = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public static String PASSWORD = RandomStringUtils.randomNumeric(6);
    public static String NAME = RandomStringUtils.randomAlphabetic(10);
    public static String WRONG_PASSWORD = RandomStringUtils.randomNumeric(5);
}
