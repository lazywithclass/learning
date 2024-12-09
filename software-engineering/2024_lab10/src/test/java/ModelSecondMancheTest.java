import it.unimi.di.sweng.slalom.Skier;
import it.unimi.di.sweng.slalom.presenters.SecondManchePresenter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ModelSecondMancheTest {

    @Test
    void testAddSecondMancheSkier() {
        SecondManchePresenter presenter = mock();

        it.unimi.di.sweng.slalom.model.ModelSecondManche SUT = new it.unimi.di.sweng.slalom.model.ModelSecondManche();
        SUT.addObserver(presenter);
        SUT.addSkier(new Skier("name1", 1.0));

        assertThat(SUT).extracting("skiers", as(LIST)).size().isEqualTo(1);
        verify(presenter).update(any(), any());
    }
}
