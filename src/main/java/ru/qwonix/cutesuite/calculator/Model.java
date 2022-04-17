package ru.qwonix.cutesuite.calculator;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {

    private Guest guest = Guest.Two;

    private int weekdaysCount = 5;
    private int weekdaysPercent = 0;

    private int weekendsCount = 2;
    private int weekendsPercent = 20;

    private int holidaysCount = 1;
    private int holidaysPercent = 30;

    private int weeklyDiscountPercent = 7;

    public List<String> getOrder() {
        List<String> orders = new ArrayList<>();

        double weekdaysPercentDouble = weekdaysPercent / 100d + 1;
        double weekendsPercentDouble = weekendsPercent / 100d + 1;
        double holidaysPercentDouble = holidaysPercent / 100d + 1;
        double weeklyDiscountPercentDouble = weeklyDiscountPercent / 100d + 1;

        orders.add(guest.personCount + " человек(а)");
        if (weekdaysCount != 0) {
            int costPerWeekday = (int) (guest.getCost() * weekdaysPercentDouble);
            orders.add(costPerWeekday + "руб. * " + weekdaysCount + " ночей");
        }
        if (weekendsCount != 0) {
            int costPerWeekend = (int) (guest.getCost() * weekendsPercentDouble);
            orders.add(costPerWeekend + "руб. * " + weekendsCount + " ночей");
        }
        if (holidaysCount != 0) {
            int costPerHoliday = (int) (guest.getCost() * holidaysPercentDouble);
            orders.add(costPerHoliday + "руб. * " + holidaysCount + " ночей");
        }

        double amount = guest.getCost() *
                        (weekdaysPercentDouble * weekdaysCount
                        + weekendsPercentDouble * weekendsCount
                        + holidaysPercentDouble * holidaysCount);

        if (weekdaysCount + weekendsCount + holidaysCount >= 7) {
            int discount = (int) (amount * (weeklyDiscountPercentDouble - 1));
            orders.add("Недельная скидка: -" + discount);
            amount = amount - discount;
        }

        orders.add("Общая стоимость: " + (int) amount + "руб.");

        return orders;
    }
}
