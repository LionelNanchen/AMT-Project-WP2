FROM mysql:5.7.24

ENV MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD}

ADD data/0-initdb.sql /docker-entrypoint-initdb.d/