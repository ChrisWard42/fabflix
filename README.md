# Fabflix

Fabflix is a web application for searching, browsing, and purchasing movies. This is a class project for CS 122B at UC Irvine undertaken in Spring 2016. It was developed using the Java EE framework (Servlets, JSP, JSTL, EL, JDBC) along with various frontend technologies (HTML, CSS, JS, jQuery, AJAX, Bootstrap). Spring and Struts were not utilized due to project restrictions.

**Developed by:** Chris Ward, Stephen Castro, Benjamin You

## Installation

### Manual Installation

Download the ZIP file for the repository or clone the repository using:

```
git clone https://github.com/ChrisWard42/fabflix.git
```

Then navigate to the sources folder and recompile all of the sources:

```
cd [Root directory where Fabflix was extracted, contains README.md]
cd src/main/webapp/WEB-INF/sources
javac -cp ".:../lib/*" fabflix/beans/*.java fabflix/core/*.java fabflix/filters/*.java -d ../classes
```

Then go to the main webapp directory and package it into a WAR file with the desired application name:

```
cd ..
jar cvf ../[web_app_name].war .
```

For deployment with Tomcat, grab the WAR file generated and deploy it using the Tomcat Web Manager, or by manually placing the WAR file in the Tomcat webapps directory. Deployment to Jetty, GlassFish, or other Jave EE web servers is not currently supported, but an experienced developer should be able to make the application run on those platforms.

### Gradle Installation

Coming Soon.


## Documents

The following documents are provided which were used in the development of the application:

**Design** (/docs/Design.txt)  
> Explains the overall visual and functional design of the application including interactions. Primarily used during Project 2 and may not contain all details of future project specifications.
	
**Technologies** (/docs/Technologies.txt)  
> Lists helpful frontend and backend libraries and resources we reviewed during development.
	
**Workflow Guidelines** (/docs/Workflow.txt)  
> The guidelines we used for handling

## License

Our implementation of Fabflix is available for **reference only**.

The application should not be used in part or in whole on production websites. Furthermore, future CS 122B students viewing this repository are encouraged to review the code plagiarism guidelines before considering taking a shortcut by using any of our implementation directly.