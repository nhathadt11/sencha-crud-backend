# sencha-crud-backend

## Prerequisites

These following must be installed on your local machine:
1. Maven
2. Tomcat Server 7+

## Configuration

### Tomcat

1. Make sure that ```CATALINA_HOME``` variable is properly set in your system
2. Make these changes to ```$CATALINA_HOME\conf\tomcat-users.xml```
```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="password" roles="manager-gui, manager-script"/>
```
### Maven

1. There are two locations where the settings.xml file may be found:
   - The Maven install: ```${maven.home}/conf/settings.xml```
   - A user’s install: ```${user.home}/.m2/settings.xml```

2. Once you have found it add:
   ```
   <servers>
       ...
       <server>
           <id>TomcatServer</id>
           <username>admin</username>
           <password>password</password>
       </server>
       ...
   </servers>
   ``` 
## Deployment

### Database restore

Create database if it does not already exists:

```
mysqladmin -u <username> -p create <database_name>
```

Restore from backup:

```
mysql -u <username> -p <database_name> < book.bak
```

Make sure you have execution permission on these files:
```start.sh, build.sh, deploy.sh, redeploy.sh, undeploy.sh and stop.sh```
If not, run the following:
```
sudo chmod +x *.sh
```

### Starting

Start tomcat server:
  - Linux: ```./start.sh```
  - Windows: ```start.bat```

### Building

Open up settings.properties and change the following correspondingly:

```
db.jdbc.url=jdbc:mysql://localhost:3306/<database_name>
db.jdbc.username=<username>
db.jdbc.password=<password>
```

Clean and install entire project:
  - Linux: ```./build.sh```
  - Windows: ```build.bat```

### Deploying

Deploy the built app to configured tomcat server:
  - Linux: ```./deploy.sh```
  - Windows: ```deploy.bat```

### Stopping

Stop a running tomcat server:
  - Linux: ```./stop.sh```
  - Windows: ```stop.bat```
