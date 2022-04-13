package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.time.Duration;

public class MyWebSocketActor extends AbstractActor {

    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    private Cancellable scheduled = null;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    try {
                        JsonNode data = Json.parse(message);
                        String type = data.get("type").asText();
                        String keywords = data.get("data").asText();
                        ActorRef actor = null;
                        if (type.equals("searchTerms")) {
                            actor = getContext().actorOf((SearchActor.getProps()));
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
                .match(Object.class, searchResult -> {
                    System.out.println(searchResult);
                    if (!out.isTerminated()) {
                        out.tell(searchResult.toString(), self());
                    }
                })
                .build();
    }
}