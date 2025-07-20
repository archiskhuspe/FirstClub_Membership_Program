# FirstClub: Membership Program

A modular, tiered, subscription-based membership system for e-commerce, built with Java Spring Boot.

---

## Project Overview
FirstClub Membership's backend system manages tiered, subscription-based memberships for e-commerce. It supports:
- Multiple membership plans (Monthly, Quarterly, Yearly)
- User tiering (Silver, Gold, Platinum)
- Unlockable benefits (free delivery, discounts, exclusive deals, etc.)
- Full membership lifecycle: subscribe, upgrade, downgrade, cancel, renew
- Extensible, event-driven, and ready for integration with checkout/user/order flows
- **Full CRUD for Users** (Create, Read, Update, Delete)

---

## Features
- **Membership Plans:** Monthly, Quarterly, Yearly, each with its own pricing and tier mapping
- **Tiers:** Silver, Gold, Platinum (auto-upgrade/downgrade supported)
- **Benefits:** Free delivery, extra discounts, exclusive deals, coupons, priority support, etc.
- **User Actions:**
  - Create, read, update, delete users
  - Subscribe to a plan
  - Upgrade/downgrade plan or tier
  - Cancel or renew membership
  - Track current plan, tier, dates, and benefits
  - Query all available plans, tiers, and tier-based benefits
- **Edge Cases Handled:**
  - Prorated upgrades/downgrades
  - Grace periods after cancellation
  - Tier jumps for yearly plans, etc.
- **Mocked:** User model and payment flows (for local/demo use)
- **Seed Data:** Preloaded users, plans, tiers, and benefits for instant testing
- **OpenAPI/Swagger UI:** For easy API exploration
- **Comprehensive Test Coverage:** Build tests for all major flows

---

## Architecture
- **Tech Stack:** Java 17+, Spring Boot 3, Spring Data JPA (Hibernate), H2 (in-memory DB), Lombok, Maven
- **Modular Monolith:** Clean separation of models, repositories, services, controllers
- **Event-driven ready:** Easily extensible for background jobs (renewals, expiries, tier evaluation)
- **Local only:** No external integrations required for demo/testing

---

## Data Model (Entities)
- **User:** id, email, name
- **Plan:** id, name, price, currency, durationMonths, defaultTier, isActive, description
- **Tier:** id, name, level, criteriaJson, isActive, description
- **Benefit:** id, name, type, description, benefitValue, applicableTo
- **TierBenefit:** id, tier, benefit, isActive
- **Membership:** id, user, plan, tier, startDate, expiryDate, autoRenew, status, lastRenewedAt, nextRenewalAt, gracePeriodUntil, cancelledAt, proratedUntil
- **Transaction:** id, user, membership, type, amount, currency, status, paymentGateway, externalReference, createdAt

---

## API Summary
- `POST   /membership/users`         — Create a new user
- `GET    /membership/users`         — List all users
- `GET    /membership/users/{id}`    — Get user by ID
- `PUT    /membership/users/{id}`    — Update user by ID
- `DELETE /membership/users/{id}`    — Delete user by ID
- `POST   /membership/subscribe`     — Subscribe to a plan
- `PUT    /membership/upgrade`       — Upgrade plan/tier
- `PUT    /membership/downgrade`     — Downgrade plan/tier
- `POST   /membership/cancel`        — Cancel membership
- `POST   /membership/renew`         — Renew membership
- `GET    /membership/status?userId=...` — Get current membership status
- `GET    /membership/plans`         — List all plans
- `GET    /membership/tiers`         — List all tiers and their benefits
- `GET    /membership/tiers/{tierId}/benefits` — List benefits for a specific tier
- `GET    /membership/catalog`       — Get all plan-tier-benefit combinations

---

## Setup & Running Locally

### **Prerequisites**
- Java 17 or newer
- Maven

### **Clone or Unzip the Project**
```sh
unzip FirstClub_Membership_Program.zip
cd FirstClub_Membership_Program\ firstclub-membership
```

### **Build the Project (with tests)**
```sh
mvn clean install
```

### **Run the Application**
```sh
mvn spring-boot:run
```
- The app will start on [http://localhost:8080](http://localhost:8080)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa` (no password)

### **Test the API**
- Use the included Postman collection: `FirstClub_Membership_API.postman_collection.json`

### **Run Build Tests**
- Tests cover user CRUD, plans, tiers, subscribe, upgrade, downgrade, cancel, and renew flows.
---
