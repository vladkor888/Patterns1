package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class AppCardDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    DataGenerator.UserData user = DataGenerator.Registration.generateUserDeliveryCard("ru");

    @Test
    public void shouldMakeAppointmentAndNewDateOfAppointment() {
        $("[data-test-id='city'] input").val(user.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys("Backspace");
        $("[data-test-id='date'] input").val(DataGenerator.generateDate(0));
        $("[data-test-id='name'] input").val(user.getName());
        $("[data-test-id='phone'] input").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(appear, Duration.ofSeconds(5));
        $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(0)));
        $x(".//button").click();
        $("[data-test-id='date'] input").doubleClick().sendKeys("Backspace");
        $("[data-test-id='date'] input").val(DataGenerator.generateDate(4));
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(appear, Duration.ofSeconds(5));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(appear, Duration.ofSeconds(5));
        $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(4)));
        $x(".//button").click();
    }
}

