import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void openResponse() throws ParseException {
        //Mocka upp en return data från server
        String servResp =  "{\"data\":\"{\\\"p3\\\":{\\\"name\\\":\\\"Marcus\\\",\\\"favorite Color\\\":\\\"Green\\\",\\\"age\\\":\\\"34\\\"}}\",\"httpStatusCode\":200}";
        //String servResp = "\"data\":{\"p1\":{\"name\":\"Markus\",\"age\":56,\"favoriteColor\":\"Green\",\"data\":[1, 2, 3, 4, 5]}},\"httpStatusCode\":200";
        //                 "{\"data\":\"{\\\"p3\\\":{\\\"name\\\":\\\"Marcus\\\",\\\"favorite Color\\\":\\\"Green\\\",\\\"age\\\":\\\"34\\\"}}\",\"httpStatusCode\":200}\n"

        System.out.println(servResp);
        //Test
        assertEquals(Main.openResponse(servResp), "Marcus");
    }

    @Test
    void testConnectionToServer() throws ParseException {
        //Skapa Request JSON från menyval att hämta data
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("httpURL", "test");
        jsonRequest.put("httpMethod", "get");

        //Skapa JSON string
        String strRequest = jsonRequest.toJSONString();

        //Skicka Request till server, ta emot response
        String strResponse = Main.connectToServer(strRequest);

        //Skickar response till unpacking
        String strTestAnswer = Main.openResponse(strResponse);

        //Test med Assert
        assertEquals(strTestAnswer, "ABC");
    }
}