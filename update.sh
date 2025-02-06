#!/bin/sh
echo "Old version:"
read -r oldVer
echo "New version:"
read -r newVer

# Update CommandAPI dependency versions
sed -i "s/dev-jorel-commandapi-annotations = \"$oldVer\"/dev-jorel-commandapi-annotations = \"$newVer\"/" reference-code/gradle/libs.versions.toml
sed -i "s/dev-jorel-commandapi-bukkit-core = \"$oldVer\"/dev-jorel-commandapi-bukkit-core = \"$newVer\"/" reference-code/gradle/libs.versions.toml
sed -i "s/dev-jorel-commandapi-bukkit-kotlin = \"$oldVer\"/dev-jorel-commandapi-bukkit-kotlin = \"$newVer\"/" reference-code/gradle/libs.versions.toml
sed -i "s/dev-jorel-commandapi-bukkit-test-toolkit = \"$oldVer\"/dev-jorel-commandapi-bukkit-test-toolkit = \"$newVer\"/" reference-code/gradle/libs.versions.toml

# Update CommandAPI version in documentation files (Maven)
sed -i "s/<version>$oldVer<\/version>/<version>$newVer<\/version>/" docs/en/dev-setup/annotations.md
sed -i "s/<version>$oldVer<\/version>/<version>$newVer<\/version>/" docs/en/dev-setup/setup.md
sed -i "s/<version>$oldVer<\/version>/<version>$newVer<\/version>/" docs/en/dev-setup/shading.md
sed -i "s/<version>$oldVer<\/version>/<version>$newVer<\/version>/" docs/en/kotlin-dsl/intro.md
sed -i "s/<version>$oldVer<\/version>/<version>$newVer<\/version>/" docs/en/test/setup.md

# Update CommandAPI version in documentation files (Gradle)
sed -i "s/dev.jorel:commandapi-annotations:$oldVer/dev.jorel:commandapi-annotations:$newVer/" docs/en/dev-setup/annotations.md
sed -i "s/dev.jorel:commandapi-bukkit-core:$oldVer/dev.jorel:commandapi-bukkit-core:$newVer/" docs/en/dev-setup/setup.md
sed -i "s/dev.jorel:commandapi-bukkit-shade:$oldVer/dev.jorel:commandapi-bukkit-shade:$newVer/" docs/en/dev-setup/shading.md
sed -i "s/dev.jorel:commandapi-bukkit-shade-mojang-mapped:$oldVer/dev.jorel:commandapi-bukkit-shade-mojang-mapped:$newVer/" docs/en/dev-setup/shading.md
sed -i "s/dev.jorel:commandapi-bukkit-kotlin:$oldVer/dev.jorel:commandapi-bukkit-kotlin:$newVer/" docs/en/kotlin-dsl/intro.md
sed -i "s/dev.jorel:commandapi-bukkit-test-toolkit:$oldVer/dev.jorel:commandapi-bukkit-test-toolkit:$newVer/" docs/en/test/setup.md
sed -i "s/dev.jorel:commandapi-bukkit-core:$oldVer/dev.jorel:commandapi-bukkit-core:$newVer/" docs/en/test/setup.md

# Possible manual updates
echo "These may have to be updated manually:"
echo "  docs/en/velocity/intro.md"
echo "  reference-code/gradle/libs.versions.toml -> dev-jorel-commandapi-velocity-shade version"
echo "  docs/public/versions.yml"