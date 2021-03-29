package fyp;

import br.gov.frameworkdemoiselle.behave.controller.BehaveContext;
import org.junit.Test;

public class MyTest {
    private BehaveContext eng = BehaveContext.getInstance();

    @Test
    public void testAllStories() {
        eng.addSteps(new MySteps());
        eng.addStories("/stories/Confirm Flight Selection.story");
        eng.addStoriesReuse("/stories/Flight Tickets Search.story");
        eng.addStoriesReuse("/stories/Select a Suitable Flight.story");
        eng.run();
    }
}
