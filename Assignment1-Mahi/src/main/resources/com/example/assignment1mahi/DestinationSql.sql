-- Create the database
CREATE DATABASE TouristDestinations;

-- Use the database
USE TouristDestinations;

-- Create the table with fields id, destination, and popularity
CREATE TABLE Destinations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(100),
    popularity INT
);

-- Insert data for the 8 most popular tourist destinations in India, arranged by popularity
INSERT INTO Destinations (destination, popularity) VALUES
('Goa Beaches', 8000000),
('Taj Mahal, Agra', 7000000),
('Varanasi', 6000000),
('Jaipur (City Palace, Amber Fort)', 4000000),
('Qutub Minar, Delhi', 3900000),
('Mysore Palace, Karnataka', 3500000),
('Ajanta and Ellora Caves, Maharashtra', 1500000),
('Kerala Backwaters', 1200000);