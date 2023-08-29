package com.service.core.entity;

import com.service.core.util.enums.Scale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temperature {
    private Scale scale;
    private int degrees;
}
