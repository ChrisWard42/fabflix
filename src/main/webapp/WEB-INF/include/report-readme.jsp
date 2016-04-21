<tr>
    <td style="padding: 20px">
        <h3 style="text-align: center">Readme</h3><br><br>

        <h4>Requirements</h4><br>

        <span style="font-weight:bold">Tomcat 8 or higher:</span> If running on Tomcat 7 or below, you will need to go into WEB-INF/web.xml and change the web-app version descriptor to whatever version is supported by that Tomcat instance. Refer to these links: <a href="http://tomcat.apache.org/whichversion.html">Apache Tomcat Versions</a> and <a href="http://www.mkyong.com/web-development/the-web-xml-deployment-descriptor-examples/">Web.xml Deployment Descriptor Examples</a><br><br>
        
        <span style="font-weight:bold">Java 8 JRE or higher:</span> The code might compile under Java 7 but definitely not Java 6 or lower since there are a couple places we use the try-with-resources statement introduced in Java 7.<br><br>

        <h4>Quick Installation</h4><br>

        The WAR file "project2_07.war" included with our submission should be sufficient to
        run the project on a Tomcat server which meets the above requirements. If such a server is not available,
        feel free to install the project to a different directory under webapps on our AWS instance. The webapp
        names we currently deploy to which shouldn't be used are: live_site, chris, stevo, benla.<br><br>

        <span style="font-weight:bold">Tomcat webapps directory on our AWS instance:</span> /opt/tomcat/webapps<br><br>

        If deploying the WAR file as-is doesn't work and the sources need to be recompiled there are two ways to
        accomplish this, the recommended way that we've utilized throughout development, and a kludgy way we
        made for this readme but have only tested twice.<br><br>

        <h4>Automatic Installation with Gradle (Recommended)</h4><br>

        We're utilizing <a href="http://gradle.org/">Gradle</a> as our build and deploy system for the projects.
        In order to utilize our Gradle build scripts, download the files at the following link:<br><br>

        <span style="font-weight:bold"><a href="http://50.18.81.254/chris/resources/gradle/gradle.zip">Download Gradle Build Files</a></span> (<span style="font-weight:bold"><a href="#">Mirror</a></span>)<br><br>

        Extract the ZIP file to any directory, and extract the submitted WAR file as well, deleting all of the *.class files in WEB-INF/classes if desired. Then place all of the contents of the WAR file (META-INF folder, WEB-INF folder, resources folder, error.html) in the folder:<br><br>

        [folder with build.gradle]/src/main/webapp<br><br>

        Now navigate to the folder with build.gradle in it and run the command: ./gradlew -PwAN=your_app_name assemble<br><br>

        This will compile all of the files in the relative 'sources' directory using the JAR files in the 'libs' directory into the 'classes' directory, then create a WAR file in the following location:<br><br>

        [folder with build.gradle]/build/libs/your_app_name.war<br><br>

        Take that WAR file and deploy it to a Tomcat server using the Tomcat Web Manager or by manually placing it in the webapps 
        directory (assuming unpackWARs and autoDeploy are true in its server.xml, which is the case by default).<br><br>

        The build scipt also enables remote deployment but requires some configuration. The server being deployed to must have a 
        Tomcat user with "manager-script" permissions defined in tomcat-users.xml like so:<br><br>

        <code>&lt;user username="scriptuser" password="somesecurepassword" roles="manager-script" /&gt;</code><br><br>

        Then the 'cargo':'remote' section of build.gradle starting at Line 98 should be updated to contain the hostname of the
        server to be deployed to and the username / password combo for the tomcat user specified above. Once that's done you should be able to run the following command to compile, build, package, deploy, and remove the intermediary WAR:<br><br>

        ./gradlew -PwAN=your_app_name wAD<br><br>

        -or-<br><br>

        ./gradlew -PwAN=your_app_name assemble<br>
        ./gradlew -PwAN=your_app_name cDR<br>
        ./gradlew -PwAN=your_app_name cW<br><br>

        <h4>Manual Installation with Java Command Line</h4><br>

        Coming Soon
    </td>
</tr>