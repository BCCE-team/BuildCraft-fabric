# BuildCraft Community Edition — Minecraft 26.3 Fabric (materialized)

Standalone source export from the provided `fabric-26.3-restoration` archive.

- Target: **26.3-fabric**
- Minecraft: **26.3**
- Fabric Loader: **0.19.5**
- Fabric API: **0.163.0+26.3**
- TeamReborn Energy API: **5.0.0**
- Java: **25**
- Gradle wrapper: **9.8.1**
- Fabric Loom: **1.18-SNAPSHOT**, native 26.3 build without remapping
- Mod version: **8.0.29+26.3+fabric**

All source layers have already been resolved, version conditions preprocessed,
compile exclusions applied, target metadata generated, and Fabric resource
placeholders expanded. This folder does not depend on the original Stonecutter
multi-target repository, `build-config`, `build-logic` or the Python materializer.

## Gradle tasks

Windows:

```powershell
.\gradlew.bat compileLibContractsJava
.\gradlew.bat compileJava
.\gradlew.bat build
```

Linux/macOS:

```bash
./gradlew compileLibContractsJava
./gradlew compileJava
./gradlew build
```

## IMPORTANT — work in progress

Materialization is complete, **Fabric port and Java compilation are not**.
The uploaded branch explicitly says this is a `lib` restoration stage, not a
playable mod. The current materialized `buildcraft/lib` still contains
**50 source files with NeoForge/Forge imports**.
These are intentionally retained, not stubbed or silently removed. The original
fail-closed `verifyFabric263LibIsolation` Gradle task is preserved to prevent
accidental packaging before loader isolation is complete.

No Java 25 compile, GameTest, smoke or parity validation was performed in this
export environment. The included Python source checks did pass; they are not
runtime validation. See `reports/fabric263-lib-import-ledger.json` and
`FABRIC_26_3_RESTORATION.md` for implementation status and blockers.

External `BCCE-team/Localizations` translations are not bundled, as they are
maintained outside of the uploaded source repository.
