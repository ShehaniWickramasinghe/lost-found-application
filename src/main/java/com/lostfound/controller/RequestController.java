
package com.lostfound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lostfound.dto.RequestDTO;
import com.lostfound.dto.RequestResponse;
import com.lostfound.dto.RequestStatusDTO;
import com.lostfound.enums.RequestStatus;
import com.lostfound.service.RequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Create a new request
    @PostMapping
    public ResponseEntity<RequestResponse> createRequest(
            @Valid @RequestBody RequestDTO requestDTO) {

        RequestResponse response =
                requestService.createRequest(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all requests
    @GetMapping
    public ResponseEntity<List<RequestResponse>> getAllRequests() {

        return ResponseEntity.ok(requestService.getAllRequests());
    }

    // Get request by ID
    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    // Update request
    @PutMapping("/{id}")
    public ResponseEntity<RequestResponse> updateRequest(
            @PathVariable Long id,
            @Valid @RequestBody RequestDTO requestDTO) {

        RequestResponse response =
                requestService.updateRequest(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    // Delete request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long id) {

        requestService.deleteRequest(id);

        return ResponseEntity.noContent().build();
    }

    // Approve or reject a request
    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponse> updateRequestStatus(
            @PathVariable Long id,
            @Valid @RequestBody RequestStatusDTO statusDTO) {

        RequestResponse response =
                requestService.updateRequestStatus(
                        id, statusDTO.getStatus());

        return ResponseEntity.ok(response);
    }

    // Get requests by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<RequestResponse>> getRequestsByStatus(
            @PathVariable RequestStatus status) {

        return ResponseEntity.ok(
                requestService.getRequestsByStatus(status));
    }

    // Get requests by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RequestResponse>> getRequestsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                requestService.getRequestsByUser(userId));
    }

    // Get requests by item ID
    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<RequestResponse>> getRequestsByItem(
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                requestService.getRequestsByItem(itemId));
    }
}
