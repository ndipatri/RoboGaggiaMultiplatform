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

# So we close the MQTT client when this script exits.
trap "kill 0" EXIT

# Launch local MQTT Broker.
/opt/homebrew/opt/mosquitto/sbin/mosquitto -c /opt/homebrew/etc/mosquitto/mosquitto.conf &

# Connect Local MQTT client.
mqttx conn -h localhost -p 1883 &

# Launch RoboGaggia Video of a shot being pulled....
# The start time is calibrated with teh telemetry data so the extraction weight (yellow line) starts
# to grow the moment liquid is shown to fall into the cup in the video.
# God sick of this video!
open -a /Applications/VLC.app/Contents/MacOS/VLC './shotExtraction.mov' --args --start-time 0000 | at now + 0 second

# Need to give client a moment to connect...
sleep 3

# Push fake Telemetry
input="./telemetry.csv"
while IFS= read -r line
do
     mqttx pub -t 'ndipatri/feeds/robogaggiatelemetry' -h localhost -p 1883 -m "$line"
     sleep 1.2
done < "$input"
