echo "Copying licenses"
mkdir "${ANDROID_HOME}/licenses" || true
cp licenses/* $ANDROID_SDK_HOME/licenses/
