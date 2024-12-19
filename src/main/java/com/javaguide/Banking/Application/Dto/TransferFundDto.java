package com.javaguide.Banking.Application.Dto;

public record TransferFundDto(Long fromAccountId, Long toAccountId, Double amount) {
}
