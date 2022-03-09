--go to host_agent database
\c host_agent;

--Group hosts by cpu_number, sor by total_memory in descending order
SELECT cpu_number, id, total_mem FROM host_info
ORDER BY cpu_number ASC,
         total_mem DESC;

--By intervals of 5 min, display used memory% of each host
CREATE FUNCTION intrvl5min(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

SELECT host_id , hostname, intrvl5min(host_usage.timestamp) AS "R_timestamp" , to_char( AVG(((total_mem/1000)-memory_free)/(total_mem/1000) * 100), 'FM99') AS "used_mem_%"
FROM host_usage, host_info
WHERE host_id = id
GROUP BY host_id, hostname, "R_timestamp"
ORDER BY host_id;

--By intervals of 5 min, display host-time intervals that do not have 3+ records
SELECT host_id, intrvl5min(host_usage.timestamp) AS "R_timestamp", COUNT(timestamp) AS "records"
FROM host_usage
GROUP BY host_id, "R_timestamp"
HAVING COUNT(timestamp) < 3;
