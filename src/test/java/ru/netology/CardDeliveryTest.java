package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.SetValueOptions.withText;


public class CardDeliveryTest {

    @Test
    void shouldTestSuccessForm() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").setValue("27.07.2023");
        $("[name='name']").setValue("Александр Труфманов");
        $("[name='phone']").setValue("+79262627815");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
       // $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно!"));
    }
}
