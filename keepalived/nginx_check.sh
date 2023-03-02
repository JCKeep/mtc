#!/bin/bash

NGINX_PROCESS=`ps -C nginx | grep nginx | wc -l`

if [ $NGINX_PROCESS -eq 0 ]
then
    echo "nginx error at " `date` >> /etc/keepalived/logs/error.log
    /usr/local/nginx/objs/nginx
    sleep 2
    NGINX_PROCESS_RESTART=`ps -C nginx | grep nginx | wc -l`
    if [ $NGINX_PROCESS_RESTART -eq 0 ]
    then
        echo "nginx down at " `date` >> /etc/keepalived/logs/error.log
        systemctl stop keepalived
        # if not support systemctl, use
        # /etc/init.d/keepalived stop
    fi
fi
