# sencha-crud-backend

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

Make sure you have execution permission on these files:
```start.sh, build.sh, deploy.sh, redeploy.sh and undeploy.sh```
If not, run the following:
```
sudo chmod +x *.sh
```

### Starting

Start tomcat server:
  - Linux: ```./start.sh```
  - Windows: ```start.bat```

### Building

This will do a clean install of the entire project:
  - Linux: ```./build.sh```
  - Windows: ```build.bat```

### Deploying

This will deploy the built app to configured tomcat server:
  - Linux: ```./deploy.sh```
  - Windows: ```deploy.bat```
