#!/bin/bash
#gather host_usage data, send to PSQL
#NEEDS PSQL CONTAINER TO RUN PRIOR!- "./scripts/psql_docker.sh start"
#Arguments psql_host psql_port db_name psql_user psql_password
#          "localhost" 5432 "host_agent" "postgres" "password"
#argument check
if [ $# -ne 5 ]; then
  echo "Argument mismatch: require [psql_host psql_port db_name psql_user psql_password]"
  exit 1
fi

#assign argw to var
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#get raw data
hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)
#get detailed data vars
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{print $10}' | tail -n1 | xargs)
temp=$(df -BM | awk {'print $4'}  | head -n2 | tail -n1 | xargs)
disk_available="${temp::-1}"
timestamp=$(vmstat -t | tail -n1 | awk '{print $18" "$19}')

#PSQL
export PGPASSWORD=$psql_password
#get the ID from host_id
query_host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";
#PSQL Commands get id | trim query shell
host_id=$(psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$query_host_id" | tail -n3 | head -n1 | xargs)
query_insert="INSERT INTO host_usage ("timestamp",host_id, memory_free, cpu_idle, cpu_kernel, disk_io,disk_available)
              VALUES  ('$timestamp', '$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$query_insert"
exit $?