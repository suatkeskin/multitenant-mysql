package com.example.multitenant.mysql.service;

import com.example.multitenant.mysql.entity.City;
import com.example.multitenant.mysql.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City get(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new IllegalStateException("Resource not found!"));
    }

    public City getByName(String name) {
        return cityRepository.findByName(name);
    }

    public void delete(String name) {
        cityRepository.deleteByName(name);
    }
}
