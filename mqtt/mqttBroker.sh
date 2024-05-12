#!/bin/zsh

# Install Mosquitto MQTT Broker: brew install mosquitto
# Configure to be open. Add the following listener to config file:
# 
#	listener 1883 0.0.0.0
#	allow_anonymous true
#
# Install MQTTX Command-Line Tool:
#
#	curl -LO https://www.emqx.com/en/downloads/MQTTX/v1.9.1/mqttx-cli-macos-arm64
#	sudo install ./mqttx-cli-macos-arm64 /usr/local/bin/mqttx

# Launch local MQTT Broker.
/opt/homebrew/opt/mosquitto/sbin/mosquitto --verbose -c /opt/homebrew/etc/mosquitto/mosquitto.conf
