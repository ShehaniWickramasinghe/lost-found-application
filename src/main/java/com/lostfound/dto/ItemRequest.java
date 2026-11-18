
package com.lostfound.dto;

import java.time.LocalDateTime;

import com.lostfound.enums.ItemStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    private String location;

    @NotNull(message = "Date is required")
    private LocalDateTime dateLostOrFound;

    @NotNull(message = "Item status is required")
    private ItemStatus status;

    @NotNull(message = "Reporter user ID is required")
    private Long reportedById;

    public ItemRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateLostOrFound() {
        return dateLostOrFound;
    }

    public void setDateLostOrFound(LocalDateTime dateLostOrFound) {
        this.dateLostOrFound = dateLostOrFound;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Long getReportedById() {
        return reportedById;
    }

    public void setReportedById(Long reportedById) {
        this.reportedById = reportedById;
    }
}
