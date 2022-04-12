package actors;
import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import services.FreeLancerServices;

import java.time.Duration;


public class EmployerActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(EmployerActor.class);
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

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        String.class,
                        ownerID -> {
                            try {
                                System.out.println(ownerID);
                                Object results = FreeLancerServices.employerDetails(ownerID);
                                sender().tell(results, self());
                            } catch (Exception e) {
                                System.out.println("Error: Websocket Actor parsing error(" + ownerID + ") " + e);
                            }
                        })
                .build();
    }

}