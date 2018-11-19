package com.baeldung.json;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsonEscapeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "escape.json=jackson"
})
public class JsonEscapeApplicationJacksonIntegrationTest extends JsonEscapeApplicationBaseIntegrationTest {

}