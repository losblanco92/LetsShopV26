package utilities;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestDataUtils {

	static Faker faker = new Faker(new Locale("en-US"));

    // Random First Name
    public static String randomFirstName() {
        return faker.name().firstName();
    }

    //  Random Last Name
    public static String randomLastName() {
        return faker.name().lastName();
    }

    //  Random Email
    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    //  Random 10-digit Indian Number
    public static String randomPhoneNumber() {
        return faker.number().digits(10);
    }

    //  Random Password (alphanumeric + special)
    public static String randomPassword() {
        return faker.internet().password(8, 12, true, true, true);
    }

    
}
