package com.example.multitenant.mysql.controller;

import com.example.multitenant.mysql.entity.City;
import com.example.multitenant.mysql.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController("city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/add")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-TenantId", value = "Client ID", dataType = "string", paramType = "header")})
    public ResponseEntity<String> save(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-TenantId", value = "Client ID", dataType = "string", paramType = "header")})
    public ResponseEntity<List<City>> getAll() throws SQLException {
        List<City> cities = cityService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping(value = "/by-id")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-TenantId", value = "Client ID", dataType = "string", paramType = "header")})
    public ResponseEntity<City> getById(@RequestParam(value = "id") String id) {
        City city = cityService.get(Long.parseLong(id));
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping(value = "/by-name")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-TenantId", value = "Client ID", dataType = "string", paramType = "header")})
    public ResponseEntity<City> getByName(@RequestParam(value = "name") String name) {
        City city = cityService.getByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping(value = "/by-name")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-TenantId", value = "Client ID", dataType = "string", paramType = "header")})
    public ResponseEntity<City> deleteByName(@RequestParam(value = "name") String name) {
        cityService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
