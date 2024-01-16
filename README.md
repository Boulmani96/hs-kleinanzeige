# HS Kleinanzeigen
Die Anwendung erlaubt es, Kleinanzeigen zu verwalten. Es ist möglich nach Anzeigen zu suchen oder 
selbst einzustellen.

* JDK 17
* Maven
* Docker
* Redis
* docker run --name=mysql -p 4406:3306 -e MYSQL_ROOT_PASSWORD=start01 -e MYSQL_DATABASE=KLEINANZEIGEN -d mysql:8.0.22 <br />
* docker run --name=hs-kleinanzeigen-cache -p 127.0.0.1:6379:6379 -d redis:6.2.6
* To connect to database: mysql -uroot -pstart01 -h 127.0.0.1 -P4406
***********************************************************************************************************************