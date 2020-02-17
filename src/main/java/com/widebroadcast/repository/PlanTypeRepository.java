package com.widebroadcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widebroadcast.entity.PlanType;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanType, Integer> {

}
