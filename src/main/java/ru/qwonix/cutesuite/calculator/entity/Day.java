package ru.qwonix.cutesuite.calculator.entity;

public enum Day {
    WEEKDAY(5, 0),
    WEEKEND(2, 20),
    HOLIDAY(1, 30);

    private int count;
    private int percent;

    Day(int count, int percent) {
        this.count = count;
        this.percent = percent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
