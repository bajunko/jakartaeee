FROM payara/server-full

# Setup configuration
USER payara
COPY ./target/postgresql-9.4.1212.jre6.jar /opt/payara41/glassfish/domains/domain1/lib
# COPY domain.xml /opt/payara41/glassfish/domains/domain1/config
