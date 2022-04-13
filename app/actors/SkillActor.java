package actors;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import services.FreeLancerServices;

import java.time.Duration;

/**
 * @author Jasleen Kaur
 * This actor is used to implement the skills information page
 */
public class SkillActor extends AbstractActor {

    /**
     * These are the props
     * @return Props
     */

    public static Props getProps() {
        return Props.create(SkillActor.class);
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
     *
     *This method decides which method will be called based
     * on different behaviours.
     *
     */

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        String.class,
                        skillId -> {
                            try {
                                System.out.println(skillId);
                                Object results = FreeLancerServices.searchSkillResults(skillId);
                                sender().tell(results, self());
                            } catch (Exception e) {
                                System.out.println("Error: Websocket Actor parsing error(" + skillId + ") " + e);
                            }
                        })
                .build();
    }

}
