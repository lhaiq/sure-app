#!/usr/bin/python
import paramiko
import sys
import os
import os.path

ip = "121.40.106.216"
user = "root"
passwd = "Suretime8"

print "clean..."
os.system("gradle clean ")
print "build...."
os.system("gradle build -x test ")
print "unzip.."
os.system("unzip sure-webapp/build/libs/sure-webapp-0.0.1-SNAPSHOT.war -d sure-webapp/build/libs/")


ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(ip, 22, user, passwd, timeout=5)
stdin, stdout, stderr = ssh.exec_command("/root/sure/tomcat/bin/shutdown.sh")
print stdout.readlines()

sftp = ssh.open_sftp()

print "upload file"
sftp.put("sure-webapp/build/libs/WEB-INF/web.xml", "/root/sure/tomcat/webapps/ROOT/WEB-INF/web.xml")
sftp.put("sure-webapp/build/libs/WEB-INF/mvc-servlet.xml", "/root/sure/tomcat/webapps/ROOT/WEB-INF/mvc-servlet.xml")
sftp.put("sure-webapp/build/libs/WEB-INF/lib/sure-service-api-0.0.1-SNAPSHOT.jar"
         , "/root/sure/tomcat/webapps/ROOT/WEB-INF/lib/sure-service-api-0.0.1-SNAPSHOT.jar")
sftp.put("sure-webapp/build/libs/WEB-INF/lib/sure-service-0.0.1-SNAPSHOT.jar"
         , "/root/sure/tomcat/webapps/ROOT/WEB-INF/lib/sure-service-0.0.1-SNAPSHOT.jar")



print "upload class"

ssh.exec_command("rm -rf /root/sure/tomcat/webapps/ROOT/WEB-INF/classes/*")
os.system("scp -r sure-webapp/build/libs/WEB-INF/classes/* root@121.40.106.216:/root/sure/tomcat/webapps/ROOT/WEB-INF/classes/")


# restart
stdin, stdout, stderr = ssh.exec_command("/root/sure/tomcat/bin/startup.sh")
print stdout.readlines()
print stderr.readlines()
