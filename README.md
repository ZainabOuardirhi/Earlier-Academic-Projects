# Earlier Academic Projects

Selected coursework and project reconstructions from earlier studies. This is a historical archive, separate from my current research.

| Project | Area | Status |
| --- | --- | --- |
| [Landmark Classification](projects/landmark-classification/) | CNNs, transfer learning, Udacity course | Original course fork linked; completed implementation not verified |
| [Riddle Game](projects/riddle-game/) | Android, image recognition, speech | Modern demonstration **reconstructed** from a February 2020 report; **not** original code |
| [GeoEarthquake](projects/geoearthquake/) | Node-RED, USGS GeoJSON, email alerts | Modern demonstration **reconstructed** from a 2019–2020 report; **not** original flow |

## Provenance and limitations

The Riddle Game report describes an original Android app integrating IBM Watson Visual Recognition and Text to Speech. The included modern demonstration uses device text-to-speech and **manual image verification**, not Watson.

The GeoEarthquake report describes a Node-RED workflow for earthquake notifications. The included modern demonstration retrieves USGS data and formats notification messages. Email delivery is disabled pending configuration.

These examples are not exact restorations of code written in 2019–2020, and neither has been verified as production-ready. Original dated reports are not distributed in this repository; they can be presented separately as documentary evidence when needed.

## Safety and reuse

Do not commit API credentials, email passwords, private datasets, or unreviewed documents. The Android demonstration has not been compiled/tested in this package; Node-RED requires local configuration. Third-party starter assets remain credited to Udacity through the original fork.
