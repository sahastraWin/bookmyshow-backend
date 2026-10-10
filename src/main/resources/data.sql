-- Cities
INSERT IGNORE INTO cities (id, name, state) VALUES (1, 'Mumbai', 'Maharashtra');
INSERT IGNORE INTO cities (id, name, state) VALUES (2, 'Bengaluru', 'Karnataka');

-- Theaters
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (1, 'PVR Icon', 'Andheri West', 1);
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (2, 'Inox', 'Malad West', 1);
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (3, 'Cinepolis', 'Koramangala', 2);

-- Movies
INSERT IGNORE INTO movies (id, title, description, genre, language, duration_minutes, rating, poster_url, release_date) 
VALUES (1, 'Dune: Part Two', 'Paul Atreides unites with Chani.', 'Sci-Fi', 'English', 166, 8.8, 'https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2JGqqUTTe.jpg', '2024-03-01');

INSERT IGNORE INTO movies (id, title, description, genre, language, duration_minutes, rating, poster_url, release_date) 
VALUES (2, 'Oppenheimer', 'The story of J. Robert Oppenheimer.', 'Drama', 'English', 180, 8.5, 'https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg', '2023-07-21');

-- Screens
INSERT IGNORE INTO screens (id, name, total_seats, theater_id) VALUES (1, 'Screen 1', 60, 1);
INSERT IGNORE INTO screens (id, name, total_seats, theater_id) VALUES (2, 'IMAX', 60, 1);

-- Seats (just a few for demo)
-- Screen 1
INSERT IGNORE INTO seats (id, seat_number, row_name, col_number, seat_type, screen_id) VALUES (1, 'A1', 'A', 1, 'NORMAL', 1);
INSERT IGNORE INTO seats (id, seat_number, row_name, col_number, seat_type, screen_id) VALUES (2, 'A2', 'A', 2, 'NORMAL', 1);
INSERT IGNORE INTO seats (id, seat_number, row_name, col_number, seat_type, screen_id) VALUES (3, 'B1', 'B', 1, 'PREMIUM', 1);
INSERT IGNORE INTO seats (id, seat_number, row_name, col_number, seat_type, screen_id) VALUES (4, 'B2', 'B', 2, 'PREMIUM', 1);

-- Shows
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (1, 1, 1, CURRENT_DATE, '10:00:00', '13:00:00', 250.0);
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (2, 2, 2, CURRENT_DATE, '14:00:00', '17:00:00', 400.0);

-- Users
INSERT IGNORE INTO users (id, name, email, password, phone_number, created_at) 
VALUES (1, 'Demo User', 'demo@bookmyshow.com', 'password123', '1234567890', CURRENT_TIMESTAMP);
