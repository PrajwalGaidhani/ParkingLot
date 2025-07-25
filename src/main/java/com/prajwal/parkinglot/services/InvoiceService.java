package com.prajwal.parkinglot.services;

import com.prajwal.parkinglot.exception.InvalidGateException;
import com.prajwal.parkinglot.exception.TicketNotFoundException;
import com.prajwal.parkinglot.models.Invoice;

public interface InvoiceService {
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException;
}