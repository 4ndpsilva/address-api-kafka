package com.maddytec.addressclient.service;

import com.maddytec.addressclient.domain.Address;
import com.maddytec.addressclient.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public Address save(final Address address){
        repository.save(address);
        return address;
    }

    public List<Address> list(){
        return repository.findAll();
    }
}