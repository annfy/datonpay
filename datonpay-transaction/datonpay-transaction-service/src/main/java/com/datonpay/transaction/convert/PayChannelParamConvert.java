package com.datonpay.transaction.convert;

import com.datonpay.transaction.api.dto.PayChannelParamDTO;
import com.datonpay.transaction.entity.PayChannelParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PayChannelParamConvert {

    PayChannelParamConvert INSTANCE= Mappers.getMapper(PayChannelParamConvert.class);

    PayChannelParamDTO entity2dto(PayChannelParam entity);

    PayChannelParam dto2entity(PayChannelParamDTO dto);

    List<PayChannelParamDTO> listentity2listdto(List<PayChannelParam> PlatformChannel);

    List<PayChannelParam> listdto2listentity(List<PayChannelParamDTO> PlatformChannelDTO);
}
