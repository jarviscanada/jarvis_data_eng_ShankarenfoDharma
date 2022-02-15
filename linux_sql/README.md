# Introduction
This project is an introductory project teaching and demonstrating usage with using a docker container of PSQL, manipulation of bash script and PSQL queries and git. This project gets the PSQL container, which is used to store two tables of host_agent: host_info and host_usage. These tables store the hothe st(JRD)'s system information. Using crontab, the system will insert new records at 1 minute intervals. SQL Queries displays concise data and human-readable data.

# Quick Start
- Start a psql instance using psql_docker.sh
```
bash ~/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/scripts/psql_docker.sh start
```
- Export password for ease of use
```
export PGPASSWORD=password
```
- Create tables using ddl.sql
```
psql -h localhost -U postgres -d host_agent -f ~/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/sql/ddl.sql
```
- Insert hardware specs data into the DB using host_info.sh
```
bash /home/centos/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/scripts/host_info.sh localhost 5432 host_agent postgres password
```
- Insert hardware usage data into the DB using host_usage.sh
```
bash /home/centos/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password
```
- Crontab setup
```
contrab -e
* * * * * bash /home/centos/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```

# Implemenation
PSQL docker is required for this project's functionality, so it is provisioned and first and has to be started before any other operation. The docker script /scripts/psql_docker.sh can accept arguments 'create username password' to pull a PGPL image for this project, which once completed can use arguments 'start' or 'stop' to run or halt the container, allowing the use of pgpl commands in bash scripts and console.

Now that the PGPL is running, we create the database and tables. Since this is a fresh PGPL image, we need to go in and create the database first.
```
psql -h localhost -U postgres -W
postgres=# CREATE DATABASE host_agent;
```
Then, we can execute the sql script ddl.sql to create the two tables we need in this project. Note that these tables are empty.

We can then execute bash scripts host_info.sh and host_usage.sh to the the table with the user computer (host) data, which is then added into the PSQL data. The two scripts are similar in structure, but host_info *must* be run prior host_usage, as host_usafe will require a data in host_info to correctly execute. Both scripts uses bash commands such as lscpu, cat proc/meminfo and vmstat to gather information, before inserting it to host_agent database in their respective tables.

Finally, sql commands in queries.sql can be used to display useful data regarding these tables, primarily queries to:
1. Group hosts by cpu_number and total_memory
2. By intervals of 5 min, display used memory% of each host
3. By intervals of 5 min, display host-time intervals that do not have 3+ records (record operation error occurred)

## Architecture
![image](https://user-images.githubusercontent.com/59852656/153981097-20e3369d-b8a8-495e-a232-13095fbf1cb8.png)

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh: bash script to create/start/stop the psql docker container
```
bash psql_docker.sh create postgres password
bash psql_docker.sh start
bash psql_docker.sh stop
```
- host_info.sh: bash gathers host device info and inserts data into host_info psql
```
bash host_info.sh localhost 5432 host_agent postgres password
```
- host_usage.sh: bash gather host device info and inserts data into host_usage psql- Requires host_info to run at least once.
```
bash host_usage.sh localhost 5432 host_agent postgres password
```
- crontab: Not a script- cron will execute host_usage at intervals of 1 min, creating a log-system with psql host_usage.
```
crontab -e
* * * * * bash /home/centos/dev/jarvis_data_eng_ShankarenfoDharma/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password >  /tmp/host_usage
```
- queries.sql (describe what business problem you are trying to resolve)
```
psql -h localhost -p 5432 -d host_agent -U postgres -f sql/queries.sql
```
1. Displays all unique hosts that are recorded- sorted with number of cpu and total memory. For comparison and reference of statistics of hosts.
2. Displays the average % of memory used by each host, in intervals of 5 minutes. Displays rough performance estimates.
3. Displays intervals where there are records that are less than 3. Shows intervals where performance is not recorded- an error behavior.

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`

| Column  | Datatype | Explanation |
| --- | --- | --- |
| id | serial | primary key auto-incrementing id of hosts |
| hostname | varchar | Name of host device |
| cpu_number | smallint | Number of cpus device has |
| cpu_architecture | varchar | Software architecture of device |
| cpu_model | varchar | Cpu model name |
| cpu_mhz | real | Recorded cpu speed in Mhz |
| L2_cache | real | Capacity of L2 Cache in mb |
| total_mem | real | Total memory capacity in mb |
| timestamp | timestamp | time record is made |
- `host_usage`: Compound primary key in timestamp & host_id

| Column  | Datatype | Explanation |
| --- | --- | --- |
| timestamp | timestamp | time record is made | PK
| host_id | serial | Foreign key of which host is being recorded | PK
| memory_free | real | Mb of memory that is free |
| cpu_idle | real | Percentage of cpu that is idle |
| cpu_kernel | real | Percentage of cpu that is working as kernel |
| disk_io | integer | Number of i/o operations made |
| disk_available | real | Free disk space available for use |

# Test
SQL scripts are tested using DBeaver with sample records. SQL queries produces human-readable results, and SQL DDL creates the table schema described above in psql terminal.
Bash scripts are built in Intellij Idea, tested using terminals. Results are monitored with psql SELECT query, which does display the details of the machine.

# Deployment
Github is used as development versioning tool. Due to the scope of the project, only feature/develop branches are used. RCR/MCR will be conducted as required.

# Improvements
- Implement and test multiple host machines 
- A menu app/mainframe that can run all scripts in one go
- SQL queries for more data (average lifetime cpu usage, peak times and low times, etc.)
