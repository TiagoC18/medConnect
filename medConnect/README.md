# Config mysql in docker

```bash
docker run --name mysql3 -e MYSQL_ROOT_PASSWORD=medConnect -e MYSQL_DATABASE=medConnect -e MYSQL_USER=meduser -e MYSQL_PASSWORD=medConnect -p 9090:3366 -d mysql/mysql-server:5.7
```

* MYSQL_ROOT_PASSWORD=medConnect -> root password
* MYSQL_DATABASE=medConnect -> database name
* MYSQL_USER=meduser -> username
* MYSQL_PASSWORD=medConnect -> user password
* -p 9090:3366 -> port mapping

```bash
docker exec -it mysql3 mysql -u root -p
```