package com.example.task.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class GenericResponse implements Serializable  {

    private String message;

    private Boolean success;

    private Object data;

    public static GenericResponse successMsg() {
        return GenericResponse
                .builder()
                .message("success")
                .success(true)
                .build();
    }

    public static GenericResponse successMsg(Object data) {
        return GenericResponse
                .builder()
                .message("success")
                .success(true)
                .data(data)
                .build();
    }


    public static GenericResponse successMessageWithoutData() {
        return successMsg(null);
    }

    public static GenericResponse errorMsg(Object data) {
        return GenericResponse
                .builder()
                .message("error")
                .success(false)
                .data(data)
                .build();
    }


}
