
package com.lostfound.dto;

import java.time.LocalDateTime;

import com.lostfound.enums.RequestStatus;

public class RequestResponse {

    private Long id;
    private String message;
    private RequestStatus status;
    private Long itemId;
    private String itemTitle;
    private Long requestedById;
    private String requestedByName;
    private LocalDateTime createdAt;

    public RequestResponse() {
    }

    public RequestResponse(Long id, String message, RequestStatus status,
            Long itemId, String itemTitle, Long requestedById,
            String requestedByName, LocalDateTime createdAt) {

        this.id = id;
        this.message = message;
        this.status = status;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.requestedById = requestedById;
        this.requestedByName = requestedByName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Long getRequestedById() {
        return requestedById;
    }

    public void setRequestedById(Long requestedById) {
        this.requestedById = requestedById;
    }

    public String getRequestedByName() {
        return requestedByName;
    }

    public void setRequestedByName(String requestedByName) {
        this.requestedByName = requestedByName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
