package fja.edu.br.legal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fja.edu.br.legal.calculo.CalculadoraSimples;


@RestController
@RequestMapping("/calculo")
public class CalculoController {

	private CalculadoraSimples calc = new CalculadoraSimples();
	
	@RequestMapping(value = "/sum/{val1}/{val2}", method=RequestMethod.GET)
	public Double soma(@PathVariable("val1") String val1, @PathVariable("val2") String val2) {
		
		return calc.soma(Double.parseDouble(val1), Double.parseDouble(val2));
		
	}
	
	@GetMapping("/outrasoma/{nr1}/{nr2}")
	public Double outrasoma(@PathVariable("nr1") Double nr1, @PathVariable("nr2") Double nr2) {
		return calc.soma(nr1, nr2);
	}
	
	
	@GetMapping("/")
	public String teste() {
		return "Teste";
	}
	
}
