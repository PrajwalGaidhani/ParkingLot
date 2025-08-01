PARKING LOT SYSTEM - OVERVIEW

This project implements a modular and extensible Parking Lot Management System using Java (Spring Boot or Java SE assumed). The architecture follows clean layering and separation of concerns.

1. MODELS (package: models)
    - Represents core domain entities such as:
        - ParkingLot, ParkingSpot, Vehicle, Ticket, Invoice
        - Enum-like classes: VehicleType, FloorStatus, GateType, etc.
        - BaseModel serves as the common base class for shared fields (e.g., ID, timestamps)

2. CONTROLLERS (package: controller)
    - Entry points for client/API calls.
    - Handles incoming requests and returns appropriate DTO responses.
    - Includes:
        - InvoiceController
        - ParkingLotController
        - TicketController

3. DTOs (package: dto)
    - Data Transfer Objects used for request/response mapping.
    - Ensures decoupling between internal models and exposed API schema.
    - E.g., GenerateTicketRequestDto, GenerateInvoiceResponseDto, etc.

4. REPOSITORY (package: repository)
    - Interfaces (I*) and Implementations (*Impl) for data persistence.
    - Abstraction over storage (DB, in-memory, etc.).
    - Example: ParkingLotRepository, TicketRepository

5. SERVICES (package: services)
    - Contains business logic for:
        - Ticket generation
        - Invoice calculation
        - Parking spot allocation
    - Interface-Implementation pairs: e.g., InvoiceService → InvoiceServiceImpl

6. STRATEGIES (package: strategies)
    - Pluggable algorithm patterns for customization:
      a. **assignmet**
        - SpotAssignmentStrategy: how parking spots are chosen
          b. **pricing**
        - PricingStrategy: dynamic pricing rules (e.g., weekday/weekend)
        - PricingStrategyFactory: returns appropriate strategy based on context

7. UTILS (package: utils)
    - Reusable utility classes for time/date calculations, floor utilities, etc.
    - E.g., DateTimeUtils, ParkingFloorUtils

8. MAIN APP CLASS
    - `ParkingLotApplication`: Bootstraps the application.

This structure is cleanly decoupled for maintainability, testability, and future enhancements (e.g., adding new pricing models or spot allocation logic).
