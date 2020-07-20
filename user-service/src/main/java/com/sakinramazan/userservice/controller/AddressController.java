package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.Address;
import com.sakinramazan.userservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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
    public Address get(@PathVariable @Range(min = 1, max = 100) Integer id) {
        return addressService.getOne(id);
    }

    @PostMapping("/create")
    public Address save(@RequestBody @Valid Address address) {
        return addressService.addOne(address);
    }

    @PutMapping("/update")
    public Address update(@RequestBody @Valid Address address) {
        return addressService.updateOne(address);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam @Range(min = 1, max = 100) Integer id) {
        return addressService.deleteOne(id);
    }
}
