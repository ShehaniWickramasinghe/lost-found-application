
package com.lostfound.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lostfound.dto.ItemRequest;
import com.lostfound.dto.ItemResponse;
import com.lostfound.entity.Item;
import com.lostfound.entity.User;
import com.lostfound.repository.ItemRepository;
import com.lostfound.repository.UserRepository;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository,
            UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    // Create a new item
    public ItemResponse createItem(ItemRequest request) {

        User user = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Reporter user not found"));

        Item item = new Item();

        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setCategory(request.getCategory());
        item.setLocation(request.getLocation());
        item.setDateLostOrFound(request.getDateLostOrFound());
        item.setStatus(request.getStatus());
        item.setReportedBy(user);

        Item savedItem = itemRepository.save(item);

        return convertToResponse(savedItem);
    }

    // Get all items
    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {

        return itemRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get item by ID
    @Transactional(readOnly = true)
    public ItemResponse getItemById(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item not found with ID: " + id));

        return convertToResponse(item);
    }

    // Update an existing item
    public ItemResponse updateItem(Long id, ItemRequest request) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item not found with ID: " + id));

        User user = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Reporter user not found"));

        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setCategory(request.getCategory());
        item.setLocation(request.getLocation());
        item.setDateLostOrFound(request.getDateLostOrFound());
        item.setStatus(request.getStatus());
        item.setReportedBy(user);

        Item updatedItem = itemRepository.save(item);

        return convertToResponse(updatedItem);
    }

    // Delete an item
    public void deleteItem(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item not found with ID: " + id));

        itemRepository.delete(item);
    }

    // Convert Entity into Response DTO
    private ItemResponse convertToResponse(Item item) {

        return new ItemResponse(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getCategory(),
                item.getLocation(),
                item.getDateLostOrFound(),
                item.getStatus(),
                item.getReportedBy().getId(),
                item.getReportedBy().getFullName(),
                item.getCreatedAt()
        );
    }
}
