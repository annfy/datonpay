package com.datonpay.merchant.convert;

import com.datonpay.merchant.api.dto.AppDTO;
import com.datonpay.merchant.entity.App;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Mapper
public interface AppCovert {

    AppCovert INSTANCE = Mappers.getMapper(AppCovert.class);

    AppDTO entity2dto(App entity);

    App dto2entity(AppDTO dto);

    List<AppDTO> listentity2dto(List<App> app);

}
