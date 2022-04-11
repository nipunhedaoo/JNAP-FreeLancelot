package actors;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import models.ProjectDetails;
import services.FreeLancerServices;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class FleschReadingIndexActor extends AbstractActor{

    public static Props getProps() {
        return Props.create(FleschReadingIndexActor.class);
    }

    FreeLancerServices fr = new FreeLancerServices();

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

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(
                        List.class,
                        list -> {
                            try {
                                Double results = fr.readabilityIndex(list);
                                System.out.println("flck actor" + results);
                                sender().tell(results, self());
                            } catch (Exception e) {
                                System.out.println("Error: Cannot find readabilityIndex");
                            }
                        })
                .build();
    }


}
