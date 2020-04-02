package com.baeldung.reader;

import com.baeldung.app.api.Flower;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FlowerReaderServiceTestWithoutSpring {

    @Mock
    private ObjectMapper objectMapper;

    private FlowerReaderService flowerReader;

    @BeforeEach
    public void setUp() {
        flowerReader = new FlowerReaderService(objectMapper);
    }

    @Test
    public void whenCallingWithJsonAsString_thenReturnsExpectedFlowerObject() throws JsonProcessingException {

        String flowerAsJson = "{\"name\": \"rose\", \"petals\": 100}";
        String roseName = "rose";
        Integer rosePetals = 100;
        Flower rose = new Flower(roseName, rosePetals);

        Mockito.when(objectMapper.readValue(flowerAsJson, Flower.class)).thenReturn(rose);
        Flower expectedRose = flowerReader.readFlower(flowerAsJson);

        Assertions.assertEquals(roseName, expectedRose.getName());
        Assertions.assertEquals(rosePetals, expectedRose.getPetals());
    }

    @Test
    public void whenCallingWithIncorrectJson_thenThrowsException() throws JsonProcessingException {

        String incorrectJson = "this is not a flower in JSON";

        Mockito.when(objectMapper.readValue(incorrectJson, Flower.class)).thenThrow(JsonProcessingException.class);

        Assertions.assertThrows(JsonProcessingException.class, () -> flowerReader.readFlower(incorrectJson));

    }
}
