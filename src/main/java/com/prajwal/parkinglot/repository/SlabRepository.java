package com.prajwal.parkinglot.repository;

import com.prajwal.parkinglot.models.Slab;
import com.prajwal.parkinglot.models.VehicleType;

import java.util.List;

public interface SlabRepository {
    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType);

    public Slab save(Slab slab);
}