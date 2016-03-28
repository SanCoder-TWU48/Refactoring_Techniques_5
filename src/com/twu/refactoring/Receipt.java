package com.twu.refactoring;

public class Receipt {
    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double totalCost() {
        double totalCost = 0;

        // fixed charges
        totalCost += 50;

        // taxi charges
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? 1.2 : 1.0;
        if(taxi.isAirConditioned()) {
            totalCost += calculateFirstDistanceCost(totalKms, peakTimeMultiple, 20);
            totalCost += calculateRestDistanceCost(totalKms, peakTimeMultiple, 17);
        } else {
            totalCost += calculateFirstDistanceCost(totalKms, peakTimeMultiple, 15);
            totalCost += calculateRestDistanceCost(totalKms, peakTimeMultiple, 12);
        }

        return totalCost * (1 + 0.1);
    }

    private double calculateRestDistanceCost(int totalKms, double peakTimeMultiple, double acRate) {
        return Math.max(0, totalKms - 10) * acRate * peakTimeMultiple;
    }

    private double calculateFirstDistanceCost(int totalKms, double peakTimeMultiple, double acRate) {
        return Math.min(10, totalKms) * acRate * peakTimeMultiple;
    }
}
