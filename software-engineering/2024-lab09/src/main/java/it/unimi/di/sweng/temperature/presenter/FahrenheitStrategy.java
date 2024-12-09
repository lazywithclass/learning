package it.unimi.di.sweng.temperature.presenter;

public enum FahrenheitStrategy implements ScaleStrategy {

    INSTANCE;

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 9 / 5 + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }
}
