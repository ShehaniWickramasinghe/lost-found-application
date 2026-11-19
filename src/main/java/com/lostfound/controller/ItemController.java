
package com.lostfound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lostfound.dto.ItemRequest;
import com.lostfound.dto.ItemResponse;
import com.lostfound.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(
            @Valid @RequestBody ItemRequest request) {

        ItemResponse response = itemService.createItem(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {

        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(
            @PathVariable Long id) {

        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemRequest request) {

        ItemResponse response = itemService.updateItem(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id) {

        itemService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponse>> searchItems(
            @RequestParam String title) {

        return ResponseEntity.ok(
                itemService.searchItemsByTitle(title));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ItemResponse>> getItemsByStatus(
            @PathVariable com.lostfound.enums.ItemStatus status) {

        return ResponseEntity.ok(
                itemService.getItemsByStatus(status));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ItemResponse>> getItemsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                itemService.getItemsByCategory(category));
    }

}
