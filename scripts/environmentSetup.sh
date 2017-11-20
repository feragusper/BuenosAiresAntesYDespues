#!/bin/bash

# Fix the CircleCI path
function getAndroidSDK(){
  export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH"

  DEPS="$ANDROID_HOME/installed-dependencies"

  if [ ! -e $DEPS ]; then
    cp -r /usr/local/android-sdk-linux $ANDROID_HOME &&
    echo y | android update sdk --no-ui --all --filter "tools"
    echo y | android update sdk --no-ui --all --filter "platform-tools"
    echo y | android update sdk --no-ui --all --filter "build-tools-25.0.0"
    echo y | android update sdk --no-ui --all --filter "android-26"
    echo y | android update sdk --no-ui --all --filter "extra-google-m2repository"
    echo y | android update sdk --no-ui --all --filter "extra-google-google_play_services"
    echo y | android update sdk --no-ui --all --filter "extra-android-support"
    echo no | android create avd -n testAVD -f -t android-26 --abi default/x86 &&
    touch $DEPS
  fi
}

function waitAVD {
    (
    local bootanim=""
    export PATH=$(dirname $(dirname $(which android)))/platform-tools:$PATH
    until [[ "$bootanim" =~ "stopped" ]]; do
      sleep 5
      bootanim=$(adb -e shell getprop init.svc.bootanim 2>&1)
      echo "emulator status=$bootanim"
    done
    )
}