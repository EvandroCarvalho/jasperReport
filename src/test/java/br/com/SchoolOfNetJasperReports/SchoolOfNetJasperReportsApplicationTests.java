package br.com.SchoolOfNetJasperReports;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.SchoolOfNetJasperReports.controller.ProductController;
import br.com.SchoolOfNetJasperReports.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
//@ContextConfiguration({"file:src/main/web/WEB-INF/server-application-context.xml"})
public class SchoolOfNetJasperReportsApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Ignore
	public void contextLoads() {
	}
	
	@MockBean
	private ProductRepository ProductRepository;
	
	@Test
	public void listAll() throws Exception {
		this.mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk());
	}

}
