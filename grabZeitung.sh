#!/bin/bash
#Copy ~/.gdfuse to /root for cron
#chmod 777 /root/.gdfuse
#chmod a+x /root

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#sudo docker run  --net=host -p 4444:4444 -v /media/gruft/Daten/Zeitung/Download:/home/seluser:rw -d -P selenium/standalone-chrome
echo "Lade Zeitungen..."
java -jar $DIR/downloadZeitung.jar
echo "Fertig mit Zeitungsdownload"
#Java.grab Papers
echo "Mounting Google Drive"
google-drive-ocamlfuse /mnt/gdrive 
if mount | grep /mnt/gdrive > /dev/null; then
	echo "Google Drive Mounted"
	echo "Kopiere Zeitungen"
	mkdir  -p /mnt/gdrive/Zeitungen/`date --rfc-3339=date`
	mkdir  -p /media/gruft/Daten/Zeitung/`date --rfc-3339=date`
	echo "Lokale Kopie"
	cp -v -f /media/gruft/Daten/Zeitung/Download/Downloads/*.pdf /media/gruft/Daten/Zeitung/`date --rfc-3339=date`
	echo "Google Drive"
	mv -v -f /media/gruft/Daten/Zeitung/Download/Downloads/*.pdf /mnt/gdrive/Zeitungen/`date --rfc-3339=date`
	fusermount -u /mnt/gdrive
	echo "Fertig mit kopieren"
	exit 0
else
	echo "Error: Google Drive not Mounted"
	exit 1
fi




