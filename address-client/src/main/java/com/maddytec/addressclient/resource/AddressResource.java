package com.maddytec.addressclient.resource;

import com.maddytec.addressclient.domain.Address;
import com.maddytec.addressclient.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/addresses")
@RequiredArgsConstructor
public class AddressResource {
    private final AddressService service;

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody final Address address){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(address));
    }

    @GetMapping
    public ResponseEntity<List<Address>> list(){
        return ResponseEntity.ok(service.list());
    }
}