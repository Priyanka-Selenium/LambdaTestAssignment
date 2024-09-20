FROM gitpod/workspace-full:latest

# Install Maven and JDK 17 (if not present)
USER root
RUN apt-get update && \
    apt-get install -y maven openjdk-17-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install browsers for Selenium testing
RUN apt-get update && \
    apt-get install -y firefox-esr chromium-browser

# Set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Switch back to the default user
USER gitpod
