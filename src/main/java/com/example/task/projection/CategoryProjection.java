package com.example.task.projection;

import java.io.Serializable;

public interface CategoryProjection   {
    Long getId();

    String getName();

    String getStatus();

    Long getCount();
}
