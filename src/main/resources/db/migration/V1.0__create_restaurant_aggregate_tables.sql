CREATE TABLE restaurants (
   "version" int8 NOT NULL DEFAULT 0,
   name text NOT NULL,
   CONSTRAINT "pk-restaurants" PRIMARY KEY (name)
);

CREATE TABLE meals (
    restaurant_name text NOT NULL references restaurants (name),
    name text NOT NULL,
    CONSTRAINT "pk-meals" PRIMARY KEY (restaurant_name, name)
);