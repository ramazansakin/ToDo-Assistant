package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.Address;
import com.sakinramazan.userservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/all")
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address get(@PathVariable Integer id) {
        return addressService.getOne(id);
    }

    @PostMapping("/create")
    public Address save(@RequestBody Address address) {
        return addressService.addOne(address);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address) {
        return addressService.updateOne(address);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Integer id) {
        return addressService.deleteOne(id);
    }
}
