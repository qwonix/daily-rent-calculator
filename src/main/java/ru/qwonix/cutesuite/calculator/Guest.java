package ru.qwonix.cutesuite.calculator;

public enum Guest {
    One(1, 900),
    Two(2, 1000),
    Three(3, 1200),
    Four(4, 1400);

    final int personCount;
    int costPerNight;
    double percent;

    Guest(int personCount, int costPerNight) {
        this.personCount = personCount;
        this.costPerNight = costPerNight;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "personCount=" + personCount +
                ", costPerNight=" + costPerNight +
                ", percent=" + percent +
                '}';
    }

    public static Guest valueByCount(int count) {
        for (Guest guest : Guest.values()) {
            if (guest.personCount == count)
                return guest;
        }
        return null;
    }
}
