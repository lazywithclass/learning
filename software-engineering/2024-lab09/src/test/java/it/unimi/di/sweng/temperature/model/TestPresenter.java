package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.FahrenheitStrategy;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.TemperaturePresenter;
import it.unimi.di.sweng.temperature.view.MyTextView;
import it.unimi.di.sweng.temperature.view.View;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.mockito.Mockito.*;

public class TestPresenter {

    @Test
    public void testConvertFahrenheitFromCelsius() {
        ScaleStrategy scale = FahrenheitStrategy.INSTANCE;
        double temperature = 42.42;
        double converted = scale.convertFromCelsius(temperature);
        assertThat(converted).isCloseTo(108.36, within(0.01));
    }

    @Test
    public void testConvertCelsiusFromFarhenheit() {
        ScaleStrategy scale = FahrenheitStrategy.INSTANCE;
        double temperature = 108.36;
        double converted = scale.convertToCelsius(temperature);
        assertThat(converted).isCloseTo(42.42, within(0.01));
    }

    @Test
    public void testConvertCelsiusFromCelcius() {
        ScaleStrategy scale = ScaleStrategy.IDENTITY;
        double temperature = 42.42;
        double converted = scale.convertToCelsius(temperature);
        assertThat(converted).isCloseTo(42.42, within(0.01));
    }

    @Test
    public void testPresenterShouldUpdateModel() {
        MyTextView view = mock(MyTextView.class);

        TemperatureModel model = mock(TemperatureModel.class);
        when(model.getTemp()).thenReturn(4242.42);

        ScaleStrategy strategy = mock();
        when(strategy.convertToCelsius(4242.42)).thenReturn(4242.42);

        TemperaturePresenter SUT = new TemperaturePresenter(view, model, strategy);
        SUT.action("4242.42");

        verify(model).setTemp(4242.42);
        verify(strategy).convertToCelsius(4242.42);
    }

    @Test
    public void testPresenterShouldListenToModelChangesAndUpdateView() {
        MyTextView view = mock(MyTextView.class);

        TemperatureModel model = mock(TemperatureModel.class);
        when(model.getTemp()).thenReturn(4242.42);

        ScaleStrategy strategy = mock();
        when(strategy.convertFromCelsius(4242.42)).thenReturn(4242.42);

        TemperaturePresenter SUT = new TemperaturePresenter(view, model, strategy);
        SUT.update(null, 4242.42);

        verify(view).setValue("4242.42");
    }
}
