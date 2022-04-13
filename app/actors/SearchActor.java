package actors;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import services.FreeLancerServices;

import java.time.Duration;

/**
 * This actor is used to implement the searches.
 * @author Pragya Tomar
 * @author Jasleen Kaur
 * @author Nipun Hedaoo
 * @author Alankrit Gupta
 */
public class SearchActor extends AbstractActor {

    /**
     * These are the props
     */
    public static Props getProps() {
        return Props.create(SearchActor.class);
    }

    private static final SupervisorStrategy strategy =
            new OneForOneStrategy(
                    10,
                    Duration.ofMinutes(1),
                    DeciderBuilder.match(ArithmeticException.class, e -> (SupervisorStrategy.Directive) SupervisorStrategy.resume())
                            .match(NullPointerException.class,e -> (SupervisorStrategy.Directive) SupervisorStrategy.restart())
                            .match(IllegalArgumentException.class, e -> (SupervisorStrategy.Directive) SupervisorStrategy.stop())
                            .matchAny(o -> (SupervisorStrategy.Directive) SupervisorStrategy.escalate())
                            .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }


    /**
     * This method decides which method will be called based
     * on different behaviours.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        String.class,
                        keywords -> {
                            try {
                                System.out.println(keywords);
                                Object results = FreeLancerServices.searchResults(keywords);
                                sender().tell(results, self());
                            } catch (Exception e) {
                                System.out.println("Error: Websocket Actor parsing error(" + keywords + ") " + e);
                            }
                        })
                .build();
    }

}
