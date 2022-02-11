--go to host_agent database
\c host_agent;

--create table host_info
CREATE TABLE IF NOT EXISTS PUBLIC.host_info (
	id               SERIAL NOT NULL PRIMARY KEY,
	hostname         VARCHAR NOT NULL UNIQUE,
	cpu_number		 SMALLINT NOT NULL,
	cpu_architecture VARCHAR NOT NULL,
	cpu_model		 VARCHAR NOT NULL,
	cpu_mhz 		 REAL NOT NULL,
	L2_cache		 REAL NOT NULL,
	total_mem		 REAL NOT NULL,
	timestamp		 TIMESTAMP NOT NULL
);

--create table host_usage
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage (
     "timestamp"      TIMESTAMP NOT NULL,
     id               SERIAL NOT NULL PRIMARY KEY,
     hostname         VARCHAR NOT NULL UNIQUE,
     memory_free      REAL NOT NULL,
     cpu_idle         REAL NOT NULL CHECK (cpu_idle <= 100),
     cpu_kernel       REAL NOT NULL CHECK (cpu_kernel <= 100),
     disk_io          INTEGER NOT NULL,
     disk_available   REAL NOT NULL
);