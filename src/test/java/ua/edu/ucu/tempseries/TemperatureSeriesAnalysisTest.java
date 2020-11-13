package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

//    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

//    @Ignore
    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.checkArray();
    }

    @Test(expected = InputMismatchException.class)
    public void testCheckMinTemp() {
        double[] temperatureSeries = {3.0, -290.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.checkMinTemp(temperatureSeries);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, -3.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 4.12;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.01);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 12.0, -7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -7.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 12.0, -7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 12.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 12.0, -7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, 6.0, 12.0, 9.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 9.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(8.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {3.0, 6.0, 12.0, 9.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, 6.0, 7.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(9.0);
        System.out.println(actualResult.toString());

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {3.0, 6.0, 12.0, 9.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {6.0, 12.0, 9.0, 7.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(6.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, 6.0, 12.0, 9.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(-3.0, 4.0);
        double[] expResult = {3.0, 6.0, 12.0, 9.0, 7.0, -3.0, 4.0};

        assertArrayEquals(expResult, seriesAnalysis.temperatureSeries, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, 6.0, 12.0, 9.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double avgTemp = seriesAnalysis.average();
        double devTemp = seriesAnalysis.deviation();
        double minTemp = seriesAnalysis.min();
        double maxTemp = seriesAnalysis.max();

        TempSummaryStatistics summary = seriesAnalysis.summaryStatistics();

        assertEquals(summary.getAvgTemp(), avgTemp, 0.00001);
        assertEquals(summary.getDevTemp(), devTemp, 0.00001);
        assertEquals(summary.getMinTemp(), minTemp, 0.00001);
        assertEquals(summary.getMaxTemp(), maxTemp, 0.00001);
    }

}
