CREATE DATABASE  IF NOT EXISTS `medicalclinic_new` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `medicalclinic_new`;
create TABLE patient_records (
  id int(10) unsigned not null,
  patient_id int(10) unsigned not null,
  patient_record_datetime datetime not null,
  comment varchar(255),
  stuff_id int(10) unsigned not null,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES patients (id),
  FOREIGN KEY (stuff_id) REFERENCES stuff (id)
);
create TABLE patients (
  id int(10) unsigned not null,
  first_name varchar(45) not null,
  middle_name varchar(45),
  last_name varchar(45) not null,
  birth_date date,
  PRIMARY KEY (id)
);
create TABLE record_details (
  id int(10) unsigned not null,
  record_id int(10) unsigned not null,
  description varchar(255) not null,
  results varchar(255),
  stuff_id int(10) unsigned not null,
  record_details_datetime datetime not null,
  PRIMARY KEY (id),
  FOREIGN KEY (record_id) REFERENCES patient_records (id),
  FOREIGN KEY (stuff_id) REFERENCES stuff (id)
);
create TABLE stuff (
  id int(10) unsigned not null,
  first_name varchar(45) not null,
  middle_name varchar(45),
  last_name varchar(45) not null,
  birth_date date,
  job_title varchar(50) not null,
  PRIMARY KEY (id)
);
create TABLE files (
  id int(10) unsigned not null,
  data longblob not null,
  comment varchar(255),
  file_name varchar(255) not null,
  record_id int(10) unsigned not null,
  content_type varchar(255) not null,
  PRIMARY KEY (id),
  FOREIGN KEY (record_id) REFERENCES patient_records (id)
);