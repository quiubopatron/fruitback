echo Sonar execution script

# Execute sonar
call "C:\Program Files\sonar-scanner-3.1.0.1141-windows\bin\sonar-scanner.bat" -DWORKSPACE=%CD%

pause
