# Config mysql in docker

```bash
docker run --name medConnect -e MYSQL_ROOT_PASSWORD=medConnect -e MYSQL_DATABASE=medConnect -e MYSQL_USER=meduser -e MYSQL_PASSWORD=medConnect -p 3306:3306 -d mysql/mysql-server:5.7
```

* MYSQL_ROOT_PASSWORD=medConnect -> root password
* MYSQL_DATABASE=medConnect -> database name
* MYSQL_USER=meduser -> username
* MYSQL_PASSWORD=medConnect -> user password
* -p 3306:3306 -> port mapping

```bash
docker exec -it medConnect mysql -u root -p
```