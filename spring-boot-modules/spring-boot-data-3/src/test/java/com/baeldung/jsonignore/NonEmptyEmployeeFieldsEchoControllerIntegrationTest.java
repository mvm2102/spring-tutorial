package com.baeldung.jsonignore;

import static com.baeldung.jsonignore.controller.EmptyEmployeeEchoController.USERS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.baeldung.jsonignore.emptyfields.Employee;
import com.baeldung.jsonignore.emptyfields.PhoneNumber;
import com.baeldung.jsonignore.emptyfields.Salary;
import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

class NonEmptyEmployeeFieldsEchoControllerIntegrationTest extends AbstractEmployeeEchoControllerBaseTest {

    @ParameterizedTest
    @MethodSource
    void giveEndpointWhenSendEmployeeThanReceiveThatUserBackIgnoringEmptyValues(final Employee expected) throws Exception {
        final Predicate<Field> nullField = s -> isFieldNull(expected, s);
        final Predicate<Field> absentField = s -> isFieldAbsent(expected, s);
        final Predicate<Field> emptyField = s -> isFieldEmpty(expected, s);
        List<String> nullOrAbsentOrEmptyFields = filterFieldsAndGetNames(expected, nullField.or(absentField).or(emptyField));
        List<String> nonNullAndNonAbsentAndNonEmptyFields = filterFieldsAndGetNames(expected,
            nullField.negate().and(absentField.negate().and(emptyField.negate())));
        final String payload = mapper.writeValueAsString(expected);
        final MvcResult result = mockMvc.perform(post(USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        final String response = result.getResponse().getContentAsString();
        final JsonNode jsonNode = mapper.readTree(response);

        nullFieldsShouldBeMissing(nullOrAbsentOrEmptyFields, jsonNode);
        nonNullFieldsShouldNonBeMissing(nonNullAndNonAbsentAndNonEmptyFields, jsonNode);
    }

    static Stream<Arguments> giveEndpointWhenSendEmployeeThanReceiveThatUserBackIgnoringEmptyValues() {
        final Salary baseSalary = new Salary(BigDecimal.TEN);
        final List<PhoneNumber> phones = Arrays.asList(new PhoneNumber("123-456"), new PhoneNumber("789-012"));
        return Stream.of(
            Arguments.of(new Employee(1L, "John", "Doe", Optional.empty(), "ʤɒn dəʊ", new ArrayList<>())),
            Arguments.of(new Employee(1L, null, "Doe", Optional.of(baseSalary), "dəʊ", new ArrayList<>())),
            Arguments.of(new Employee(1L, "John", null, Optional.empty(), "ʤɒn", new ArrayList<>())),
            Arguments.of(new Employee(1L, null, null, Optional.of(baseSalary), null, new ArrayList<>())),
            Arguments.of(new Employee(1L, "John", "Doe", Optional.empty(), "ʤɒn dəʊ", phones)),
            Arguments.of(new Employee(1L, null, "Doe", Optional.of(baseSalary), "dəʊ", phones)),
            Arguments.of(new Employee(1L, "John", null, Optional.empty(), "ʤɒn", phones)),
            Arguments.of(new Employee(1L, null, null, Optional.of(baseSalary), null, phones))
        );
    }

}