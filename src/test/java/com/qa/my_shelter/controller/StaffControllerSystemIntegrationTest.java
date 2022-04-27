package com.qa.my_shelter.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({ "test" })
@Sql(scripts = { "classpath:schema.sql", "classpath:staff-data.sql" }, 
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StaffControllerSystemIntegrationTest {

}
