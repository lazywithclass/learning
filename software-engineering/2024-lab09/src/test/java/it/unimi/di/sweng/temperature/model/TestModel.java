package it.unimi.di.sweng.temperature.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.*;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import org.junit.jupiter.api.Test;

public class TestModel {

  @Test
  public void testSetterGetter() {
    Model model = new TemperatureModel();
    //inizializzazione
    assertThat(model.getTemp()).isCloseTo(0, within(0.01));
    //setter
    model.setTemp(42.42);
    //getter
    assertThat(model.getTemp()).isCloseTo(42.42, within(0.01));
  }

  @Test
  public void testNotifyObservers() {
    Observer<Double> obs =  mock();
    Observer<Double> obs1 = mock();

    TemperatureModel model = new TemperatureModel();

    model.addObserver(obs);
    model.addObserver(obs1);

    model.setTemp(42.42);

    verify(obs).update(model, 42.42);
    verify(obs1).update(model, 42.42);
  }

  @Test
  public void testNotifyObserversOnlyIfChanged() {
    Observer<Double> obs =  mock(Observer.class);
    Observer<Double> obs1 = mock(Observer.class);

    TemperatureModel model = new TemperatureModel();

    model.addObserver(obs);
    model.addObserver(obs1);

    model.setTemp(42.42);
    model.setTemp(42.40);
    model.setTemp(42.40);

    verify(obs, times(2)).update(eq(model), any());
    verify(obs1, times(2)).update(eq(model), any());
  }

  @Test
  public void testGetStateAfterSetTemp() {
    TemperatureModel model = new TemperatureModel();
    model.setTemp(42.42);
    assertThat(model.getState()).isCloseTo(42.42, within(0.01));
  }
}
