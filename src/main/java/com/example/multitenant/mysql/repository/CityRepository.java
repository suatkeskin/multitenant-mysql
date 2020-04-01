package com.example.multitenant.mysql.repository;

import com.example.multitenant.mysql.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);

    void deleteByName(String name);
}
