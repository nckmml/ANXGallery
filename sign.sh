#!/usr/bin/env bash

# adjust $BUILDTOOLSPATH if needed
BUILDTOOLSPATH=/opt/android-sdk/build-tools/29.0.2

cp ./out/ANXGallery-Unsigned.apk ./out/ANXGallery-to-be-signed.apk
$BUILDTOOLSPATH/apksigner sign --key ../ANXMiuiPortTools/testkey.pk8 --cert ../ANXMiuiPortTools/testkey.x509.pem ./out/ANXGallery-to-be-signed.apk
mv ./out/ANXGallery-to-be-signed.apk ./out/ANXGallery-Unaligned.apk
$BUILDTOOLSPATH/zipalign -f 4  ./out/ANXGallery-Unaligned.apk ./out/ANXGallery.apk
