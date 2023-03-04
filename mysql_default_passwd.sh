#!/bin/bash

DEFAULT_USER=`cat /etc/mysql/debian.cnf | grep "user" | sort -u | awk -F ' ' '{print $3}'`
DEFAULT_PASSWORD=`cat /etc/mysql/debian.cnf | grep "password" | sort -u | awk -F ' ' '{print $3}'`

echo "USER: " $DEFAULT_USER
echo "PASSWORD: " $DEFAULT_PASSWORD
