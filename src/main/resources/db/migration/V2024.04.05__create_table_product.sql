CREATE TABLE product (
    id_product INTEGER NOT NULL,
    name_product character varying(256) NOT NULL,
    description_product character varying(256) NOT NULL,
    price_product NUMERIC NOT NULL,
    stock_product INTEGER NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id_product)
