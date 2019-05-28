package br.com.SchoolOfNetJasperReports.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.SchoolOfNetJasperReports.model.Product;
import br.com.SchoolOfNetJasperReports.repository.ProductRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	@Qualifier("dataSource")
	private DataSource datasource;
	
	@Autowired
	private ProductRepository productRepository;
	@GetMapping("/get")
	public List<Product> test() {
		return productRepository.findAll();
	}
	
	@GetMapping(path = "/report")
	public void printReport(HttpServletResponse response) throws JRException, IOException, SQLException {
		InputStream reportStrem = this.getClass().getResourceAsStream("/reports/Products.jasper");
		Map<String, Object> params = new HashMap<>();
		
		params.put("total", "123");
		
		//Faz a cptura do item
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStrem);
		//Preenche com os dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, datasource.getConnection());
		//Altera a resposta
		response.setContentType("application/pdf");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=products_son.pdf"); //inline apresenta na body, attachment faz o download
		//Finaliza com outputStream
		OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
	}
}
