package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v109.css.model.Value;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;
import static com.codeborne.selenide.files.DownloadActions.click;


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
        var daysToAddForMeeting = 7;
        var meetingDate = DataGenerator.generateDate(daysToAddForMeeting);
        var meetingDay= DataGenerator.generateDay(daysToAddForMeeting);
        $("[data-test-id=city] input").setValue("Мо");
        $$(".menu-item__control").findBy(text("Москва")).click();
        $("[data-test-id=date]").click();
        $(".popup__content").shouldBe(visible);
        $$("td.calendar__day").findBy(text(meetingDay)).click();
        $("[data-test-id=name] input").setValue("Александр Труфманов");
        $("[data-test-id=phone] input").setValue("+79262627815");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
//        $("[.data-test-id='notification']")
//        $("[.data-test-id='notification'] .notification__title")
        $("[data-test-id='notification']")
                .shouldBe(visible, Duration.ofSeconds(15));
        $$(".notification__title").findBy(text("Успешно"));
        $$(".notification__content").findBy(text("Встреча успешно забронирована на " + meetingDate));
//                .shouldHave(exactText("Встреча успешно забронирована на " + meetingDate));
//////                .shouldBe(visible);
//        $("[.data-test-id='notification'] .notification__content")
//                .shouldHave(exactText("Встреча успешно забронирована на " + meetingDate))
//                .shouldBe(visible, Duration.ofSeconds(15));
//                .shou
//        ).findBy(text("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
//        $("[data-test-id='notification'] .notification__content")
//                .shouldHave(exactText("Встреча успешно забронирована на " + meetingDate))
//                .shouldBe(visible);
    }
}
