# RestServiceSpringBoot


-- No linux
openssl req -newkey rsa:2048 -x509 -keyout /home/allan/ssl/tomcat/cakey.pem -out /home/allan/ssl/tomcat/cacert.pem -days 3650

openssl pkcs12 -export -in /home/allan/ssl/tomcat/cacert.pem -inkey /home/allan/ssl/tomcat/cakey.pem -out /home/allan/ssl/tomcat/identity.p12 -name "tomcat"

keytool -import -file /home/allan/ssl/tomcat/cacert.pem -keystore /home/allan/ssl/tomcat/trust.jks -storepass changeit


-- Tomcat
<Connector port="8443"
			maxHttpHeaderSize="8192" SSLEnabled="true"
			maxThreads="150" minSpareThreads="25"
			enableLookups="false" disableUploadTimeout="true"
			acceptCount="100" scheme="https" secure="true"
			clientAuth="false" sslProtocol="TLS" useBodyEncodingForURI="true"
			keystoreFile="/home/allan/ssl/tomcat/identity.p12"
			keystorePass="changeit" keystoreType="PKCS12" keyAlias="tomcat" />