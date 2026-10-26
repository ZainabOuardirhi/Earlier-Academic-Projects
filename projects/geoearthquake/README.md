# GeoEarthquake: Node-RED reconstruction

Inspired by the 2019–2020 report. Requirements: Node-RED with the core HTTP, Function, Debug, and Inject nodes. Optional email requires `node-red-node-email` and SMTP configuration.

1. Open the Node-RED editor.
2. Menu → Import → select `flows.json`.
3. Deploy, then press the Inject node. The Debug sidebar previews the messages.
4. **Optional:** install the email node, set an SMTP server, credentials, and recipient, then enable the disabled email node. Do not commit secrets.

This project fetches the USGS 30-day M4.5+ GeoJSON feed, limits the demonstration to 20 messages, and formats each result for email. The 20-message limit and feed choice are practical reconstruction defaults, not claims about the original implementation.
