
package com.lostfound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lostfound.entity.Item;
import com.lostfound.enums.ItemStatus;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByStatus(ItemStatus status);

    List<Item> findByCategory(String category);

    List<Item> findByTitleContainingIgnoreCase(String title);
}
