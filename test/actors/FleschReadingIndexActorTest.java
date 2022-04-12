package actors;


import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import akka.testkit.javadsl.TestKit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FleschReadingIndexActorTest {

    static ActorSystem actorSystem;
    private static TestKit testProbe;

    static Object mockObject = new Object();

    @Before
    public  void setup() {
        actorSystem = ActorSystem.create();
        testProbe = new TestKit(actorSystem);
    }

    @Test
    public void actorTest() {
        new TestKit(actorSystem) {
            {
                final TestKit testProbe = new TestKit(actorSystem);
                Props props = Props.create(FleschReadingIndexActor.class);
                Object results = new Object();
                TestActorRef<FleschReadingIndexActor> ref = TestActorRef.create(actorSystem, props);
                ref.tell(results, testProbe.getRef());
            }

        };
    }
}
