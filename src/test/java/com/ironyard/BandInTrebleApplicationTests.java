package com.ironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironyard.entities.User;
import com.ironyard.services.BandManagerRepository;
import com.ironyard.services.MusicianRepository;
import com.ironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BandInTrebleApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BandInTrebleApplicationTests {

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	UserRepository users;

	@Autowired
	BandManagerRepository band_managers;

	@Autowired
	MusicianRepository musicians;

	@Test
	public void atestLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
		);
		Assert.assertTrue(users.count() != 0);
	}
}

