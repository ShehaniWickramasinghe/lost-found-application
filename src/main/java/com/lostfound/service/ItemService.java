
package com.lostfound.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lostfound.dto.ItemRequest;
import com.lostfound.dto.ItemResponse;
import com.lostfound.entity.Item;
import com.lostfound.entity.User;
import com.lostfound.exception.ResourceNotFoundException;
import com.lostfound.repository.ItemRepository;
import com.lostfound.repository.UserRepository;

@Service
@Transactional
public class ItemService {

    private static final Logger logger =
            LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository,
            UserRepository userRepository) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ItemResponse createItem(ItemRequest request) {

        logger.info("Creating a new item: {}", request.getTitle());

        User user = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new ResourceNotFoundException(
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

        logger.info("Item created successfully with ID: {}", savedItem.getId());

        return convertToResponse(savedItem);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {

        logger.info("Fetching all items");

        return itemRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemResponse getItemById(Long id) {

        logger.info("Fetching item with ID: {}", id);

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Item not found with ID: " + id));

        return convertToResponse(item);
    }

    public ItemResponse updateItem(Long id, ItemRequest request) {

        logger.info("Updating item with ID: {}", id);

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                      "Item not found with ID: " + id));

        User user = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new ResourceNotFoundException(
    "Reporter user not found"));

        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setCategory(request.getCategory());
        item.setLocation(request.getLocation());
        item.setDateLostOrFound(request.getDateLostOrFound());
        item.setStatus(request.getStatus());
        item.setReportedBy(user);

        Item updatedItem = itemRepository.save(item);

        logger.info("Item updated successfully with ID: {}", id);

        return convertToResponse(updatedItem);
    }

    public void deleteItem(Long id) {

        logger.info("Deleting item with ID: {}", id);

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                         "Item not found with ID: " + id));

        itemRepository.delete(item);

        logger.info("Item deleted successfully with ID: {}", id);
    }

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

    @Transactional(readOnly = true)
    public List<ItemResponse> searchItemsByTitle(String title) {

        logger.info("Searching items with title: {}", title);

        return itemRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getItemsByStatus(
            com.lostfound.enums.ItemStatus status) {

        logger.info("Filtering items by status: {}", status);

        return itemRepository.findByStatus(status)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getItemsByCategory(String category) {

        logger.info("Filtering items by category: {}", category);

        return itemRepository.findByCategory(category)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

}
