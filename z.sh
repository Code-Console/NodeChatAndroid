clear
clear
clear
echo "=============Start=============="
adb uninstall com.robotics.nodechat
adb install ./app/build/outputs/apk/debug/app-debug.apk
adb logcat -c 
adb shell am start -n com.robotics.nodechat/.MainActivity
adb logcat -s YOGE
#adb logcat System.out:I *:S
echo "=========Complete============="


#keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
#keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 900000
