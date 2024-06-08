package br.com.borsoitech.pdv.controller.mapper;

import br.com.borsoitech.pdv.controller.dto.ItemVendaDTO;
import br.com.borsoitech.pdv.controller.dto.PagamentoDTO;
import br.com.borsoitech.pdv.entity.ItemVenda;
import br.com.borsoitech.pdv.entity.Pagamento;
import br.com.borsoitech.pdv.entity.enums.TipoPagamento;
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
		VendaDTO dto = mapper.map(entity, VendaDTO.class);
		dto.setValorTotal(entity.getValorTotal());
		dto.setVendaQuitada(entity.vendaQuitada());
		dto.setValorParcialPago(entity.getValorParcialPago());
		dto.setValorTotalPago(entity.getValorTotalPago());
		if (!entity.getItens().isEmpty()) {
			for (ItemVenda item : entity.getItens()) {
				ItemVendaDTO vendaItem = mapper.map(item, ItemVendaDTO.class);
				vendaItem.setValorTotalItem(item.getValorTotalItem());
				dto.addItem(vendaItem);
			}
		}
		if (!entity.getPagamentos().isEmpty()) {
			for (Pagamento pagamento : entity.getPagamentos()) {
				PagamentoDTO pagamentoDTO = mapper.map(pagamento, PagamentoDTO.class);
				pagamentoDTO.setTipoPag(pagamento.getTipoPagamento().getCodigo());
				dto.addPagamento(pagamentoDTO);
			}
		}
		return dto;
	}

	public static Venda toEntity(VendaDTO dto) {
		Venda entity = mapper.map(dto, Venda.class);
		if (!dto.getItens().isEmpty()) {
			for (ItemVendaDTO item : dto.getItens()) {
				entity.addItem(mapper.map(item, ItemVenda.class));
			}
		}
		if (!dto.getPagamentos().isEmpty()) {
			for (PagamentoDTO pagamento : dto.getPagamentos()) {
				Pagamento pagamentoEntity = mapper.map(pagamento, Pagamento.class);
				pagamentoEntity.setTipoPagamento(TipoPagamento.paraEnum(pagamento.getTipoPag()));
				entity.addPagamento(pagamentoEntity);
			}
		}
		return entity;
	}
}
