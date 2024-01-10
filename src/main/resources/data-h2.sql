INSERT INTO "PUBLIC"."USER" ("ID", "EMAIL", "FIRST_NAME", "LAST_NAME") VALUES
(1, 'user1@example.com', 'John', 'Doe'),
(2, 'user2@example.com', 'Jane', 'Smith'),
(3, 'user3@example.com', 'Alice', 'Johnson');

INSERT INTO "PUBLIC"."CATEGORY" ("ID", "CATEGORY_NAME", "DESCRIPTION", "IMAGE_URL") VALUES
(1, 'Electronics', 'Electronic gadgets and devices', 'electronics.jpg'),
(2, 'Clothing', 'Fashionable clothing items', 'clothing.jpg'),
(3, 'Books', 'A wide variety of books', 'books.jpg');

INSERT INTO "PUBLIC"."PRODUCT" ("ID", "DESCRIPTION", "IMAGEURL", "NAME", "PRICE", "CATEGORY_ID") VALUES
(101, 'Smartphone with advanced features', 'phone1.jpg', 'Smartphone A', 499.99, 1),
(102, 'Laptop with high performance', 'laptop1.jpg', 'Laptop X', 899.99, 1),
(103, 'Stylish T-shirt for casual wear', 'tshirt1.jpg', 'Casual T-shirt', 29.99, 2),
(104, 'Classic Novel by Author X', 'book1.jpg', 'Classic Novel', 14.99, 3),
(105, 'Wireless Earbuds with noise cancellation', 'earbuds1.jpg', 'Noise-Canceling Earbuds', 79.99, 1);

INSERT INTO "PUBLIC"."INVENTORY" ("ID", "NAME", "QUANTITY", "PRODUCT_ID") VALUES
(1, 'Inventory1', 10, 101),
(2, 'Inventory2', 15, 102),
(3, 'Inventory3', 8, 103),
(4, 'Inventory4', 20, 104),
(5, 'Inventory5', 12, 105);

INSERT INTO "PUBLIC"."CART" ("ID", "USER_ID") VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO "PUBLIC"."CART_PRODUCT" ("CART_ID", "QUANTITY", "PRODUCT_ID") VALUES
(1, 2, 101),
(1, 1, 102),
(2, 3, 103),
(2, 1, 104),
(3, 2, 105);
