package ru.qwonix.cutesuite.calculator;

import lombok.Getter;
import lombok.Setter;
import ru.qwonix.cutesuite.calculator.entity.Converter;
import ru.qwonix.cutesuite.calculator.entity.Day;
import ru.qwonix.cutesuite.calculator.entity.Guest;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {

    private final String PATH_TO_SETTING = "settings.json";
    private Guest guest = Guest.TWO;

    private int weeklyDiscountPercent = 7;

    public Model() {
        Converter.readFromJSON(PATH_TO_SETTING);
    }

    public List<String> getOrder() {
        List<String> orders = new ArrayList<>();
        orders.add(guest.getPersonCount() + " человек(а)");

        double totalAmount = 0;
        for (Day day : Day.values()) {
            if (day.getCount() != 0) {
                double dailyCoefficient = day.getPercent() / 100d + 1;
                long costPerDay = Math.round(guest.getCost() * dailyCoefficient);
                orders.add(costPerDay + "руб. * " + day.getCount() + " ночей");
                totalAmount += guest.getCost() * dailyCoefficient * day.getCount();
            }
        }
        int totalDays = Day.WEEKDAY.getCount() + Day.WEEKEND.getCount() + Day.HOLIDAY.getCount();
        orders.add("Средняя цена за сутки: " + Math.round(totalAmount / totalDays) + "руб.");

        double weeklyDiscountPercentDouble = weeklyDiscountPercent / 100d + 1;
        if (totalDays >= 7) {
            long discount = Math.round(totalAmount * (weeklyDiscountPercentDouble - 1));
            totalAmount = totalAmount - discount;
            orders.add("Недельная скидка: -" + discount
                    + "р (" + Math.round(totalAmount / totalDays) + "р/сут)");
        }

        orders.add("Общая стоимость: " + (int) totalAmount + "руб.");

        Converter.writeToJSON(PATH_TO_SETTING);
        return orders;
    }
}
