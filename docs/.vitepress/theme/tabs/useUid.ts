// copy from https://github.com/sapphi-red/vitepress-plugins , original license is MIT

let id = 0

export const useUid = () => {
    id++
    return '' + id
}