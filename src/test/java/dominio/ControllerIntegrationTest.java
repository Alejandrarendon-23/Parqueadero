package dominio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import aplicacion.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Application.class })

public class ControllerIntegrationTest {
	private String placa;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void postIngreso() throws Exception {

		mockMvc.perform(post("/ingreso").contentType("application/json").content("{\r\n"
				+ "	\"placa\": \"QWE786\",\r\n" + "	\"tipo\": \"carro\"\r\n" + "\r\n" + "	\r\n" + "	\r\n" + "}"))
				.andExpect(status().isOk());
		;
	}

	@Test
	public void getCosto() throws Exception {
	
		mockMvc.perform(get("/costo?placa="+ "QWE786")).andExpect(status().isOk());
				
	}

	@Test
	public void getLista() throws Exception {
	
		mockMvc.perform(get("/listar")).andExpect(status().isOk());

	}

}
