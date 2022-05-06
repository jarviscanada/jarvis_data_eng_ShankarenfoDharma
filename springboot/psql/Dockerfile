FROM postgres:9.6-alpine
ENV POSTGRES_PASSWORD password
ENV POSTGRES_USER postgres 
ENV POSTGRES_DB jrvstrading
COPY sql_ddl/schema.sql /docker-entrypoint-initdb.d/
