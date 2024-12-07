import {ref, Ref} from "vue";

class UpgradeInfo {
    from: string
    to: string
}

export const upgradeInfos: UpgradeInfo[] = [
    // {from: '6.5.2', to: '7.0.0'},
    // {from: '7.0.0', to: '8.0.0'},
    // {from: '8.0.0', to: '8.1.0'},
    // {from: '8.3.1', to: '8.4.0'},
    // {from: '8.5.0', to: '8.5.1'},
    // {from: '8.5.1', to: '8.6.0'},
    {from: '8.6.0', to: '8.7.0'},
    {from: '8.8.0', to: '9.0.0'},
    {from: '9.0.0', to: '9.0.1'},
    {from: '9.0.1', to: '9.0.2'},
    {from: '9.0.3', to: '9.1.0'},
    {from: '9.2.0', to: '9.3.0'},
]

export const keyVersions = Array.from(new Set(upgradeInfos.map(info => [info.from, info.to]).flat()));

export function getVersionsBefore(version: string) {
    const index = keyVersions.indexOf(version)
    if (index === -1) {
        return []
    }
    return keyVersions.slice(0, index)
}

export function getVersionsAfter(version: string) {
    const index = keyVersions.indexOf(version)
    if (index === -1) {
        return []
    }
    return keyVersions.slice(index + 1)
}

export function getDefaultUpgradeInfo() {
    return upgradeInfos[upgradeInfos.length - 1]
}

export function getChanges(info: UpgradeInfo) {
    const result = [];
    let put = false;
    upgradeInfos.forEach(upgradeInfo => {
        if (info.from === upgradeInfo.from) {
            put = info.from !== upgradeInfo.from || info.to !== upgradeInfo.to;
            result.push(upgradeInfo);
        } else if (info.from === upgradeInfo.to) {
            put = true;
        } else if (info.to === upgradeInfo.from) {
            put = false;
        } else if (info.to === upgradeInfo.to) {
            put = false;
            result.push(upgradeInfo);
        } else if (put) {
            result.push(upgradeInfo);
        }
    });
    return result;
}

export const nowUpgradeInfo: Ref<UpgradeInfo> = ref(getDefaultUpgradeInfo());
export const nowChanges: Ref<UpgradeInfo[]> = ref([]);