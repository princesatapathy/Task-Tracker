package com.amcvillan.Task.Tracker.domain.dto;

public record ErrorResponse(
        int  status,
        String message,
        String details
) {

}
