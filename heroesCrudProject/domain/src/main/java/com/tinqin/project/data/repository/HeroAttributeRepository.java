package com.tinqin.project.data.repository;

import com.tinqin.project.data.entity.HeroAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroAttributeRepository extends JpaRepository<HeroAttribute,Long> {



}
