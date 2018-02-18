package ke.co.kinyua.forzateams;

/**
 * Created by Kinyua on 2/18/18.
 */

import com.google.gson.Gson;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import ke.co.kinyua.forzateams.web.result_models.Team;

import static org.junit.Assert.*;

public class JSONTest {
    public static final String json = "[\n" +
            "    {\n" +
            "        \"name\": \"Arsenal FC\",\n" +
            "        \"national\": false,\n" +
            "        \"country_name\": \"England\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"FC Barcelona\",\n" +
            "        \"national\": false,\n" +
            "        \"country_name\": \"Spain\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Sweden\",\n" +
            "        \"national\": true,\n" +
            "        \"country_name\": \"Sweden\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Inter Milan\",\n" +
            "        \"national\": false,\n" +
            "        \"country_name\": \"Italy\"\n" +
            "    }\n" +
            "]";
    @Test
    public void jsonIsCorrect() throws Exception {

        Team[] teams = new Gson().fromJson(json, Team[].class);
        assertNotNull(teams);

        for (Team team: teams) {
            assertThat(Team.class.cast(team),matches(team));
        }
        assertEquals("Arsenal FC",teams[0].getName());
    }
    public static Matcher matches(final Object expected){

        return new BaseMatcher() {

            protected Object theExpected = expected;

            public boolean matches(Object o) {
                return theExpected.equals(o);
            }

            public void describeTo(Description description) {
                description.appendText(theExpected.toString());
            }
        };
    }
}
