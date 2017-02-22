package ucoach.auth.restclient.util;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DynamicTest.*;

import ucoach.auth.extension.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by szucs on 2/3/2017.
 */
class JsonParserTestAnnotations {

    public JsonParser m_Parser;

    @BeforeEach
    void Setup() {
        m_Parser = new JsonParser();
    }

    @Test
    void loadJson() {
        String json = "{\n" +
                "     \"surname\": \"John\",\n" +
                "     \"forename\": \"Doe\",\n" +
                "       }                     ";

        m_Parser.loadJson(json);
        assertNotEquals(null, m_Parser.jsonObj);
    }

    @Test
    void loadJson_CheckObject()
    {
        assertNotEquals(null, m_Parser.jsonObj);
    }


    @Test
    @DisplayName("Abraka - Dabra")
    void loadJson_Display()
    {
        String json = "{\n" +
                "     \"surname\": \"John\",\n" +
                "     \"forename\": \"Doe\",\n" +
                "       }                     ";

        m_Parser.loadJson(json);
        assertNotEquals(null, m_Parser.jsonObj);
    }


    @Test
    @Tag("Demonstrate @BeforeEach")//later
    void loadJson_Tagging()
    {
        String json = "{\n" +
                "     \"surname\": \"John\",\n" +
                "     \"forename\": \"Doe\",\n" +
                "       }                     ";

        m_Parser.loadJson(json);
        assertNotEquals(null, m_Parser.jsonObj);
    }

    @Test
    @Tag("Demonstrate @BeforeEach")
    void loadJson_CheckObject_Tagging()//later
    {
        assertNotEquals(null, m_Parser.jsonObj);
    }



    @Test
    @Disabled("Demonstrate @Disabled")
    void countList() {

    }

    //Homework make smiley visible at the Test View
    @Test
    @DisplayName("😱")
    void loadJson_Happy()
    {
        assertTrue(true);
    }


    @Nested
    @DisplayName("When multiple Jsons added")
    class WhenMultipleJsons{

        @Test
        void loadJson_MultipleJsons()
        {
            String json = "{\n" +
                    "     \"surname\": \"John\",\n" +
                    "     \"forename\": \"Doe\",\n" +
                    "       }                     ";

            for(int i = 0; i < 10; i++){
                m_Parser.loadJson(json);
            }

            assertTrue(m_Parser.jsonObj == null);
        }

        @Test
        void isEmpty()
        {
            assertTrue(m_Parser.jsonObj == null);
        }

    }

    @DisplayName("Demonstrate: @TestFactory Bad")
    @TestFactory
    List<String> loadJson_Generate()
    {
        return Arrays.asList("{ surname : Adam }");
    }

    @DisplayName("Demonstrate: @TestFactory Correct")
    @TestFactory
    Collection<DynamicTest> dynamicTest_FromJson() {
        return Arrays.asList(
            DynamicTest.dynamicTest("Passing test 1", ()->assertTrue(true)),
            DynamicTest.dynamicTest("Passing test 2", ()->assertEquals(4, 2 * 2)),
            DynamicTest.dynamicTest("Passing test 3", ()->{ m_Parser.loadJson("{ surname : Adam }"); })
        );
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    void loadJson_Mockito()
    {
        //later on
    }

}