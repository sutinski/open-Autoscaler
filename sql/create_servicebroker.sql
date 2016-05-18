CREATE TABLE IF NOT EXISTS service_instance (
  service_id varchar(36) NOT NULL PRIMARY KEY,
  org_id varchar(36) NOT NULL,
  space_id varchar(36) NOT NULL
);