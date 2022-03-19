package com.datonpay.user.convert;

import com.datonpay.user.api.dto.tenant.TenantDTO;
import com.datonpay.user.entity.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    TenantDTO entity2dto(Tenant entity);

    Tenant dto2entity(TenantDTO dto);
}
