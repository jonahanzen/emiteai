CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    uuid varchar NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(255),
    cep VARCHAR(10) NOT NULL,
    neighborhood VARCHAR(100),
    city_name VARCHAR(100),
    state_name VARCHAR(50)
);


CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    uuid varchar NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    address_id INTEGER REFERENCES address(id)
);


CREATE TABLE request_audit_log (
    id SERIAL PRIMARY KEY,
    uuid varchar NOT NULL DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    url varchar NOT NULL,
    headers varchar NOT NULL,
    method varchar NOT NULL,
    body varchar
);


CREATE INDEX idx_address_uuid ON address(uuid);
CREATE INDEX idx_person_uuid ON person(uuid);
CREATE INDEX idx_request_audit_log_uuid ON request_audit_log(uuid);