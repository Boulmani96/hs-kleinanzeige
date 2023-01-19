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

## gitlab Teammitgliedern:

Ouassime Boulmani 767834 <br />
El-houssine Taibi 764676 <br />
Mohaned Mgaidi  767583 <br />
Mohammad Al Msalmeh  767411

### gitlab
- Workflow
    1. Issue nehmen
    2. Branch erstellen
    3. Feature mit Tests implementieren und dabei nach jeder Implementationseinheit pushen
    4. Code aufräumen, Code inspection etc.
    5. Merge-Request stellen (Template benutzen und Draft: in den Titel schreiben)
       Branch wird vom Git-Head nochmal überprüft und sobald alles in Ordnung ist gemerged
    6. Falls etwas auffällt, wird dies am Code oder in einem Kommentar in der Merge Request vermekt
- Sprache
    - gitlab-Kommentare, Programmcode komplett in Englisch
    - Issues, Kommentare dort in Deutsch oder Englisch

### Code Review
Ziele eines Codereviews
- Code verbessern
- voneinander lernen
- best practices umsetzen
- Fehler vermeiden
- Konsistenz im gesamten Projekt
