
package helper;

import models.ProjectDetails;
import models.SearchResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Http;
import java.util.UUID;

import java.util.*;

/**
 * This class manages session for the Application.
 */

public class Session {
    private static final HashMap<String, Set<String>> sessionSearchResultsBySearchKeywordHashMap = new HashMap<>();
    private static final String SESSION_KEY = "sessionId";
    private static Logger logger = LoggerFactory.getLogger("play");

    public static String getSessionKey() {
        return SESSION_KEY;
    }

    /**
     * This method returns the SearchResults stored for the current session
     *
     * @param request Http Request
     * @return {@link LinkedHashMap} of SearchKeyword and {@link ProjectDetails}
     */


    public static String generateSessionValue() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static HashMap<String, SearchResultModel> getSearchResultsHashMapFromSession(Http.Request request, LinkedHashMap<String, SearchResultModel> searchResults) {
        String key = getSessionValue(request);
        logger.info(key);

        return getSearchResultsForSession(searchResults, sessionSearchResultsBySearchKeywordHashMap.get(key));
    }

    private static HashMap<String, SearchResultModel> getSearchResultsForSession(LinkedHashMap<String, SearchResultModel> searchResults, Set<String> searchKeywords){
        HashMap<String, SearchResultModel> result = new HashMap<>();
        if(searchKeywords!=null) {
            for (String key : searchKeywords) {
                result.put(key, searchResults.get(key));
            }
        }
        return result;
    }

    /**
     * This method stores the SearchResults for the current session
     *
     * @param request       Http Request
     * @param searchKeyword keyword for which SearchResults are fetched.
     */
    public static void setSessionSearchResultsHashMap(Http.Request request, String searchKeyword) {
        String key = getSessionValue(request);
        if(!sessionSearchResultsBySearchKeywordHashMap.containsKey(key)){
            sessionSearchResultsBySearchKeywordHashMap.put(key, new HashSet<>());
        }
        sessionSearchResultsBySearchKeywordHashMap.get(key).add(searchKeyword);
    }

    /**
     * @param request Http Request
     * @return Boolean whether session exists or not.
     */
    public static boolean isSessionExist(Http.Request request) {
        return request.session().get(SESSION_KEY).orElse(null) != null;
    }

    /**
     * @param request Http Request
     * @return String Session value or null.
     */

    public static String getSessionValue(Http.Request request) {
        return request.session().get(SESSION_KEY).orElse(null);
    }

}