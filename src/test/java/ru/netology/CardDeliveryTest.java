package ru.netology;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.conditions.ExactText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    public void setup() {
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
        var daysToAddForMeeting = 31;
        var meetingDate = DataGenerator.generateDate(daysToAddForMeeting);
        var meetingDay = DataGenerator.generateDay(daysToAddForMeeting);
        $("[data-test-id=city] input").setValue("Мо");
        $$(".menu-item__control").findBy(text("Москва")).click();
        $("[class=icon-button__content]").click();
//        $("[class=calendar-input__calendar-wrapper]").shouldBe(visible);
//        if ($$("[data-day]").findBy(value(meetingDay)).equals(false)) {
            $("[class='calendar__arrow calendar__arrow_direction_right']").click();
            $$("[data-day]").findBy(exactText(meetingDay)).click();
//        } {
//            $$("[data-day]").findBy(exactText(meetingDay)).click();
//        }
        $("[data-test-id=name] input").setValue("Александр Труфманов");
        $("[data-test-id=phone] input").setValue("+79262627815");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title").shouldBe(visible, Duration.ofSeconds(15))
                .equals(exactText("Успешно!"));
        $("[data-test-id='notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .equals(exactText("Встреча успешно забронирована на " + meetingDate));
    }
}
