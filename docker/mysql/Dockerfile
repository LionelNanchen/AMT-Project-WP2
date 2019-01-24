FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD}
CMD ["--default-authentication-plugin=mysql_native_password"]

ADD data/0-initdb.sql /docker-entrypoint-initdb.d/