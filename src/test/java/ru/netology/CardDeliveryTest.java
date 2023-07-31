package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;


public class CardDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestSuccessForm() {
        var daysToAddForMeeting = 4;
        var meetingDate = DataGenerator.generateDate(daysToAddForMeeting);
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(meetingDate);
        $("[data-test-id=name] input").setValue("Александр Труфманов");
        $("[data-test-id=phone] input").setValue("+79262627815");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на " + meetingDate))
                .shouldBe(visible);
    }

    @Test
    void shouldTestSuccessFormWithCalendar() {
        var daysToAddForMeeting = 4;
        var meetingDate = DataGenerator.generateDate(daysToAddForMeeting);
        $("[data-test-id=city] input").setValue("Мо");
        $(byText("Москва")).click();
        $("[data-test-id=date]").click();
        $(".popup__content").shouldBe(visible);
        $(".calendar__arrow.calendar__arrow_direction_right").click();
        $$(".calendar__day").findBy(text("[data-day='1694120400000']")).click();
        $("[data-test-id=name] input").setValue("Александр Труфманов");
        $("[data-test-id=phone] input").setValue("+79262627815");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на " + meetingDate))
                .shouldBe(visible);
    }
}
