package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateDay(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd"));
    }

    public static String generateCity() {
        var cities = new String[]{"Майкоп", "Нальчик", "Москва", "Санкт-Петербург", "Казань",
                "Воронеж", "Екатеринбург", "Магадан", "Владимир", "Брянск", "Калуга", "Саратов", "Самара"};
        return cities[new Random().nextInt(cities.length)];
    }
}
