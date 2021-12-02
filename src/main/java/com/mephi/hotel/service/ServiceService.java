package com.mephi.hotel.service;

import com.mephi.hotel.model.Service;
import com.mephi.hotel.repository.ServiceRepository;

import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Stream<Service> getAllService() {
        return serviceRepository.findAll().stream();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).get();
    }
}
