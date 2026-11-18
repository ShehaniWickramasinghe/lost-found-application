
package com.lostfound.dto;

import java.time.LocalDateTime;

import com.lostfound.enums.ItemStatus;

public class ItemResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    private LocalDateTime dateLostOrFound;
    private ItemStatus status;
    private Long reportedById;
    private String reportedByName;
    private LocalDateTime createdAt;

    public ItemResponse() {
    }

    public ItemResponse(Long id, String title, String description,
            String category, String location,
            LocalDateTime dateLostOrFound, ItemStatus status,
            Long reportedById, String reportedByName,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.dateLostOrFound = dateLostOrFound;
        this.status = status;
        this.reportedById = reportedById;
        this.reportedByName = reportedByName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReportedByName() {
        return reportedByName;
    }

    public void setReportedByName(String reportedByName) {
        this.reportedByName = reportedByName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
