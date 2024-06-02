package br.com.borsoitech.pdv.controller.mapper;

import br.com.borsoitech.pdv.controller.dto.save.ItemVendaSaveDTO;
import br.com.borsoitech.pdv.controller.dto.update.VendaUpdateDTO;
import br.com.borsoitech.pdv.entity.ItemVenda;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.borsoitech.pdv.controller.dto.save.VendaSaveDTO;
import br.com.borsoitech.pdv.entity.Venda;

@Component
public class VendaMapper {
	
	private static ModelMapper mapper;
	
	public VendaMapper(ModelMapper mapper) {
		VendaMapper.mapper = mapper;
	}

	public static VendaSaveDTO toSaveDTO(Venda entity) {
		VendaSaveDTO dto = mapper.map(entity, VendaSaveDTO.class);
		dto.setValorTotal(entity.getValorTotal());
		for (ItemVenda item : entity.getItens()) {
			dto.getItens().add(new ItemVendaSaveDTO(item.getValorTotalItem(), item.getDescricao(), item.getQuantidade(), item.getValorUnit(), item.getDesconto(), item.getProduto().getId()));
		}
		return dto;
	}

	public static VendaUpdateDTO toUpdateDTO(Venda entity) {
		return mapper.map(entity, VendaUpdateDTO.class);
	}

	public static Venda toEntity(VendaSaveDTO dto) {
		Venda entity = mapper.map(dto, Venda.class);
		for (ItemVendaSaveDTO item : dto.getItens()) {
			entity.addItem(mapper.map(item, ItemVenda.class));
		}
		return entity;
	}
	
	public static Venda toEntity(VendaUpdateDTO dto) {
		return mapper.map(dto, Venda.class);
	}
}
