
package services;
import javax.inject.Inject;

import play.libs.Json;
import play.libs.ws.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class FreeLancerServices implements WSBodyReadables, WSBodyWritables  {
    @Inject
    private WSClient ws = null;

    public FreeLancerServices() {

    }
    public void setWsClient(WSClient ws)
    {
        this.ws=ws;
    }
    public WSClient getWsClient()
    {
        return this.ws;
    }
    public CompletionStage searchResults(String phrase) {
//        WSRequest request = this.ws
//                .url("https://www.freelancer.com/api/projects/0.1/projects/active?query=\"nodejs\"&limit=10&job_details=true");
//                .addQueryParameter("query", phrase)
//                .addQueryParameter("limit", "10")
//                .addQueryParameter("job_details", "true");

//            WSRequest request = this.ws.url("https://www.freelancer.com/api/projects/0.1/projects/active?query=\"nodejs\"&limit=10&job_details=true");
        return ws
                .url("https://www.freelancer.com/api/projects/0.1/projects/active")
                .addQueryParameter("query", phrase)
                .addQueryParameter("limit", "10")
                .addQueryParameter("job_details", "true")
                .get()
                .thenApply(WsResponse-> Json.parse(WsResponse.getBody()))
                .toCompletableFuture();
    }
}

