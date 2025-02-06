# Name Detect Mod

## Overview
Name Detect is a client-side Minecraft mod that allows players to search for specific substrings within the names of players currently on the server. It provides a simple command to count and display the occurrences of a given text within player names.

## Features
- Searches through all player names on the current server.
- Uses the `/detectname {word}` command to find and count occurrences.
- Works entirely on the client-side, requiring no server modifications.

## Installation
### Requirements:
- **Minecraft 1.21.1**
- **Fabric Loader (0.15.7 or later)**
- **Fabric API (0.115.0+1.21.1)**

### Steps:
1. Download and install **Fabric Loader** from [FabricMC](https://fabricmc.net/use/).
2. Install **Fabric API** from [Modrinth](https://modrinth.com/mod/fabric-api) or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api).
3. Download the latest `name-detect-x.x.x.jar` from the [Releases](https://github.com/YOUR_GITHUB/name-detect/releases).
4. Move the `.jar` file into your `.minecraft/mods/` folder.
5. Launch Minecraft with the Fabric Loader profile and enjoy!

## Usage
### Command:
```
/detectname {word}
```
- Example: `/detectname Steve` will count all players whose names contain "Steve".

## Development
### Building from Source
1. Clone the repository:
   ```sh
   git clone https://github.com/cellfone/detectname.git
   cd namedetect-1.21.1
   ```
2. Setup the development environment:
   ```sh
   ./gradlew build
   ```
3. The built mod will be in `build/libs/`.

## Issues & Contributions
- If you find a bug or want to request a feature, open an issue on [GitHub Issues](https://github.com/YOUR_GITHUB/name-detect/issues).
- Contributions are welcome! Fork the repo and submit a pull request.

## License
This project is licensed under the **MIT License**. See the `LICENSE` file for details.

