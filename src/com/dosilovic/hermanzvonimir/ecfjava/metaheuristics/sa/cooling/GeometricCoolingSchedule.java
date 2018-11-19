package com.dosilovic.hermanzvonimir.ecfjava.metaheuristics.sa.cooling;

import java.util.Iterator;

public class GeometricCoolingSchedule extends AbstractCoolingSchedule {

    private double alpha;
    private int    numberOfIterations;
    private double finalTemperature;

    public GeometricCoolingSchedule(int numberOfIterations, double initialTemperature, double finalTemperature) {
        super(initialTemperature);
        this.numberOfIterations = numberOfIterations;
        this.finalTemperature = finalTemperature;
        calculateAlpha();
    }

    @Override
    public void setInitialTemperature(double initialTemperature) {
        super.setInitialTemperature(initialTemperature);
        calculateAlpha();
    }

    public double getFinalTemperature() {
        return finalTemperature;
    }

    public void setFinalTemperature(double finalTemperature) {
        this.finalTemperature = finalTemperature;
        calculateAlpha();
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
        calculateAlpha();
    }

    @Override
    public double getTemperature(int iteration) {
        return Math.pow(alpha, iteration) * initialTemperature;
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<>() {
            private int currentIteration;

            @Override
            public boolean hasNext() {
                return currentIteration < numberOfIterations;
            }

            @Override
            public Double next() {
                return getTemperature(currentIteration++);
            }
        };
    }

    private void calculateAlpha() {
        alpha = Math.pow(finalTemperature / initialTemperature, 1.0 / (double) (numberOfIterations - 1));
    }
}
