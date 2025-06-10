-- Drop the database if it exists
DROP DATABASE IF EXISTS prs_capstone;

-- Create a new database
CREATE DATABASE prs_capstone;

-- Select (use) the new database
USE prs_capstone;

CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(10) NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    phoneNumber VARCHAR(12) NOT NULL,
    email VARCHAR(75) NOT NULL,
    isReviewer BOOLEAN NOT NULL DEFAULT FALSE,
    isAdmin BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT uname UNIQUE (username)
);

CREATE TABLE Vendor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(5) NOT NULL,
    phoneNumber VARCHAR(12) NOT NULL,
    email VARCHAR(100) NOT NULL,
    CONSTRAINT vcode UNIQUE (code)
);

CREATE TABLE Product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vendorId INT NOT NULL,
    partNumber VARCHAR(50) NOT NULL,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    unit VARCHAR(255),
    photoPath VARCHAR(255),
    FOREIGN KEY (vendorId) REFERENCES Vendor(id),
    CONSTRAINT vendor_part UNIQUE (vendorId, partNumber)
);

CREATE TABLE Request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    requestNumber VARCHAR(20) NOT NULL,
    description VARCHAR(100) NOT NULL,
    justification VARCHAR(255) NOT NULL,
    dateNeeded DATE NOT NULL,
    deliveryMode VARCHAR(25) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'NEW',
    total DECIMAL(10,2) NOT NULL DEFAULT 0.0,
    submittedDate DATETIME NOT NULL,
    reasonForRejection VARCHAR(100),
    FOREIGN KEY (userId) REFERENCES User(id)
);

CREATE TABLE LineItem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    requestId INT NOT NULL,
    productId INT NOT NULL,
    quantity INT NOT NULL, 
    FOREIGN KEY (productId) REFERENCES Product(id),
    FOREIGN KEY (requestId) REFERENCES Request(id),
    CONSTRAINT req_pdt UNIQUE (requestId, productId)
);