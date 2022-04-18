package ru.qwonix.cutesuite.calculator;

public enum Day {
    WEEKDAY(5, 0),
    WEEKEND(2, 20),
    HOLIDAY(1, 30);

    public int count;
    public int percent;

    Day(int count, int percent) {
        this.count = count;
        this.percent = percent;
    }
}
