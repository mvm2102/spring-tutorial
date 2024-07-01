package com.baeldung.testcontainers.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcSupportLiveTest {

	@Autowired
	HobbitRepository theShire;

	@Test
	void whenCallingSave_thenEntityIsPersistedToDb() {
		theShire.save(new Hobbit("Bilbo Baggnis"));

		assertThat(theShire.findAll())
		  .hasSize(1).first()
		  .extracting(Hobbit::getName).isEqualTo("Bilbo Baggnis");
	}
}
