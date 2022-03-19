package com.datonpay.transaction.convert;

import com.datonpay.transaction.api.dto.PayOrderDTO;
import com.datonpay.transaction.entity.PayOrder;
import com.datonpay.transaction.vo.OrderConfirmVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayOrderConvert {

    PayOrderConvert INSTANCE = Mappers.getMapper(PayOrderConvert.class);

    PayOrderDTO entity2dto(PayOrder entity);

    PayOrder dto2entity(PayOrderDTO dto);

    //忽略totalAmount，不做映射
    @Mapping(target = "totalAmount", ignore = true)
    PayOrderDTO vo2dto(OrderConfirmVO vo);

}
