package com.prajwal.parkinglot;

public class InvoiceController {

        private InvoiceService invoiceService;

        public InvoiceController(InvoiceService invoiceService) {
            this.invoiceService = invoiceService;
        }

        public GenerateInvoiceResponseDto createInvoice(GenerateInvoiceRequestDto requestDto){
            return null;
        }
}