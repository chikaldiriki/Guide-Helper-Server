How to run database in docker

```bash
 sudo docker build -t guide-helper-db .
 sudo docker run --name app-db -p 3307:3306 -d guide-helper-db
 sudo docker start app-db
 sudo docker exec -it app-db /bin/bash
```

Use ```restartDB.sh``` script from database directory to restart database. Remember to change Dockerfile before running this script. 
