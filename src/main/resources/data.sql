-- Cities
INSERT IGNORE INTO cities (id, name, state) VALUES (1, 'Mumbai', 'Maharashtra');
INSERT IGNORE INTO cities (id, name, state) VALUES (2, 'Bengaluru', 'Karnataka');

-- Theaters
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (1, 'PVR Icon', 'Andheri West', 1);
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (2, 'Inox Insignia', 'Malad West', 1);
INSERT IGNORE INTO theaters (id, name, address, city_id) VALUES (3, 'Cinepolis', 'Koramangala', 2);

-- Movies
INSERT IGNORE INTO movies (id, title, description, genre, language, duration_minutes, rating, poster_url, release_date) 
VALUES (1, 'Dune: Part Two', 'Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.', 'Sci-Fi', 'English', 166, 8.8, 'https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2JGqqUTTe.jpg', '2024-03-01');

INSERT IGNORE INTO movies (id, title, description, genre, language, duration_minutes, rating, poster_url, release_date) 
VALUES (2, 'Oppenheimer', 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.', 'Drama', 'English', 180, 8.5, 'https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg', '2023-07-21');

INSERT IGNORE INTO movies (id, title, description, genre, language, duration_minutes, rating, poster_url, release_date) 
VALUES (3, 'Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.', 'Sci-Fi', 'English', 169, 8.7, 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg', '2014-11-07');

-- Screens
INSERT IGNORE INTO screens (id, name, total_seats, theater_id) VALUES (1, 'Audi 1 - Luxe', 32, 1);
INSERT IGNORE INTO screens (id, name, total_seats, theater_id) VALUES (2, 'IMAX 3D Laser', 32, 1);
INSERT IGNORE INTO screens (id, name, total_seats, theater_id) VALUES (3, 'Screen 2', 32, 2);

-- Seats for Screen 1 (Rows A to D, Cols 1 to 8)
INSERT IGNORE INTO seats (id, seat_number, seat_row, seat_col, seat_type, screen_id) VALUES
(1, 'A1', 'A', 1, 'REGULAR', 1), (2, 'A2', 'A', 2, 'REGULAR', 1), (3, 'A3', 'A', 3, 'REGULAR', 1), (4, 'A4', 'A', 4, 'REGULAR', 1),
(5, 'A5', 'A', 5, 'REGULAR', 1), (6, 'A6', 'A', 6, 'REGULAR', 1), (7, 'A7', 'A', 7, 'REGULAR', 1), (8, 'A8', 'A', 8, 'REGULAR', 1),
(9, 'B1', 'B', 1, 'REGULAR', 1), (10, 'B2', 'B', 2, 'REGULAR', 1), (11, 'B3', 'B', 3, 'REGULAR', 1), (12, 'B4', 'B', 4, 'REGULAR', 1),
(13, 'B5', 'B', 5, 'REGULAR', 1), (14, 'B6', 'B', 6, 'REGULAR', 1), (15, 'B7', 'B', 7, 'REGULAR', 1), (16, 'B8', 'B', 8, 'REGULAR', 1),
(17, 'C1', 'C', 1, 'PREMIUM', 1), (18, 'C2', 'C', 2, 'PREMIUM', 1), (19, 'C3', 'C', 3, 'PREMIUM', 1), (20, 'C4', 'C', 4, 'PREMIUM', 1),
(21, 'C5', 'C', 5, 'PREMIUM', 1), (22, 'C6', 'C', 6, 'PREMIUM', 1), (23, 'C7', 'C', 7, 'PREMIUM', 1), (24, 'C8', 'C', 8, 'PREMIUM', 1),
(25, 'D1', 'D', 1, 'VIP', 1), (26, 'D2', 'D', 2, 'VIP', 1), (27, 'D3', 'D', 3, 'VIP', 1), (28, 'D4', 'D', 4, 'VIP', 1),
(29, 'D5', 'D', 5, 'VIP', 1), (30, 'D6', 'D', 6, 'VIP', 1), (31, 'D7', 'D', 7, 'VIP', 1), (32, 'D8', 'D', 8, 'VIP', 1);

-- Seats for Screen 2 (Rows A to D, Cols 1 to 8)
INSERT IGNORE INTO seats (id, seat_number, seat_row, seat_col, seat_type, screen_id) VALUES
(33, 'A1', 'A', 1, 'REGULAR', 2), (34, 'A2', 'A', 2, 'REGULAR', 2), (35, 'A3', 'A', 3, 'REGULAR', 2), (36, 'A4', 'A', 4, 'REGULAR', 2),
(37, 'A5', 'A', 5, 'REGULAR', 2), (38, 'A6', 'A', 6, 'REGULAR', 2), (39, 'A7', 'A', 7, 'REGULAR', 2), (40, 'A8', 'A', 8, 'REGULAR', 2),
(41, 'B1', 'B', 1, 'REGULAR', 2), (42, 'B2', 'B', 2, 'REGULAR', 2), (43, 'B3', 'B', 3, 'REGULAR', 2), (44, 'B4', 'B', 4, 'REGULAR', 2),
(45, 'B5', 'B', 5, 'REGULAR', 2), (46, 'B6', 'B', 6, 'REGULAR', 2), (47, 'B7', 'B', 7, 'REGULAR', 2), (48, 'B8', 'B', 8, 'REGULAR', 2),
(49, 'C1', 'C', 1, 'PREMIUM', 2), (50, 'C2', 'C', 2, 'PREMIUM', 2), (51, 'C3', 'C', 3, 'PREMIUM', 2), (52, 'C4', 'C', 4, 'PREMIUM', 2),
(53, 'C5', 'C', 5, 'PREMIUM', 2), (54, 'C6', 'C', 6, 'PREMIUM', 2), (55, 'C7', 'C', 7, 'PREMIUM', 2), (56, 'C8', 'C', 8, 'PREMIUM', 2),
(57, 'D1', 'D', 1, 'VIP', 2), (58, 'D2', 'D', 2, 'VIP', 2), (59, 'D3', 'D', 3, 'VIP', 2), (60, 'D4', 'D', 4, 'VIP', 2),
(61, 'D5', 'D', 5, 'VIP', 2), (62, 'D6', 'D', 6, 'VIP', 2), (63, 'D7', 'D', 7, 'VIP', 2), (64, 'D8', 'D', 8, 'VIP', 2);

-- Shows
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (1, 1, 1, CURRENT_DATE, '10:00:00', '13:00:00', 250.0);
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (2, 1, 2, CURRENT_DATE, '18:30:00', '21:30:00', 350.0);
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (3, 2, 2, CURRENT_DATE, '14:00:00', '17:00:00', 400.0);
INSERT IGNORE INTO shows (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price) 
VALUES (4, 3, 1, CURRENT_DATE, '21:45:00', '00:45:00', 300.0);

-- Users
INSERT IGNORE INTO users (id, name, email, password, phone_number, created_at) 
VALUES (1, 'Demo User', 'demo@bookmyshow.com', 'password123', '1234567890', CURRENT_TIMESTAMP);
