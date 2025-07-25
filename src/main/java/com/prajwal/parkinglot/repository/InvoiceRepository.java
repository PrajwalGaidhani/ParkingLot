package com.prajwal.parkinglot.repository;

import com.prajwal.parkinglot.models.Invoice;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
}