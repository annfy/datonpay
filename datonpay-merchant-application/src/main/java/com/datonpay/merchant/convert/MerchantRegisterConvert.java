package com.datonpay.merchant.convert;

import com.datonpay.merchant.api.dto.MerchantDTO;
import com.datonpay.merchant.vo.MerchantRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 将商户注册vo和dto进行转换
 * Created by Administrator.
 */
@Mapper
public interface MerchantRegisterConvert {

    MerchantRegisterConvert INSTANCE = Mappers.getMapper(MerchantRegisterConvert.class);

    //将dto转成vo
    MerchantRegisterVO dto2vo(MerchantDTO merchantDTO);
    //将vo转成dto
    MerchantDTO vo2dto(MerchantRegisterVO merchantRegisterVO);

}
