
package com.lostfound.dto;

import com.lostfound.enums.RequestStatus;

import jakarta.validation.constraints.NotNull;

public class RequestStatusDTO {

    @NotNull(message = "Request status is required")
    private RequestStatus status;

    public RequestStatusDTO() {
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
