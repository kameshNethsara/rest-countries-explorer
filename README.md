# Rest Countries Explorer (Sentura Technologies Practical Test)

This is a full-stack application built for the 2026 IJSE Internship Interview Practical Test.

## Features
- **Backend (Spring Boot):** - Fetches data from `https://restcountries.com/v3.1/`.
  - Filters specific fields: Name, Capital, Region, Population, and Flag.
  - Implements in-memory caching with a 10-minute refresh rate using `@Scheduled`.
- **Frontend (React + Vite + TypeScript):**
  - Dark-themed, responsive UI.
  - Real-time search filtering.
  - Detailed view of countries via a modal.

## How to Run
1. **Backend:**
   - Navigate to the backend folder.
   - Run `./mvnw spring-boot:run`.
2. **Frontend:**
   - Navigate to the frontend folder.
   - Run `npm install` and then `npm run dev`.

## Technologies Used
- Java 17+, Spring Boot, RestTemplate, Lombok.
- React, TypeScript, Vite, CSS3 (Flexbox/Media Queries).