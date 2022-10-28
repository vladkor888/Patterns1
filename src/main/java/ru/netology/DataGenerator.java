package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    @Data
    @RequiredArgsConstructor
    public static class UserData {
        private final String city;
        private final String name;
        private final String phone;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static @NotNull String generateDate(int shift) {
        LocalDate todayDelivery = LocalDate.now();
        return todayDelivery.plusDays(3 + shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static @NotNull String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.numerify("+7##########");
    }

    public static class Registration {
        private Registration() {
        }

        @Contract("_ -> new")
        public static @NotNull UserData generateUserDeliveryCard(String locale) {
            return new UserData(generateCity(locale), generateName(locale), generatePhone(locale));
        }
    }
}
