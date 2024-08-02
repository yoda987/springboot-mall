CREATE DATABASE mall

use mall


CREATE TABLE product (
    product_id INT NOT NULL PRIMARY KEY IDENTITY,
    product_name VARCHAR(128) NOT NULL,
    category VARCHAR(32) NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    description VARCHAR(1024),
    created_date DATETIME2 NOT NULL DEFAULT GETDATE(),
    last_modified_date DATETIME2 NOT NULL DEFAULT GETDATE()
);


select * from product

INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)
VALUES ('蘋果', 'FOOD', 'https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261__480.jpg', 
        20, 10, '這是來自澳洲的蘋果', 
        '2024-07-31 02:40:28', '2024-07-31 03:42:21');

		Select product_id,product_name, category, image_url, price, stock, description, created_date, last_modified_date from product where product_id = 1