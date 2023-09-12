package com.service.core.dto.response;

import com.service.core.util.enums.Scale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemperatureResponse {
    private Scale scale;
    private int degrees;
}
