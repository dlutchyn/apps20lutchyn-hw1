package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {

    public double[] temperatureSeries;
    private int arrayLength;
    final double lowerEdge = -273.0;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = null;
        this.arrayLength = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries)
            throws InputMismatchException {
        this.checkMinTemp(temperatureSeries);
        this.arrayLength = temperatureSeries.length;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                this.arrayLength);
    }

    public void checkArray() throws IllegalArgumentException {
        if (this.arrayLength == 0) {
            throw new IllegalArgumentException(
                    "Empty temperature series error!");
        }
    }

    public void checkMinTemp(double[] temperatureArray)
            throws InputMismatchException {
        for (double temperature: temperatureArray) {
            if (temperature < lowerEdge) {
                throw new InputMismatchException(
                        "Temperature can't be below absolute zero!");
            }
        }
    }

    public double average() throws IllegalArgumentException {
        this.checkArray();

        double average = 0;

        for (int i = 0; i < this.arrayLength; ++i) {
            average += this.temperatureSeries[i];
        }

        return average / this.arrayLength;
    }

    public double deviation() throws IllegalArgumentException {
        this.checkArray();

        double mean = this.average();
        double meanDistanceSum = 0;

        for (int i = 0; i < this.arrayLength; ++i) {
            meanDistanceSum += Math.pow(this.temperatureSeries[i] - mean, 2);
        }

        return Math.sqrt(meanDistanceSum / this.arrayLength);
    }

    public double min() throws IllegalArgumentException {
        this.checkArray();

        double minimal = Double.POSITIVE_INFINITY;

        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] < minimal) {
                minimal = this.temperatureSeries[i];
            }
        }

        return minimal;
    }

    public double max() throws IllegalArgumentException {
        this.checkArray();

        double maximal = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] > maximal) {
                maximal = this.temperatureSeries[i];
            }
        }

        return maximal;
    }

    public double findTempClosestToZero()
            throws IllegalArgumentException {
        return this.findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue)
            throws IllegalArgumentException {
        this.checkArray();

        double closest = 0;
        double distance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < this.arrayLength; ++i) {
            double tempDist = Math.abs(this.temperatureSeries[i] - tempValue);
            if (tempDist < distance) {
                distance = tempDist;
                closest = this.temperatureSeries[i];
            } else if (tempDist == distance) {
                closest = Math.max(this.temperatureSeries[i], closest);
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue)
            throws IllegalArgumentException {
        this.checkArray();

        int newLength = 0;
        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] < tempValue) {
                newLength += 1;
            }
        }

        double[] newArray = new double[newLength];
        int index = 0;
        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] < tempValue) {
                newArray[index] = this.temperatureSeries[i];
                index += 1;
            }
        }

        return newArray;
    }

    public double[] findTempsGreaterThen(double tempValue)
            throws IllegalArgumentException {
        this.checkArray();

        int newLength = 0;
        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] >= tempValue) {
                newLength += 1;
            }
        }

        double[] newArray = new double[newLength];
        int index = 0;
        for (int i = 0; i < this.arrayLength; ++i) {
            if (this.temperatureSeries[i] >= tempValue) {
                newArray[index] = this.temperatureSeries[i];
                index += 1;
            }
        }
        return newArray;
    }

    public TempSummaryStatistics summaryStatistics()
            throws IllegalArgumentException {
        this.checkArray();

        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        return new TempSummaryStatistics(avgTemp, devTemp,
                minTemp, maxTemp);
    }

    public int addTemps(double... temps) throws InputMismatchException {
        this.checkMinTemp(temps);

        int addLength = temps.length;
        this.temperatureSeries = Arrays.copyOf(this.temperatureSeries,
                this.arrayLength + addLength);

        int index = this.arrayLength;
        for (double temperature: temps) {
            this.temperatureSeries[index] = temperature;
            index += 1;
        }
        this.arrayLength = this.temperatureSeries.length;
        return this.arrayLength;
    }
}
