
package com.lostfound.service;

import java.util.List;

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

        return convertToResponse(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<RequestResponse> getAllRequests() {

        return requestRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public RequestResponse getRequestById(Long id) {

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Request not found with ID: " + id));

        return convertToResponse(request);
    }

    public RequestResponse updateRequest(Long id, RequestDTO requestDTO) {

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Request not found with ID: " + id));

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

        return convertToResponse(updatedRequest);
    }

    public void deleteRequest(Long id) {

        Request request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Request not found with ID: " + id));

        requestRepository.delete(request);
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
}
