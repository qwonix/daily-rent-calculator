package ru.qwonix.cutesuite.calculator;

public enum Guest {
    One(1, 900, -10),
    Two(2, 1000, 0),
    Three(3, 1200, 20),
    Four(4, 1400, 40);

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

    public void setCost(int cost) {
        this.percent =
                (int) Math.round(((cost / (double) Guest.Two.cost) - 1) * 100);
        this.cost = cost;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.cost = (int) (Guest.Two.cost * (percent / 100d + 1));
        this.percent = percent;
    }
}
