package model.user;
import lombok.Builder;

import java.sql.Date;

@Builder
public record UserOrderDetail(
        String userName,
        Date orderedDate,
        String productName
) { }
