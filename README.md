# Real-Time Alert Notification POC

This project is a proof of concept (POC) that demonstrates a **real-time alert notification system** for East West Bank using **Apache Kafka** to enable scalable, reliable, and event-driven communication between core banking systems and consumer notification channels (e.g., email, SMS, mobile push).

## Tech Stack

- **Java**: 21
- **Framework**: Spring Boot (Web, Actuator)
- **Messaging**: Apache Kafka with Spring Kafka
- **Logging**: SLF4J
- **Serialization**: Jackson (JSON)

## High-Level Architecture

- **Producer side (Banking System)**: Publishes alert events (e.g., transaction alerts, balance alerts) to Kafka topics.
- **Kafka**: Acts as the event backbone, decoupling producers from consumers and providing durability and scalability.
- **Consumer side (Notification Service)**: Consumes alert events from Kafka and forwards them to notification channels (email/SMS/push). For this POC we typically expose REST endpoints and simple logging to simulate notifications.
- **Observability**: Spring Boot Actuator exposes health and metrics endpoints to monitor the service.

## Prerequisites

- JDK **21**
- Maven **3.9+**
- A running **Apache Kafka** broker (local or remote)

## Build and Run

1. **Build the project**

   ```bash
   mvn clean install
   ```

2. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

3. The application will start on `http://localhost:8080` by default.

## Next Steps for the POC

- Implement Kafka **producer** to publish sample alert events.
- Implement Kafka **consumer** to process alerts and simulate sending notifications.
- Add REST endpoints to trigger test alerts and inspect status.
- Configure Actuator health checks for Kafka connectivity.
