import {ref, Ref} from "vue";

class UpgradingInfo {
    from: string
    to: string
}

export const upgradingInfos: UpgradingInfo[] = [
    {from: '6.5.2', to: '7.0.0'},
    {from: '7.0.0', to: '8.0.0'},
    {from: '8.0.0', to: '8.1.0'},
    {from: '8.3.1', to: '8.4.0'},
    {from: '8.5.0', to: '8.5.1'},
    {from: '8.5.1', to: '8.6.0'},
    {from: '8.6.0', to: '8.7.0'},
    {from: '8.8.0', to: '9.0.0'},
    {from: '9.0.0', to: '9.0.1'},
    {from: '9.0.1', to: '9.0.2'},
    {from: '9.0.3', to: '9.1.0'},
    {from: '9.2.0', to: '9.3.0'},
    {from: `9.7.0`, to: '10.0.0'},
]

export const keyVersions = Array.from(new Set(upgradingInfos.map(info => [info.from, info.to]).flat()));

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

export function getDefaultUpgradingInfo() {
    return upgradingInfos[upgradingInfos.length - 1]
}

export function getChanges(info: UpgradingInfo) {
    const result = [];
    let put = false;
    upgradingInfos.forEach(upgradingInfo => {
        if (info.from === upgradingInfo.from) {
            put = info.from !== upgradingInfo.from || info.to !== upgradingInfo.to;
            result.push(upgradingInfo);
        } else if (info.from === upgradingInfo.to) {
            put = true;
        } else if (info.to === upgradingInfo.from) {
            put = false;
        } else if (info.to === upgradingInfo.to) {
            put = false;
            result.push(upgradingInfo);
        } else if (put) {
            result.push(upgradingInfo);
        }
    });
    return result;
}

export const nowUpgradingInfo: Ref<UpgradingInfo> = ref(getDefaultUpgradingInfo());
export const nowChanges: Ref<UpgradingInfo[]> = ref([]);