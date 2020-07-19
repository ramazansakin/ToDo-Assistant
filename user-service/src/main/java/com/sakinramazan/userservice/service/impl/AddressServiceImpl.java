package com.sakinramazan.userservice.service.impl;

import com.sakinramazan.userservice.entity.Address;
import com.sakinramazan.userservice.repository.AddressRepository;
import com.sakinramazan.userservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getOne(Integer id) {
        if (id != null)
            return addressRepository.getOne(id);
        return null;
    }

    @Override
    public Address addOne(Address address) {
        if (address != null) {
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public Address updateOne(Address address) {
        if (address != null && address.getId() != null) {
            Address one = getOne(address.getId());
            if (one != null)
                return addressRepository.save(one);
        }
        return null;
    }

    @Override
    public boolean deleteOne(Integer id) {
        if (id != null) {
            Address one = getOne(id);
            if (one != null) {
                addressRepository.save(one);
                return true;
            }
        }
        return false;
    }
}
