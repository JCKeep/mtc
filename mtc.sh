#!/bin/bash

if [ $1 = "back" ] 
then
	str=`ps -ef | grep mtc-0.0.1-SNAPSHOT.jar`
	i=0
	for s in $str 
	do
		if [ $i -eq 1 ]
		then	
			kill $s
			break
		fi
		i=`expr $i + 1`
	done
	nohup java -jar mtc-0.0.1-SNAPSHOT.jar &
elif [ $1 = "mtc" ]
then
	rm -rf dist
	unzip dist.zip
	rm -rf /usr/local/nginx/html/file/assets /usr/local/nginx/html/file/index.html
	cp -r dist/assets /usr/local/nginx/html/file/assets
	cp -r dist/index.html /usr/local/nginx/html/file/index.html	
elif [ $1 = "admin" ]
then
	rm -rf dist
        unzip dist.zip
        rm -rf /usr/local/nginx/html/admin/assets /usr/local/nginx/html/admin/index.html
        cp -r dist/assets /usr/local/nginx/html/admin/assets
        cp -r dist/index.html /usr/local/nginx/html/admin/index.html
fi

