#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE st_customer;
	CREATE DATABASE st_inventory;
	CREATE DATABASE st_order;
	CREATE DATABASE st_payment;
	CREATE DATABASE st_shipping;
EOSQL