#!/usr/bin/env python3
"""
Publish a signed .aab to Google Play via the Play Developer API.

Usage:
    python3 scripts/publish_play.py \
        --aab app/build/outputs/bundle/release/app-release.aab \
        --credentials buildsystem/playstore_deploy_keys.json \
        --package com.feragusper.buenosairesantesydespues \
        --track internal \
        --status draft

`--status draft` uploads without submitting for review (you finish it in the
console). `--status completed` submits/rolls out on that track.
"""
import argparse
import sys

from google.oauth2 import service_account
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError
from googleapiclient.http import MediaFileUpload

SCOPE = "https://www.googleapis.com/auth/androidpublisher"


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--aab", required=True)
    ap.add_argument("--credentials", required=True)
    ap.add_argument("--package", required=True)
    ap.add_argument("--track", default="internal")
    ap.add_argument("--status", default="draft", choices=["draft", "completed", "inProgress"])
    ap.add_argument("--notes", default="Modernización completa: Kotlin, Compose, target API 36.")
    args = ap.parse_args()

    creds = service_account.Credentials.from_service_account_file(
        args.credentials, scopes=[SCOPE]
    )
    android = build("androidpublisher", "v3", credentials=creds, cache_discovery=False)
    edits = android.edits()

    print(f"Creating edit for {args.package} ...")
    edit_id = edits.insert(packageName=args.package, body={}).execute()["id"]

    is_aab = args.aab.lower().endswith(".aab")
    kind = "bundle" if is_aab else "APK"
    print(f"Uploading {kind} {args.aab} ...")
    media = MediaFileUpload(args.aab, mimetype="application/octet-stream", resumable=True)
    if is_aab:
        uploaded = edits.bundles().upload(
            packageName=args.package, editId=edit_id, media_body=media
        ).execute()
    else:
        uploaded = edits.apks().upload(
            packageName=args.package, editId=edit_id, media_body=media
        ).execute()
    version_code = uploaded["versionCode"]
    print(f"Uploaded versionCode {version_code}")

    print(f"Assigning to track '{args.track}' as '{args.status}' ...")
    release = {"versionCodes": [str(version_code)], "status": args.status}
    if args.notes:
        release["releaseNotes"] = [{"language": "es-419", "text": args.notes}]
    edits.tracks().update(
        packageName=args.package,
        editId=edit_id,
        track=args.track,
        body={"releases": [release]},
    ).execute()

    print("Committing edit (changes staged, not auto-sent for review) ...")
    edits.commit(
        packageName=args.package, editId=edit_id, changesNotSentForReview=True
    ).execute()
    print(f"✅ Done: versionCode {version_code} staged on '{args.track}' ({args.status}).")
    print("   Finish + send for review from the Play Console.")
    return 0


if __name__ == "__main__":
    try:
        sys.exit(main())
    except HttpError as e:
        print(f"❌ Play API error: {e.status_code} {e.reason}", file=sys.stderr)
        print(e.content.decode() if hasattr(e, "content") else str(e), file=sys.stderr)
        sys.exit(2)
