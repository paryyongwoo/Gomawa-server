package com.gomawa.gomawa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // method들을 가진 클래스
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc // 이걸 추가해줘야 MockMvc 빈을 사용 가능
class GomawaApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void controllerTest() throws Exception {

		this.mockMvc.perform(get("/api/hello"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void getLoginRequestUrl() throws Exception {

		this.mockMvc.perform(get("/api/login")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("url").exists());
	}

}
