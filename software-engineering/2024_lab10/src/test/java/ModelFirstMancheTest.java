import it.unimi.di.sweng.slalom.model.ModelFirstManche;
import it.unimi.di.sweng.slalom.presenters.FirstManchePresenter;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.Mockito.*;

public class ModelFirstMancheTest {

    private ModelFirstManche SUT;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        SUT = new ModelFirstManche();
        scanner = mock();

        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("name1;1.0", "name2;2.0");
    }

    @Test
    public void shouldReadFirstMancheSkiersFromFileTest() {
        SUT.readFilePrimaManche(scanner);

        AssertionsForClassTypes.assertThat(SUT)
                .extracting("skiers", as(LIST))
                .size()
                .isEqualTo(2);
    }

    @Test
    public void notifyPresenterTest() {
        FirstManchePresenter presenter = mock();
        SUT.addObserver(presenter);
        SUT.readFilePrimaManche(scanner);
        verify(presenter, times(2)).update(any(), any());
    }

    @Test
    void shouldReturnState() {
        SUT.readFilePrimaManche(scanner);

        assertThat(SUT)
                .extracting("skiers", as(LIST))
                .containsExactlyElementsOf(SUT.getState());
    }



}
