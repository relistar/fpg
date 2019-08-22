package com.factories;

import com.domain.ConverterType;
import com.products.MinerResultOutput;

public class MinerResultConverterFactory {

    public MinerResultOutput create(ConverterType type) {
        return type.getInstance();
    }
}
