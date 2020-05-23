package fja.edu.br.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fja.edu.br.calculo.CalculadoraSimples;


@RestController
@RequestMapping("/calculo")
public class CalculoController {

	private CalculadoraSimples calc = new CalculadoraSimples();
	
	@RequestMapping(value = "/soma/{num1}/{num2}", method = RequestMethod.GET)
	public Double soma(@PathVariable("num1") String num1, @PathVariable("num2")String num2) {
		
		return calc.soma(Double.parseDouble(num1), Double.parseDouble(num2));
		
	}
	
	@GetMapping("/sum/{val1}/{val2}")
	public Double sum(@PathVariable("val1")Double val1, @PathVariable("val2")Double val2) {
		return calc.soma(val1, val2);
	}
	
	
	@GetMapping("/")
	public String teste() {
		return "Teste";
	}
	
}
