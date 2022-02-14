#!/bin/bash
#Gather host info data and save into psql
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
lscpu_out=`lscpu`
meminfo=$(cat /proc/meminfo)
#get desired details
host_name=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
temp=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)
l2_cache="${temp::-1}"
total_mem=$(echo "$meminfo" | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | tail -n1 | awk '{print $18" "$19}')

#PSQL
export PGPASSWORD=$psql_password
#insert into host_info table
insert_stmt="INSERT INTO host_info (hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,total_mem,"timestamp")
             VALUES ('$host_name','$cpu_number','$cpu_architecture','$cpu_model','$cpu_mhz','$l2_cache','$total_mem','$timestamp');"
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?