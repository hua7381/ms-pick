# ms-recognize
微服务-识别与要素提取

# ms-recognize.xml
<configuration>
  <id>java-ms-recognize</id>
  <name>java-ms-recognize</name>
  <description>java-ms-recognize</description>
  <executable>java</executable>
  <arguments>-Dfile.encoding=utf-8 -Xms300m -Xmx400m -jar ../jar/ms-recognize-1.0.jar</arguments>
  <logpath>%BASE%\log</logpath>
  <logmode>rotate</logmode>
</configuration>

# update
```
cd c:/zgh/code/ms-recognize
git pull
git pull
c:/apps/java-services/ms-recognize stop
mvn clean
mvn install
copy "ms-recognize-1.0.jar" "c:/apps/jar/"
Yes
c:/apps/java-services/ms-recognize start
```
