package com.service.stats.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatisticResponse {
    String data;
    int count;
}