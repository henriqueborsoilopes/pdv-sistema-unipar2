package br.com.borsoitech.pdv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vendas", description = "Contém todos os recursos relacionados com venda, como: .")
@RestController
@RequestMapping("/vendas")
public class VendaController {

}
