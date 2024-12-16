import it.unimi.di.sweng.esame.model.Excursion;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.Observer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ModelTest {

    private Model SUT;
    private Scanner scannerMock;
    private Observer<List<Excursion>> excursionPresenterMock;

    @BeforeEach
    public void setup() {
        SUT = new Model();
        scannerMock = mock();

        excursionPresenterMock = mock();
        SUT.addObserver(excursionPresenterMock);

        when(scannerMock.hasNextLine()).thenReturn(true, true, false);
        when(scannerMock.nextLine()).thenReturn("Excursion1,30.00,10", "Excursion2,40.00,20");

    }

    @Test
    public void testReadFile() {
        SUT.readFile(scannerMock);

        assertThat(SUT).extracting("excursions", as(MAP)).size().isEqualTo(2);
    }

    @Test
    public void testReadFileUpdateView() {
        SUT.readFile(scannerMock);

        verify(excursionPresenterMock).update(any(), any());
    }

    @Test
    void shouldFailAddingABookingWhenActedUponView() {
        assertThrows(IllegalArgumentException.class, () -> SUT.addBooking("Luigi", "Crema"), "Invalid excursion name provided.");
    }

    @Test
    void whenAddingANewBookingShouldDecreasedBookingAvailabilityByOne() {
        SUT.readFile(scannerMock);
        SUT.addBooking("Luigi", "Excursion2");

        assertThat(SUT)
                .extracting("excursions", as(MAP))
                .containsEntry("Excursion2", new Excursion("Excursion2", 40.00, 19));
    }

    @Test
    void testOnlyKeepExcursionsWithPositiveNonZeroAvailabilities() {
        scannerMock = mock();
        when(scannerMock.nextLine()).thenReturn("Excursion1,30.00,1");
        when(scannerMock.hasNextLine()).thenReturn(true, false);
        SUT.readFile(scannerMock);

        assertThat(SUT).extracting("excursions", as(MAP)).size().isEqualTo(1);
        SUT.addBooking("Luigi", "Excursion1");
        assertThat(SUT).extracting("excursions", as(MAP)).size().isEqualTo(0);
    }

    @Test
    void testPayDebit() {
        SUT.readFile(scannerMock);
        SUT.addBooking("Luigi", "Excursion2");
        SUT.payDebit("Luigi", 39.00);
        assertThat(SUT).extracting("bookings", as(MAP)).containsEntry("Luigi", 1.0);
    }

    @Test
    void testPayDebitShouldThrowOnDebtExceeded() {
        SUT.readFile(scannerMock);
        SUT.addBooking("Luigi", "Excursion2");

        assertThrows(IllegalArgumentException.class, () -> SUT.payDebit("Luigi", 41.0f), "Can't pay more than you owe!");
    }
}
