package model.user;

import lombok.Builder;

@Builder
public record UserOrder(
        Integer userId,
        Integer orderId,
        String orderName,
        Integer productId
) { }
