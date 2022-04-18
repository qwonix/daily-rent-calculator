package ru.qwonix.cutesuite.calculator;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {

    private Guest guest = Guest.TWO;

    private int weeklyDiscountPercent = 7;

    public List<String> getOrder() {
        List<String> orders = new ArrayList<>();
        orders.add(guest.personCount + " человек(а)");

        double totalAmount = 0;
        for (Day day : Day.values()) {
            if (day.count != 0) {
                double dailyCoefficient = day.percent / 100d + 1;
                long costPerDay = Math.round(guest.getCost() * dailyCoefficient);
                orders.add(costPerDay + "руб. * " + day.count + " ночей");
                totalAmount += guest.getCost() * dailyCoefficient * day.count;
            }
        }
        int totalDays = Day.WEEKDAY.count + Day.WEEKEND.count + Day.HOLIDAY.count;
        orders.add("Средняя цена за сутки: " + Math.round(totalAmount / totalDays) + "руб.");

        double weeklyDiscountPercentDouble = weeklyDiscountPercent / 100d + 1;
        if (totalDays >= 7) {
            long discount = Math.round(totalAmount * (weeklyDiscountPercentDouble - 1));
            totalAmount = totalAmount - discount;
            orders.add("Недельная скидка: -" + discount
                    + "р (" + Math.round(totalAmount / totalDays) + "р/сут)");
        }

        orders.add("Общая стоимость: " + (int) totalAmount + "руб.");

        return orders;
    }
}
