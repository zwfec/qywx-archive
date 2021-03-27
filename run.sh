javac -cp .:lib/fastjson-1.2.75.jar:lib/lombok.jar com/tencent/entity/Qychat.java
javac -cp .:lib/fastjson-1.2.75.jar:lib/lombok.jar com/tencent/entity/ChatDatas.java
javac -cp .:lib/fastjson-1.2.75.jar:lib/lombok.jar sdkdemo.java
java -cp .:lib/fastjson-1.2.75.jar:lib/lombok.jar sdkdemo 1 140 10 "" "" 90
