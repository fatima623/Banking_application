package com.javaguide.Banking.Application.Exception;

import java.time.LocalDateTime;

public record ErrorDetail (LocalDateTime timeStamp,
                           String message,

                           String detail,
  String errorCode) {}

