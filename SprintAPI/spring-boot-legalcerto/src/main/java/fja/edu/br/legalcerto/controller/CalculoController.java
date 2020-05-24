package fja.edu.br.legalcerto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fja.edu.br.legalcerto.calculadora.Calculadora;


@RestController
@RequestMapping("/calculo")
public class CalculoController {
	
	private Calculadora calcula = new Calculadora();
	
	@RequestMapping(value = "/soma/{numero1}/{numero2}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numero1") String numero1, @PathVariable("numero2") String numero2) {
		
		return calcula.soma( Double.parseDouble(numero1), Double.parseDouble(numero1));
		
	}
	
	@RequestMapping(value = "/subtracao/{numero1}/{numero2}", method=RequestMethod.GET)
	public Double subtraction(@PathVariable("numero1") String numero1, @PathVariable("numero2") String numero2) {
		
		return calcula.subtracao(Double.parseDouble(numero1), Double.parseDouble(numero1));
		
	}
	
	@GetMapping("/raiz/{nr}")
	public Double raiz(@PathVariable("nr") Double nr) {
		return calcula.raiz(nr);
	}
	
	@GetMapping("/outrasoma/{nr1}/{nr2}")
	public Double outrasoma(@PathVariable("nr1") Double nr1, @PathVariable("nr2") Double nr2) {
		return calcula.soma(nr1, nr2);
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "Teste";
	}

}
