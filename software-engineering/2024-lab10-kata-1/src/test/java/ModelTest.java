import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.FirstManchePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ModelTest {

    private Scanner scannerMock;
    private Model SUT;

    @BeforeEach
    public void setup() {
        scannerMock = mock();
        when(scannerMock.hasNextLine()).thenReturn(true, true, false);
        when(scannerMock.nextLine()).thenReturn("name;1.00", "name2;2.00");

        SUT = new Model();
    }

    @Test
    void shouldReadFilePrimaManche() {
        SUT.readFilePrimaManche(scannerMock);

        assertThat(SUT)
                .extracting("firstMancheSkiers", as(LIST))
                .containsExactly(new Skier("name", 100), new Skier("name2", 200));

    }

    @Test
    void shouldNotifyPresenters() {
        FirstManchePresenter presenterMock = mock();
        SUT.addObserver(presenterMock);
        SUT.readFilePrimaManche(scannerMock);
        verify(presenterMock).update(any(), any(), any());
    }

    @Test
    void testGetLastSkier() {
        SUT.readFilePrimaManche(scannerMock);

        List<Skier> last = SUT.getLastSkier();
        assertThat(last).matches(skier -> skier.contains(new Skier("name2", 200)) && skier.size() == 1);
    }

}
