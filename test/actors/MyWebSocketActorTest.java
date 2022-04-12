package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import akka.testkit.javadsl.TestKit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MyWebSocketActorTest {

    static ActorSystem actorSystem;
    private static TestKit testProbe;
    static ActorRef actorRef;

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
                Props props = Props.create(MyWebSocketActor.class,actorRef);
                Object results = new Object();

                TestActorRef<MyWebSocketActor> ref = TestActorRef.create(actorSystem, props);
                ref.tell(results, testProbe.getRef());
            }

        };
    }
}
