package br.com.borsoitech.pdv.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.borsoitech.pdv.controller.dto.VendaDTO;
import br.com.borsoitech.pdv.entity.Venda;

@Component
public class VendaMapper {
	
	private static ModelMapper mapper;
	
	public VendaMapper(ModelMapper mapper) {
		VendaMapper.mapper = mapper;
	}

	public static VendaDTO toDTO(Venda entity) {
		return mapper.map(entity, VendaDTO.class);
	}
	
	public static Venda toEntity(VendaDTO dto) {
		return mapper.map(dto, Venda.class);
	}
}
