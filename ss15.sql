create database ss15_db;
use ss15_db;

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DOUBLE
);

CREATE TABLE review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    user_id INT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO product (name, description, price) VALUES
            ('Laptop Dell XPS 13', 'Laptop cao cấp với thiết kế mỏng nhẹ và hiệu năng mạnh mẽ.', 32999.99),
            ('iPhone 14 Pro', 'Điện thoại thông minh mới nhất của Apple với camera cải tiến.', 24999.00),
            ('Samsung Galaxy S23', 'Điện thoại cao cấp Android với màn hình Dynamic AMOLED.', 22999.50),
            ('Tai nghe Sony WH-1000XM4', 'Tai nghe chống ồn chủ động với chất lượng âm thanh tuyệt vời.', 6999.99),
            ('Bàn phím cơ Keychron K6', 'Bàn phím không dây nhỏ gọn, switch Gateron, đèn nền RGB.', 1999.00);

CREATE TABLE cart (
    idCart INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    idProduct INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (idProduct) REFERENCES product(id)
);
