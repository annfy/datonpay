package com.datonpay.merchant.convert;

import com.datonpay.merchant.api.dto.StaffDTO;
import com.datonpay.merchant.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StaffConvert {

    StaffConvert INSTANCE = Mappers.getMapper(StaffConvert.class);

    StaffDTO entity2dto(Staff entity);

    Staff dto2entity(StaffDTO dto);

    List<StaffDTO> listentity2dto(List<Staff> staff);

}
