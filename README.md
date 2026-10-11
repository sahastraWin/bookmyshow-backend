# 🎬 Midnight Cinema (BookMyShow Full-Stack Clone)

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Render](https://img.shields.io/badge/Render-Deployed-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://render.com/)

A modern cinema ticket booking platform inspired by BookMyShow. Built with a high-performance **Spring Boot 3 + MySQL** REST backend and a zero-dependency **vanilla HTML5 / CSS3 / ES6 modular frontend** featuring dynamic themes, real-time seat reservation, cancellation flows, and digital boarding passes.

---

## ✨ Key Features

- **🌙 Midnight Black & ☀️ Light Mode:** Instant theme toggling in the navbar with automatic `localStorage` persistence.
- **🟢 Real-Time Interactive Seat Selection:** Grid layout with emerald-green glowing selection (`#10B981`), row alphabets, VIP/Premium tiers, and live pricing calculation.
- **🎉 Booking Confirmation Popup:** Animated modal with a pulsing green checkmark ring, reservation badge, and one-click ticket pass redirection.
- **🎟️ Digital Ticket Passes:** QR code generation, theater details, seat numbers, and booking reference IDs.
- **❌ Instant Ticket Cancellation & Seat Release:** Cancel tickets directly from "My Bookings" with smooth animated removal, which immediately frees up the seats for other customers.
- **📽️ Movie Switcher:** Switch between currently showing movies directly on the showtimes page without navigation bounces.
- **🔐 User Authentication:** User registration, session handling, and demo autofill credentials.

---

## 🧱 Architecture & Tech Stack

| Layer | Technologies |
|-------|--------------|
| **Backend** | Java 21, Spring Boot 3, Spring Data JPA, Hibernate, Lombok |
| **Database** | MySQL 8.0 (configured with automatic table generation & `data.sql` seeding) |
| **Frontend** | Vanilla HTML5, Modern CSS3 (Custom Properties & Design Tokens), ES6 Modules |
| **Container & Cloud** | Docker multi-stage build, Render Cloud Platform |

---

## 🗂️ Project Structure

```
bms-redesigned/
├── src/main/java/com/bms/       # Spring Boot Application
│   ├── config/                  # CORS and web configuration
│   ├── controller/              # REST Controllers (Movies, Shows, Seats, Bookings, Users)
│   ├── entity/                  # JPA Entities (Movie, Show, Screen, Seat, Booking, User)
│   ├── repository/              # Spring Data JPA Repositories
│   └── service/                 # Business logic & transactional service layer
├── src/main/resources/
│   ├── application.properties   # Database and server config
│   ├── data.sql                 # Seed data (movies, shows, theaters, 32 seats per show)
│   └── static/                  # Production frontend bundle served by Spring Boot
├── frontend/                    # Standalone static web application
│   ├── css/
│   │   ├── tokens.css           # Black mode & light mode variables
│   │   └── base.css             # Glassmorphism cards, unified buttons, popups
│   ├── js/
│   │   ├── api.js               # Dynamic API client with error handling
│   │   ├── auth.js              # User session management
│   │   ├── config.js            # Environment-aware API base URL resolution
│   │   └── ui.js                # Navbar, theme toggling, toasts & green popups
│   ├── index.html               # Home page with Now Showing hero rail
│   ├── shows.html               # Showtimes by theater with movie pill switcher
│   ├── seats.html               # Interactive seat layout with green highlights
│   ├── bookings.html            # My Bookings with live cancellation removal
│   ├── confirmation.html        # Digital ticket pass with QR code
│   ├── auth.html                # Sign In / Registration with demo autofill
│   └── serve.json               # Local static server routing config
├── Dockerfile                   # Multi-stage Docker build for deployment
└── pom.xml                      # Maven dependencies and build plugins
```

---

## 🚀 Running Locally

### 1. Start MySQL Database
Ensure MySQL is running on port `3306`:
```sql
CREATE DATABASE IF NOT EXISTS bookmyshow;
```

Update your password in `src/main/resources/application.properties` (or pass via environment variables):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```

### 2. Start the Spring Boot Backend
In the `bms-redesigned` directory:
```bash
./mvnw spring-boot:run
```
> The API will be live at `http://localhost:8080`. On first startup, `data.sql` automatically populates sample movies (*Dune: Part Two*, *Oppenheimer*, *Interstellar*), theaters, showtimes, and auditorium seats.

### 3. Start the Frontend Server
In a new terminal window:
```bash
cd frontend
npx serve . -l 5500
```
Open **`http://localhost:5500`** in your browser.

> **Demo Credentials:**  
> Email: `demo@bookmyshow.com` | Password: `password123` (or click **Autofill** on the Sign In page).

---

## ☁️ How to Deploy on Render

### Option 1: All-in-One Deployment (Recommended - Zero Extra Cost)

Since all frontend files from `frontend/` are automatically packaged into `src/main/resources/static/`, **your existing Docker Web Service on Render already contains and serves the entire frontend!**

1. Push your latest code to your GitHub repository:
   ```bash
   git add .
   git commit -m "feat: complete full-stack movie booking system with green highlight & theme toggling"
   git push origin feature/exquisite-frontend
   ```
2. In your Render Dashboard under your service **`bookmyshow-backend`**:
   - Render will detect the new commit and automatically trigger a Docker build.
3. Once deployed, open your Render service URL (e.g. `https://bookmyshow-backend.onrender.com`):
   - Visiting `/` directly loads the frontend home page!
   - REST API requests hit `/api/*` on the same domain with **zero CORS configuration** needed.

### Option 2: Deploy Frontend as a Render Static Site

If you prefer hosting the frontend separately:
1. In Render Dashboard, click **New +** > **Static Site**.
2. Connect your GitHub repository `bookmyshow-backend`.
3. Set the following settings:
   - **Root Directory:** `bms-redesigned/frontend`
   - **Publish Directory:** `.`
4. In `frontend/js/config.js`, `CONFIG.API_BASE_URL` automatically routes requests to your Render backend `https://bookmyshow-backend.onrender.com/api`!
5. Add a Rewrite Rule in Render Static Site Settings:
   - **Source:** `/*`
   - **Destination:** `/index.html`

---

## 📡 REST API Reference

| Method | Endpoint | Description |
|:-------|:---------|:------------|
| `GET` | `/api/movies` | Fetch all active movies |
| `GET` | `/api/movies/{id}` | Fetch movie details by ID |
| `GET` | `/api/shows/movie/{id}` | Fetch showtimes and screens for a movie |
| `GET` | `/api/shows/{id}` | Fetch showtime details |
| `GET` | `/api/seats/show/{id}` | Fetch auditorium seat map and availability |
| `POST` | `/api/bookings` | Book selected seats (`userId`, `showId`, `seatIds`) |
| `GET` | `/api/bookings/{id}` | Fetch booking details by ID |
| `GET` | `/api/bookings/user/{id}` | Fetch all bookings for a user |
| `PUT` | `/api/bookings/{id}/cancel` | Cancel booking and release reserved seats |
| `POST` | `/api/users/login` | Authenticate user credentials |
| `POST` | `/api/users/register` | Register a new user account |

---

## 📄 License
Distributed under the MIT License.
