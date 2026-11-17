
package com.lostfound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lostfound.entity.Request;
import com.lostfound.enums.RequestStatus;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByStatus(RequestStatus status);

    List<Request> findByRequestedById(Long userId);

    List<Request> findByItemId(Long itemId);
}

