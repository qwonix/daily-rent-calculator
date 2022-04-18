package ru.qwonix.cutesuite.calculator;

public enum Guest {
    ONE(1, 900, -10),
    TWO(2, 1000, 0),
    THREE(3, 1200, 20),
    FOUR(4, 1400, 40);

    final int personCount;
    private int cost;
    private int percent;

    Guest(int personCount, int cost, int percent) {
        this.personCount = personCount;
        this.cost = cost;
        this.percent = percent;
    }

    public static Guest valueByCount(int count) {
        for (Guest guest : Guest.values()) {
            if (guest.personCount == count)
                return guest;
        }
        return null;
    }

    public int getCost() {
        return cost;
    }

    public int getPercent() {
        return percent;
    }

    public void setCost(int cost) {
        this.percent =
                (int) Math.round(((cost / (double) Guest.TWO.cost) - 1) * 100);
        this.cost = cost;
    }

    public void setPercent(int percent) {
        this.cost = (int) (Guest.TWO.cost * (percent / 100d + 1));
        this.percent = percent;
    }
}
