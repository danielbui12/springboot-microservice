CREATE TABLE inventories
(
    inventory_id BIGSERIAL PRIMARY KEY,
    sku_code VARCHAR(255),
    quantity INTEGER
);
