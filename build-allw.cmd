cd common
cmd /C mvn clean install
cd ..\preprocessor
cmd /C mvn clean install
cd ..
cmd /C gradlew build install
