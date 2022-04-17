package ru.qwonix.cutesuite.calculator;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {

    private Guest guest = Guest.Two;

    private int weekdaysCount;
    private double weekdaysPercent;

    private int holidaysCount;
    private double holidaysPercent;

    private int weekendsCount;
    private double weekendsPercent;

    private double WeeklyDiscountPercent = 1.07;

    public List<String> getOrder() {
        List<String> orders = new ArrayList<>();

        orders.add(guest.personCount + " человек(а)");
        if (weekdaysCount != 0) {
            int costPerWeekday = (int) (guest.costPerNight * weekdaysPercent);
            orders.add(costPerWeekday + "руб. * " + weekdaysCount + " ночей");
        }
        if (weekendsCount != 0) {
            int costPerWeekend = (int) (guest.costPerNight * weekendsPercent);
            orders.add(costPerWeekend + "руб. * " + weekendsCount + " ночей");
        }
        if (holidaysCount != 0) {
            int costPerHoliday = (int) (guest.costPerNight * holidaysPercent);
            orders.add(costPerHoliday + "руб. * " + holidaysCount + " ночей");
        }

        double amount = guest.costPerNight * weekdaysPercent * weekdaysCount
                + guest.costPerNight * weekendsPercent * weekendsCount
                + guest.costPerNight * holidaysPercent * holidaysCount;

        if (weekdaysCount + weekendsCount + holidaysCount >= 7) {
            int discount = (int) (amount * (WeeklyDiscountPercent - 1));
            orders.add("Недельная скидка: -" + discount);
            amount = amount - discount;
        }

        orders.add("Общая стоимость: "
                + (int)amount
                + "руб.");

        return orders;
    }
}
