# 🎬 BookMyShow — Backend REST API

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring-boot" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven" />
  <img src="https://img.shields.io/badge/Deployed_on-Render-46E3B7?style=for-the-badge&logo=render" />
</p>

A production-grade, fully layered **RESTful backend** for a movie ticket booking platform — inspired by BookMyShow. Built with **Spring Boot 3**, **JPA/Hibernate**, and **MySQL**, featuring clean architecture, DTO mapping, centralised exception handling, and environment-based configuration for seamless local and cloud deployment.

---

## 📐 System Architecture

```
Client (Postman / Frontend)
        │
        ▼
┌─────────────────────────────┐
│      REST Controllers       │  ← /api/*  (HTTP layer)
├─────────────────────────────┤
│      Service Layer          │  ← Business logic + validation
├─────────────────────────────┤
│      Repository Layer       │  ← Spring Data JPA
├─────────────────────────────┤
│      MySQL Database         │  ← Persistent storage
└─────────────────────────────┘
```

---

## 🗺️ Domain Model

```
City ──< Theater ──< Screen ──< Seat
                        │
                       Show ──< Booking ──< User
                        └── Movie
```

| Entity    | Description                                     |
|-----------|-------------------------------------------------|
| `City`    | Geographic city where theaters are located      |
| `Theater` | Venue belonging to a city                       |
| `Screen`  | Auditorium (AUDI-1, AUDI-2…) inside a theater   |
| `Seat`    | Individual seat in a screen (REGULAR/PREMIUM/VIP) |
| `Movie`   | Film details — title, genre, language, rating   |
| `Show`    | Scheduled screening of a movie on a screen      |
| `User`    | Registered customer                             |
| `Booking` | Confirmed seat reservation linking user + show  |

---

## 🚀 API Endpoints

All endpoints are prefixed with `/api`.

### 🏙️ Cities
| Method | Endpoint          | Description          |
|--------|-------------------|----------------------|
| POST   | `/api/cities`     | Add a city           |
| GET    | `/api/cities`     | List all cities      |
| GET    | `/api/cities/{id}`| Get city by ID       |

### 🎭 Theaters
| Method | Endpoint                          | Description                    |
|--------|-----------------------------------|--------------------------------|
| POST   | `/api/theaters`                   | Add a theater                  |
| GET    | `/api/theaters`                   | List all theaters               |
| GET    | `/api/theaters/{id}`              | Get theater by ID              |
| GET    | `/api/theaters/city/{cityId}`     | Get theaters by city           |

### 📺 Screens
| Method | Endpoint                              | Description                  |
|--------|---------------------------------------|------------------------------|
| POST   | `/api/screens`                        | Add a screen                 |
| GET    | `/api/screens`                        | List all screens             |
| GET    | `/api/screens/{id}`                   | Get screen by ID             |
| GET    | `/api/screens/theater/{theaterId}`    | Get screens by theater       |

### 💺 Seats
| Method | Endpoint                      | Description                                    |
|--------|-------------------------------|------------------------------------------------|
| POST   | `/api/seats`                  | Add a seat                                     |
| GET    | `/api/seats/{id}`             | Get seat by ID                                 |
| GET    | `/api/seats/show/{showId}`    | List seats with AVAILABLE/BOOKED status for a show |

### 🎬 Movies
| Method | Endpoint                          | Description                    |
|--------|-----------------------------------|--------------------------------|
| POST   | `/api/movies`                     | Add a movie                    |
| GET    | `/api/movies`                     | List all movies                |
| GET    | `/api/movies/{id}`                | Get movie by ID                |
| GET    | `/api/movies/search?title=xxx`    | Search movies by title         |
| GET    | `/api/movies/genre/{genre}`       | Filter movies by genre         |
| GET    | `/api/movies/language/{language}` | Filter movies by language      |
| PUT    | `/api/movies/{id}`                | Update movie details           |
| DELETE | `/api/movies/{id}`                | Delete a movie                 |

### 🕐 Shows
| Method | Endpoint                                              | Description                        |
|--------|-------------------------------------------------------|------------------------------------|
| POST   | `/api/shows`                                          | Schedule a show                    |
| GET    | `/api/shows`                                          | List all shows                     |
| GET    | `/api/shows/{id}`                                     | Get show by ID                     |
| GET    | `/api/shows/movie/{movieId}`                          | Get shows by movie                 |
| GET    | `/api/shows/movie/{movieId}/date?date=YYYY-MM-DD`     | Get shows by movie + date          |

### 👤 Users
| Method | Endpoint               | Description          |
|--------|------------------------|----------------------|
| POST   | `/api/users/register`  | Register a new user  |
| POST   | `/api/users/login`     | Login                |
| GET    | `/api/users`           | List all users       |
| GET    | `/api/users/{id}`      | Get user by ID       |

### 🎟️ Bookings
| Method | Endpoint                          | Description                          |
|--------|-----------------------------------|--------------------------------------|
| POST   | `/api/bookings`                   | Create a booking                     |
| GET    | `/api/bookings/{id}`              | Get booking by ID                    |
| GET    | `/api/bookings/user/{userId}`     | Get all bookings by user             |
| PUT    | `/api/bookings/{id}/cancel`       | Cancel entire booking                |
| PUT    | `/api/bookings/{id}/cancel-seats` | Cancel specific seats in a booking   |

---

## 🧰 Tech Stack

| Layer         | Technology                        |
|---------------|-----------------------------------|
| Language      | Java 17                           |
| Framework     | Spring Boot 3.3.0                 |
| ORM           | Spring Data JPA / Hibernate 6     |
| Database      | MySQL 8.0                         |
| Build Tool    | Maven 3.9                         |
| Utilities     | Lombok, Bean Validation (Jakarta) |
| Deployment    | Render (Web Service + MySQL)      |

---

## ⚙️ Local Setup — IntelliJ IDEA

### Prerequisites

- **Java 17** — [Download Adoptium](https://adoptium.net/)
- **MySQL 8.0** — running locally on port `3306`
- **IntelliJ IDEA** (Community or Ultimate)
- **Maven** bundled with IntelliJ (no separate install needed)

### Step-by-step

**1. Clone / open the project**
```bash
git clone https://github.com/<your-username>/bookmyshow.git
```
Open IntelliJ → **File → Open** → select the `bookmyshow` folder.

**2. Let IntelliJ import Maven**
IntelliJ will auto-detect `pom.xml` and download all dependencies. Wait for the progress bar at the bottom to finish.

**3. Create the MySQL database**
```sql
-- run in MySQL Workbench or any SQL client
CREATE DATABASE bookmyshow;
```

**4. Configure environment variables in IntelliJ**

Go to **Run → Edit Configurations → BookMyShowApplication → Environment variables** and add:

```
DB_URL=jdbc:mysql://localhost:3306/bookmyshow?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

> Alternatively, edit `src/main/resources/application.properties` directly for local dev:
> ```properties
> spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow?createDatabaseIfNotExist=true
> spring.datasource.username=root
> spring.datasource.password=your_password
> ```

**5. Run the application**

Open `BookMyShowApplication.java` → click the green ▶ **Run** button, or press **Shift + F10**.

You should see:
```
✅ BookMyShow API is up and running!
```

**6. Test with Postman or curl**
```bash
curl http://localhost:8080/api/cities
```

---

## ☁️ Deployment on Render

### Step 1 — Create a MySQL database on Render

1. Go to [https://render.com](https://render.com) → **New → MySQL**
2. Choose a name (e.g., `bookmyshow-db`), select the free plan
3. Once created, copy the **External Database URL** — it looks like:
   ```
   mysql://user:password@host:port/dbname
   ```
4. Note down: `host`, `port`, `database name`, `username`, `password` separately — you'll need them as individual env vars

### Step 2 — Push your code to GitHub

```bash
git init
git add .
git commit -m "feat: initial BookMyShow backend"
git remote add origin https://github.com/<your-username>/bookmyshow.git
git push -u origin main
```

### Step 3 — Create a Web Service on Render

1. **New → Web Service** → connect your GitHub repo
2. Configure the service:

| Field            | Value                                              |
|------------------|----------------------------------------------------|
| **Runtime**      | Java                                               |
| **Build Command**| `./mvnw clean package -DskipTests`                 |
| **Start Command**| `java -jar target/bookmyshow-1.0.0.jar`            |
| **Branch**       | `main`                                             |

### Step 4 — Set Environment Variables on Render

In your Web Service → **Environment** tab, add:

| Key                     | Value                                        |
|-------------------------|----------------------------------------------|
| `SPRING_PROFILES_ACTIVE`| `prod`                                       |
| `DB_URL`                | `jdbc:mysql://<host>:<port>/<dbname>?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true` |
| `DB_USERNAME`           | `<your render db username>`                  |
| `DB_PASSWORD`           | `<your render db password>`                  |
| `PORT`                  | `8080`                                       |

### Step 5 — Deploy

Click **Deploy** — Render will build the JAR and start the service. Your API will be live at:
```
https://your-service-name.onrender.com/api
```

---

## 📁 Project Structure

```
bookmyshow/
├── src/
│   ├── main/
│   │   ├── java/com/bms/
│   │   │   ├── BookMyShowApplication.java
│   │   │   ├── config/
│   │   │   │   └── CorsConfig.java
│   │   │   ├── controller/          # REST controllers (8 controllers)
│   │   │   ├── service/             # Interfaces + Implementations
│   │   │   ├── repository/          # Spring Data JPA interfaces
│   │   │   ├── entity/              # JPA entities
│   │   │   ├── dto/                 # Request & Response DTOs
│   │   │   ├── enums/               # BookingStatus, SeatType
│   │   │   └── exception/           # Custom exceptions + Global handler
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/com/bms/
│           └── BookMyShowApplicationTests.java
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

---

## 🔐 Error Handling

All errors return a consistent JSON structure:

```json
{
  "timestamp": "2025-06-09T14:23:11",
  "status": 404,
  "error": "Not Found",
  "message": "Movie not found with id: 99"
}
```

| Exception                    | HTTP Status |
|------------------------------|-------------|
| `ResourceNotFoundException`  | 404         |
| `BusinessException`          | 400         |
| `MethodArgumentNotValidException` | 400    |
| Unhandled `Exception`        | 500         |

---

## 📝 Sample Request Payloads

**Register User**
```json
POST /api/users/register
{
  "name": "Ravi Sharma",
  "email": "ravi@example.com",
  "password": "secret123",
  "phoneNumber": "9876543210"
}
```

**Create Booking**
```json
POST /api/bookings
{
  "userId": 1,
  "showId": 3,
  "seatIds": [10, 11, 12]
}
```

**Cancel Specific Seats**
```json
PUT /api/bookings/5/cancel-seats
{
  "seatIds": [11]
}
```

---

## 🚧 Future Enhancements

- JWT-based Authentication & Authorization (Spring Security)
- Password hashing with BCrypt
- Payment gateway integration (Razorpay / Stripe)
- Email notifications (Spring Mail)
- Pagination and sorting on list endpoints
- Redis caching for frequently fetched movies/shows
- Swagger / OpenAPI documentation

---

## 👤 Author

Built as a backend system design showcase — demonstrating RESTful API design, JPA relationships, layered architecture, and cloud deployment.
