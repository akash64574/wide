package com.widebroadcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widebroadcast.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

}
