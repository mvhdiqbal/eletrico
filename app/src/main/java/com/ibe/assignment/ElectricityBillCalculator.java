package com.ibe.assignment;


public class ElectricityBillCalculator {

    public double calculateElectricityBill(double units, double rebatePercentage) {
        double totalCharge = 0;

        if (units <= 200) {
            totalCharge = units * 21.8;
        } else if (units <= 300) {
            totalCharge = 200 * 21.8 + (units - 200) * 33.4;
        } else if (units <= 600) {
            totalCharge = 200 * 21.8 + 100 * 33.4 + (units - 300) * 51.6;
        } else {
            totalCharge = 200 * 21.8 + 100 * 33.4 + 300 * 51.6 + (units - 600) * 54.6;
        }

        double rebate = totalCharge * (rebatePercentage / 100);
        totalCharge -= rebate;

        return totalCharge;
    }
}








