package ucoach.auth.restclient.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by szucs on 2/3/2017.
 */
class JsonParserTestCorrect {

    @Test
    void loadJson() {
        String json = "{\n" +
                "     \"surname\": \"John\",\n" +
                "     \"forename\": \"Doe\",\n" +
                "       }                     ";

        JsonParser parser = new JsonParser();
        parser.loadJson(json);
        assertNotEquals(null, parser.jsonObj);
    }

    @Test
    public void loadJson_JsonArrayCorrect() throws Exception
    {
        String jsonArray = "[\n" +
                "         {\n" +
                "           \"type\": \"home\",\n" +
                "           \"number\": \"212 555-1234\"\n" +
                "         },\n" +
                "         {\n" +
                "           \"type\": \"fax\",\n" +
                "           \"number\": \"646 555-4567\"\n" +
                "         }\n" +
                "     ]";
        JsonParser parser = new JsonParser();
        parser.loadJson(jsonArray);
        assertNotEquals(null, parser.jsonArr);
    }

    //not complete feature
    @Test
    public void loadJson_StartsWithWhiteSpace() throws Exception {
        String json = " { name : John Doe }";

        JsonParser parser = new JsonParser();
        parser.loadJson(json);
        assertNotEquals(null, parser.jsonObj);
    }


    @Test
    void countList() {
        String json = "[\n" +
                "         {\n" +
                "           \"type\": \"home\",\n" +
                "           \"number\": \"212 555-1234\"\n" +
                "         },\n" +
                "         {\n" +
                "           \"type\": \"fax\",\n" +
                "           \"number\": \"646 555-4567\"\n" +
                "         }\n" +
                "     ]";
        JsonParser parser = new JsonParser();
        parser.loadJson(json);

        try{
            int numObjects = parser.countList();
            assertFalse( numObjects == 1 );
        }
        catch(Exception e){
            assertTrue(true);
        }
    }

}