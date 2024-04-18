package com.baeldung.mapstruct.enumtostring.mapper;

import com.baeldung.mapstruct.enumtostring.model.ExternalOrder;
import com.baeldung.mapstruct.enumtostring.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The order mapper.
 */
@Mapper(uses = {OrderStatusMapper.class, OrderTypeMapper.class})
public interface OrderMapper {
    /**
     * The order mapper.
     */
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Maps order to external order.
     *
     * @param source the source order
     * @return the external order
     */
    @Mapping(target = "orderType", source = "orderType", qualifiedByName = "orderTypeToString")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "orderStatusToString")
    ExternalOrder toExternalOrder(Order source);
}
