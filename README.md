# Midnight Cinema (BookMyShow Clone)

![Midnight Cinema](https://via.placeholder.com/1200x400/181824/ffffff?text=Midnight+Cinema)

An exquisite, cinematic movie booking experience, bringing the vibe of a luxury theatre lobby straight to your browser. Built with a robust Spring Boot REST backend and a pure HTML/CSS/JS ES6 modular frontend.

## ✨ Features
- **Cinematic UI:** Custom design tokens, glassmorphism, fluid typography, and layered soft shadows.
- **Dynamic Content:** Connects to a live Spring Boot backend for real-time movies, shows, and seat availability.
- **Seat Map Engine:** SVG-style responsive seat picker with row grouping and state management (Available, Selected, Booked).
- **Authentication:** In-memory local storage auth flow with login and registration.
- **Booking Flow:** E2E journey from movie selection to ticket generation with an animated stub.

## 🧱 Tech Stack
- **Backend:** Java, Spring Boot, Spring Data JPA, MySQL
- **Frontend:** Vanilla HTML5, CSS3 (Custom Properties), ES6 Modules (No Frameworks!)
- **Build/Server:** Maven

## 🗂️ Folder Structure
```
bookmyshow-backend-main/bms-redesigned/
├── src/main/java      # Spring Boot Backend code
├── src/main/resources # application.properties, data.sql
├── frontend/          # Exquisite UI code
│   ├── css/           # Design tokens and base styles
│   ├── js/            # API, Auth, and Router logic
│   └── *.html         # Feature pages
└── pom.xml            # Maven configuration
```

## 🚀 How to Run

### 1. Backend (Spring Boot)
Ensure you have MySQL running on port 3306.
Update `src/main/resources/application.properties` with your MySQL credentials.
Run the app:
```bash
./mvnw spring-boot:run
```
*Note: The app runs on `http://localhost:8080`. On the first run, `data.sql` will seed demo movies and theaters.*

### 2. Frontend
Since the frontend uses ES6 modules, it must be served via a web server.
```bash
cd frontend
npx serve .
# OR
python -m http.server 5500
```
Open `http://localhost:5500` in your browser.

## 📚 API Endpoint Reference

| Resource | Endpoints | Method | Notes |
|----------|-----------|--------|-------|
| Users | `/api/users/login`, `/register` | POST | Returns user ID for session. |
| Movies | `/api/movies`, `/api/movies/{id}` | GET, POST | Lists and fetches movies. |
| Shows | `/api/shows/movie/{id}` | GET | Returns shows for a specific movie. |
| Seats | `/api/seats/show/{id}` | GET | Fetches seat layout/status for a show. |
| Bookings | `/api/bookings`, `/api/bookings/user/{id}` | POST, GET | Create and view bookings. |

## 👤 User Walkthrough
1. **Login:** Use `demo@bookmyshow.com` / `password123` or register a new account.
2. **Browse:** Select "Dune: Part Two" or "Oppenheimer" from the Now Showing rail.
3. **Shows:** Pick a showtime from the available theaters.
4. **Seats:** Select available (unbooked) seats. Watch the total price update in real-time.
5. **Book:** Confirm the booking and receive your beautiful digital ticket stub.

## 📄 License
MIT License
