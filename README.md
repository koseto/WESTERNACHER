# Setup

Before you can build this project, you must install and configure the following dependencies on your machine.

[Java](https://adoptopenjdk.net/index.html?variant=openjdk11&jvmVariant=hotspot)

Download and install Java 11 using the appropriate link for your system. Add JAVA_HOME to your system environment variables (e.g., C:\Program Files\Java\jdk) and the Java bin directory to your path (C:\Program Files\Java\jdk\bin).

[MySQL](https://dev.mysql.com/downloads/mysql/5.7.html)

Download and install MySQL.

## Run the app!
You should run ./gradlew build for Linux(MacOS) or gradlew.bat build for Windows
Run gradle command: gradle bootRun.

## View API Endpoints with Swagger

You can use Swagger to directly call service API endpoints for troubleshooting and testing.

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)