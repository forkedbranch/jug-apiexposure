FROM jetty
ADD target/apiexposure.war /var/lib/jetty/webapps/ROOT.war

