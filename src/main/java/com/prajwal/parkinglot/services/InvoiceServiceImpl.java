package com.prajwal.parkinglot.services;

import com.prajwal.parkinglot.exception.InvalidGateException;
import com.prajwal.parkinglot.exception.TicketNotFoundException;
import com.prajwal.parkinglot.models.AdditionalService;
import com.prajwal.parkinglot.models.Gate;
import com.prajwal.parkinglot.models.Invoice;
import com.prajwal.parkinglot.models.Ticket;
import com.prajwal.parkinglot.repository.GateRepository;
import com.prajwal.parkinglot.repository.InvoiceRepository;
import com.prajwal.parkinglot.repository.TicketRepository;
import com.prajwal.parkinglot.strategies.pricing.PricingStrategy;
import com.prajwal.parkinglot.strategies.pricing.PricingStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class InvoiceServiceImpl implements InvoiceService{

    private TicketRepository ticketRepository;
    private PricingStrategyFactory pricingStrategyFactory;

    private GateRepository gateRepository;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketRepository ticketRepository, PricingStrategyFactory pricingStrategyFactory, GateRepository gateRepository, InvoiceRepository invoiceRepository) {
        this.ticketRepository = ticketRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.gateRepository = gateRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException {
        Optional<Ticket> ticketOptional = ticketRepository.getTicketById(ticketId);

        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }
        Ticket ticket = ticketOptional.get();
        Optional<Gate> optionalGate = this.gateRepository.findById(gateId);
        if(optionalGate.isEmpty()){
            throw new InvalidGateException("Invalid gate id");
        }
        Date exitTime = new Date();
        PricingStrategy pricingStrategy = pricingStrategyFactory.getPricingStrategy(exitTime);
        double costIncurredForParking = pricingStrategy.calculateAmount(ticket.getEntryTime(), exitTime, ticket.getVehicle().getVehicleType());
        double costIncurredForAdditionalServices = 0;
        for(AdditionalService additionalService: ticket.getAdditionalServices()){
            costIncurredForAdditionalServices += additionalService.getCost();
        }
        double totalCost = costIncurredForParking + costIncurredForAdditionalServices;
        Gate gate = optionalGate.get();
        Invoice invoice = new Invoice();
        invoice.setTicket(ticket);
        invoice.setGate(gate);
        invoice.setExitTime(exitTime);
        invoice.setAmount(totalCost);
        invoice = this.invoiceRepository.save(invoice);
        return invoice;
    }
}
