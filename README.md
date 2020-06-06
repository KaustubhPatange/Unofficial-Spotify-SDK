# Unofficial-Spotify-SDK

![CircleCI](https://img.shields.io/circleci/build/github/KaustubhPatange/Unofficial-Spotify-SDK/master)
![Maven Central](https://img.shields.io/maven-central/v/io.github.kaustubhpatange/spotifyapi)

The library provides a wrapper over **spotify-auth** api (which is a part of [official-sdk](https://github.com/spotify/android-sdk)) to handle _automatic authentication_ as well as to yield some certain routes provided by [web-api](https://developer.spotify.com/documentation/web-api/reference-beta/).

> _The library only includes spotify-auth library as a part of this sdk, for spotify-app-remote you need to add it manually._

> _The library uses OkHttp version 3.1.2 internally to make http calls, which makes the minimum support sdk to 16._

## Features

The library overcomes some limitation of official sdk, some of them are listed below.

- A built-in way to _refresh_ **access token**.
- Some Routes to directly make a call to api (eg: [TracksApi](https://developer.spotify.com/documentation/web-api/reference-beta/#category-tracks), [AlbumsApi](https://developer.spotify.com/documentation/web-api/reference-beta/#category-albums), etc).
- Automatic _generation_ of _access token_ when making a **routed call**.
- Automatic **refreshing** of _access token_ so you don't have to do it manually.
- Explicitly specify result callback _(does not depend on calling activity's onActivityResult(), thanks to [ActivityResultContract](https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts.StartActivityForResult))_.
- More...

## Download

Library is available on `mavenCentral()`. Read more in [docs](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started#adding-dependency).

```gradle
 implementation 'io.github.kaustubhpatange:spotifyapi:$version'
```

## Guides

Browse from the following topics,

- [Understanding](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Understanding)
  - [WorkFlow](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Understanding#workflow)
  - [Anatomy of Refresh Token](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Understanding#anatomy-of-refresh-token)
  - [Library workflow](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Understanding#library-workflow)
- [Getting Started](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started)
  - [Registering Application](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started#registering-application)
  - [Adding Dependency](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started#adding-dependency)
  - [Creating Spotify client](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started#creating-a-client)
  - [Making API calls](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Getting-Started#making-an-api-call)
- [More about library](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Extending-Library)
  - [Limitations](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Extending-Library#limitation)
  - [Understanding](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Extending-Library#understanding)
  - [Extending Library](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/Extending-Library#extending-library)

## Requirements

- AndroidX
- Min SDK 16+

## License

- [General Publication License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html)

```
Copyright 2020 Kaustubh Patange

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```
