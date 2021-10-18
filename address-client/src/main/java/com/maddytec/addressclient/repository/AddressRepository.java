package com.maddytec.addressclient.repository;

import com.maddytec.addressclient.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> { }