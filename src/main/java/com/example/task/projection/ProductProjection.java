package com.example.task.projection;

import java.time.LocalDate;

public interface ProductProjection {

    Long getId();

    String getName();

    Integer getQuantity();

    String getCategoryName();

    String getStatus();

    LocalDate getExpireDate();

}
