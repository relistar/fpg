package com.executors;

import com.domain.Arguments;

public interface Runner {
    void run(String[] args);

    Arguments parseArguments(String[] args);
}
