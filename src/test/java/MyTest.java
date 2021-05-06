import br.gov.frameworkdemoiselle.behave.controller.BehaveContext;
import org.junit.Test;


/*
 *
 *   FYP Project: Automated Assessment of Domain Models using BDD Story Parsing
 *   Developed by Thomas Kiely - 17185203
 *   Supervisor: Thiago Silva
 *
 */
public class MyTest {
    private BehaveContext eng = BehaveContext.getInstance();

    @Test
    public void testAllStories() throws Exception {
        MySteps mySteps = new MySteps();
        eng.addSteps(mySteps);

        eng.addStories("/stories/US1.story");
        eng.addStories("/stories/US2.story");
        eng.addStories("/stories/US3.story");
        eng.addStories("/stories/US4.story");
        eng.addStories("/stories/US5.story");
        eng.addStories("/stories/US6.story");
        eng.addStories("/stories/US7.story");


        eng.run();
    }

}
