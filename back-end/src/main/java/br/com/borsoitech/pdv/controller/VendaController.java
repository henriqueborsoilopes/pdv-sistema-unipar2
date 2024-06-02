package br.com.borsoitech.pdv.controller;

import java.net.URI;

import javax.validation.Valid;

import br.com.borsoitech.pdv.controller.dto.update.VendaUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nimbusds.oauth2.sdk.ErrorResponse;

import br.com.borsoitech.pdv.controller.dto.save.VendaSaveDTO;
import br.com.borsoitech.pdv.controller.mapper.VendaMapper;
import br.com.borsoitech.pdv.entity.Venda;
import br.com.borsoitech.pdv.service.VendaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Venda", description = "API para gerenciamento de venda")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaServico vendaServico;

    public VendaController(VendaServico vendaServico) {
        this.vendaServico = vendaServico;
    }

    @Operation(summary = "Salvar venda", description = "Salvar uma nova venda")
    @ApiResponse(responseCode = "201", description = "Venda salva com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Void.class))))
    @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_OPERATOR')")
    @PostMapping
    public ResponseEntity<Void> insert(
    	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Venda a ser criada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaSaveDTO.class)))
        @Valid @RequestBody VendaSaveDTO dto) {
        Venda venda = vendaServico.inserir(VendaMapper.toEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza carrinho", description = "Atualiza o carrinho")
    @ApiResponse(responseCode = "200", description = "carrinho atualiza com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VendaSaveDTO.class))))
    @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_OPERATOR')")
    @PutMapping
    public ResponseEntity<VendaSaveDTO> updateCarrinho(
    		@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Venda a ser atualizada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaSaveDTO.class)))
    		@RequestBody VendaSaveDTO dto) {
        dto = VendaMapper.toSaveDTO(vendaServico.updateCarrinho(VendaMapper.toEntity(dto)));
        return ResponseEntity.ok().body(dto);
    }
}
