---
created: 2024-08-26T10:00
updated: 2024-08-26T10:09
---
## Overview
Actias Livedev is a websocket based protocol for debugging Actias programs, it creates ephemeral revisions/bundles. A socket may have one revision active for one script ID at once, and this requires the `SCRIPT_WRITE` permission.

## Implementation
Gateway will live on `actias-api` service which will push to `actias-script-service` which will handle management/storage of all livedev sessions in Redis map for the script with session IDs. There should be a way to fetch this through the gRPC spec for `script-service.proto`. The worker will look at the URL `http(s)://<h>`