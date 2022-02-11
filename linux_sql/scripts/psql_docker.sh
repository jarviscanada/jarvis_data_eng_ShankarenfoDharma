#!/bin/bash

#Take 3 arguments (PSQL Command, username & pass[nullable])
cmnd=$1
user=$2
pass=$3

#fastif check if docker is running. || (IF NOT) start docker
sudo systemctl status docker  1> /dev/null || sudo systemctl start docker  1> /dev/null

#query container, save to status
docker container inspect jrvs-psql  1> /dev/null
cntnrstat=$?

#switch case on command
case $cmnd in
create)
  #ensure container does not exists
  if [ $cntnrstat -eq 0 ]; then
    echo "Container already exists"
    exit 1
  fi

  #create operation explicitly requires username and pass
  if [ $# -ne 3 ]; then
    echo "Please add username and password to command"
    exit 1
  fi

  #pass checks, create container & finish
  docker pull postgres 1> /dev/null
  docker volume create pgdata 1> /dev/null
  export PGPASSWORD='password'
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine  1> /dev/null
  exit $?
  ;;
start|stop)
  if [ $cntnrstat -ne 0 ]; then
      echo "Container does not exist."
      exit 1
  fi

  #execute command if container does exist
  docker container $cmnd jrvs-psql
  echo "Container $cmnd"
  exit $?
  ;;
*)
  echo "incorrect command"
  echo 'start | stop | create'
  exit 1
  ;;
esac
