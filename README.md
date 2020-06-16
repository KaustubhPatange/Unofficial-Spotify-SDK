# Unofficial-Spotify-SDK

![build](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/workflows/build/badge.svg)
![Maven Central](https://img.shields.io/maven-central/v/io.github.kaustubhpatange/spotifyapi)

The library provideps a wrapper over **spotify-auth** api (which is a part of [official-sdk](https://github.com/spotify/android-sdk)) to handle _automatic authentication_ as well as to yield some certain routes provided by [web-api](https://developer.spotify.com/documentation/web-api/reference-beta/).

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
- [More about library](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/More-about-library)
  - [Limitations](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/More-about-library#limitation)
  - [Understanding](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/More-about-library#understanding)
  - [Extending Library](https://github.com/KaustubhPatange/Unofficial-Spotify-SDK/wiki/More-about-library#extending-library)

## Requirements

- AndroidX
- Min SDK 16+

## License

- [The Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)

```
Copyright 2020 Kaustubh Patange

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
