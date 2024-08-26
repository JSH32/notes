---
created: 2024-08-26T10:00
updated: 2024-08-26T10:17
---
## Overview
Actias Livedev is a websocket based protocol for debugging Actias programs, it creates ephemeral revisions/bundles. A socket may have one revision active for one script ID at once, and this requires the `SCRIPT_WRITE` permission.

## Implementation
Gateway will live on `actias-api` service which will push to `actias-script-service` which will handle management/storage of all livedev sessions in Redis map for the script with session IDs. There should be a way to fetch this through the gRPC spec for `script-service.proto`. The worker will look at the URL which will be similar to `http(s)://<hostname>/livedev/<sessionId>`, the routing within the Lua worker will omit this as a prefix in the request path since its irrelevant.

### TODO
- [ ] Actias API
	- [ ] Gateway which pushes to Script Service and manages creation/deletion of sessions.
- [ ] Script Service
	- [ ] Proto spec to introduce livedev session creation/deletion/querying
	- [ ] Backing store for livedev sessions using Redis
		- [ ] Make sure to handle what happens on creation/deletion of project IDs or script IDs as a whole.
- [ ] Worker
	- [ ] Respond to URL similar to `http(s)://<hostname>/livedev/<sessionId>` which should fetch from livedev gRPC calls instead of regular script ID latest revision.
		- [ ] The routing within the Lua worker will omit this as a prefix in the request path since its irrelevant.