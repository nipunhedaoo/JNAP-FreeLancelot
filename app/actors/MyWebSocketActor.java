package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.time.Duration;
import java.util.ArrayList;

/**
 * This actor is used to implement the web socket page
 * @Alankrit Gupta
 * Nipun Hedaoo
 * Jasleen Kaur
 * Pragya Tomar
 */
public class MyWebSocketActor extends AbstractActor {

    /**
     * These are the props
     * @return Props
     */

    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    private Cancellable scheduled = null;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
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
                .match(String.class, message -> {
                    try {
                        System.out.println(message);
                        JsonNode data = Json.parse(message);
                        String type = data.get("type").asText();
                        String keywords = data.get("data").asText();
                        ActorRef actor = null;
                        if (type.equals("searchKeyword")) {
                            actor = getContext().actorOf((SearchActor.getProps()));
                        }

                        if(type.equals("readabilityIndex")){
                            actor = getContext().actorOf((FleschReadingIndexActor.getProps()));
                        }

                        if (scheduled != null) {
                            scheduled.cancel();
                        }
                        // tell the message to searchActor
                        scheduled = getContext().getSystem().getScheduler().scheduleAtFixedRate(
                                Duration.ZERO,
                                Duration.ofSeconds(10),
                                actor, keywords,
                                getContext().getDispatcher(),
                                self());

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: Websocket Actor parsing error(" + message + ") " + e);
                    }
                })
                .match(ArrayList.class, repositories -> {
                    JsonNode jsonData = Json.toJson(repositories);
                    if (!out.isTerminated())
                        out.tell(jsonData.toString(), self());
                })
                .build();
    }
}