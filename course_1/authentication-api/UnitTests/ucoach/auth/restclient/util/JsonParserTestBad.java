package ucoach.auth.restclient.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by szucs on 2/3/2017.
 */
class JsonParserTestBad {

    String testPassJson = "" +
            "{\n" +
            "     \"surname\": \"John\",\n" +
            "     \"forename\": \"Doe\",\n" +
            "     \"age\": 25,\n" +
            "     \"address\":\n" +
            "     {\n" +
            "         \"house number\": \"2. street 21.\",\n" +
            "         \"city\": \"New York\",\n" +
            "         \"state\": \"NY\",\n" +
            "         \"area code\": \"10021\"\n" +
            "     },\n" +
            "     \"telephone number\":\n" +
            "     [\n" +
            "         {\n" +
            "           \"type\": \"home\",\n" +
            "           \"number\": \"212 555-1234\"\n" +
            "         },\n" +
            "         {\n" +
            "           \"type\": \"fax\",\n" +
            "           \"number\": \"646 555-4567\"\n" +
            "         }\n" +
            "     ]\n" +
            "}";

    String testFailJson = "12345454656";


    String testPassJsonArray =
            "     [\n" +
                    "         {\n" +
                    "           \"type\": \"home\",\n" +
                    "           \"number\": \"212 555-1234\"\n" +
                    "         },\n" +
                    "         {\n" +
                    "           \"type\": \"fax\",\n" +
                    "           \"number\": \"646 555-4567\"\n" +
                    "         }\n" +
                    "     ]\n" ;

    String testFailJsonArray = "ABCDEFGHIJK";

    String testFailJsonArrayUnexpected = "[ ABRAKA DABRA";


    @Test
    void loadJson() {
        JsonParser parser = new JsonParser();
        parser.loadJson(testPassJson);
        assertNotEquals(null, parser.jsonObj);

        parser = new JsonParser(); //clear object
        parser.loadJson(testFailJson);
        assertEquals(null, parser.jsonObj);

        parser = new JsonParser();
        parser.loadJson(testPassJsonArray);
        assertNotEquals(null, parser.jsonArr);

        parser = new JsonParser();
        parser.loadJson(testFailJsonArray);
        assertEquals(null, parser.jsonArr);

        parser = new JsonParser();
        parser.loadJson(testFailJsonArray);
        assertEquals(null, parser.jsonArr);
    }
}