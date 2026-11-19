
package com.lostfound.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lostfound.dto.RequestDTO;
import com.lostfound.dto.RequestResponse;
import com.lostfound.entity.Item;
import com.lostfound.entity.Request;
import com.lostfound.entity.User;
import com.lostfound.enums.RequestStatus;
import com.lostfound.repository.ItemRepository;
import com.lostfound.repository.RequestRepository;
import com.lostfound.repository.UserRepository;

@Service
@Transactional
public class RequestService {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestService.class);

    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public RequestService(RequestRepository requestRepository,
            ItemRepository itemRepository,
            UserRepository userRepository) {

        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public RequestResponse createRequest(RequestDTO requestDTO) {

        logger.info("Creating a new request for item ID: {}",
                requestDTO.getItemId());

        Item item = itemRepository.findById(requestDTO.getItemId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Item not found"));

        User user = userRepository.findById(requestDTO.getRequestedById())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Request request = new Request();

        request.setMessage(requestDTO.getMessage());
        request.setItem(item);
        request.setRequestedBy(user);
        request.setStatus(RequestStatus.PENDING);

        Request savedRequest = requestRepository.save(request);

        logger.info("Request created successfully with ID: {}",
                savedRequest.getId());

        return convertToResponse(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<RequestResponse> getAllRequests() {

        logger.info("Fetching all requests");

        return requestRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public RequestResponse getRequestById(Long id) {

        logger.info("Fetching request with ID: {}", id);

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Request not found with ID: " + id));

        return convertToResponse(request);
    }

    public RequestResponse updateRequest(Long id, RequestDTO requestDTO) {

        logger.info("Updating request with ID: {}", id);

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Request not found with ID: " + id));

        Item item = itemRepository.findById(requestDTO.getItemId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Item not found"));

        User user = userRepository.findById(requestDTO.getRequestedById())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        request.setMessage(requestDTO.getMessage());
        request.setItem(item);
        request.setRequestedBy(user);

        Request updatedRequest = requestRepository.save(request);

        logger.info("Request updated successfully with ID: {}", id);

        return convertToResponse(updatedRequest);
    }

    public void deleteRequest(Long id) {

        logger.info("Deleting request with ID: {}", id);

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Request not found with ID: " + id));

        requestRepository.delete(request);

        logger.info("Request deleted successfully with ID: {}", id);
    }

    private RequestResponse convertToResponse(Request request) {

        return new RequestResponse(
                request.getId(),
                request.getMessage(),
                request.getStatus(),
                request.getItem().getId(),
                request.getItem().getTitle(),
                request.getRequestedBy().getId(),
                request.getRequestedBy().getFullName(),
                request.getCreatedAt()
        );
    }

    public RequestResponse updateRequestStatus(
            Long id, RequestStatus newStatus) {

        logger.info("Updating status of request ID: {}", id);

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Request not found with ID: " + id));

        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Request status is required");
        }

        if (newStatus == RequestStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Request status can only be APPROVED or REJECTED");
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Only pending requests can be approved or rejected");
        }

        request.setStatus(newStatus);

        Request updatedRequest = requestRepository.save(request);

        logger.info("Request ID: {} status changed to: {}",
                id, newStatus);

        return convertToResponse(updatedRequest);
    }

}
