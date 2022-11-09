package com.acme_fresh.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMsg {

    private LocalDateTime timeStamp;

    private Integer status;

    private String error;

    private String message;

}