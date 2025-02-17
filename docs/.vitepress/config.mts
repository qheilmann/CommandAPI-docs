// @ts-ignore
import {repository, homepage} from '../../package.json';
import {UserConfig} from 'vitepress';
import {withI18n} from 'vitepress-i18n';
import {VitePressI18nOptions} from 'vitepress-i18n/dist/types';
import {VitePressSidebarOptions, withSidebar} from "vitepress-sidebar";
import {tabsPlugin} from "./theme/tabs/markdownPlugin";
import fs from "fs";
import {exampleAutoAnchorPreprocessor} from "./theme/anchor/exampleAutoAnchorPreprocessor";
import {mathjaxContainerPreprocessor} from "./theme/mathjax/mathjaxContainerPreprocessor";
import {withMermaid} from "vitepress-plugin-mermaid";
import {mermaidSpaceConverter} from "./theme/mermaid/mermaidSpaceConverter";
import {injectUpgradingPartsPlugin} from "./theme/upgrading/injectUpgradingPartsPlugin";
import {titleAnchorPreprocessor} from "./theme/anchor/titleAnchorPreprocessor";
import slugifyTitle from "./theme/anchor/slugify";

const defaultLocale: string = 'en';
const supportLocales: string[] = [
    defaultLocale,
    // 'zhHans'
];
const editLinkPattern = `${repository.url}/edit/master/docs/:path`;

const commonSidebarOptions: VitePressSidebarOptions = {
    excludeFilesByFrontmatterFieldName: "hidden",
    excludePattern: ['upgrading-parts/**'],
    collapsed: false,
    collapseDepth: 4,
    capitalizeFirst: true,
    useTitleFromFileHeading: true,
    useTitleFromFrontmatter: true,
    useFolderTitleFromIndexFile: true,
    sortMenusByFrontmatterOrder: true,
};

const vitepressOptions: UserConfig = {
    title: "CommandAPI",
    lastUpdated: true,
    description: "Website of CommandAPI",
    cleanUrls: true,
    metaChunk: true,
    rewrites: {
        'en/:rest*': ':rest*'
    },
    head: [['link', {rel: 'icon', href: '/favicon.svg'}]],
    base: getBase(),
    vite: {
        optimizeDeps: {
            exclude: [
                '@nolebase/vitepress-plugin-enhanced-readabilities/client',
                'vitepress',
                '@nolebase/ui',
            ],
        },
        ssr: {
            noExternal: [
                '@nolebase/vitepress-plugin-enhanced-readabilities',
                '@nolebase/ui',
            ],
        },
    },
    sitemap: {
        hostname: homepage
    },
    themeConfig: {
        logo: '/favicon.svg',
        editLink: {
            pattern: editLinkPattern
        },
        socialLinks: [
            {
                // from https://www.svgrepo.com/
                icon: {svg: '<svg xmlns="http://www.w3.org/2000/svg" fill="#000000" width="800px" height="800px" viewBox="0 0 512 512"><path d="M256 416c114.9 0 208-93.1 208-208S370.9 0 256 0 48 93.1 48 208s93.1 208 208 208zM233.8 97.4V80.6c0-9.2 7.4-16.6 16.6-16.6h11.1c9.2 0 16.6 7.4 16.6 16.6v17c15.5.8 30.5 6.1 43 15.4 5.6 4.1 6.2 12.3 1.2 17.1L306 145.6c-3.8 3.7-9.5 3.8-14 1-5.4-3.4-11.4-5.1-17.8-5.1h-38.9c-9 0-16.3 8.2-16.3 18.3 0 8.2 5 15.5 12.1 17.6l62.3 18.7c25.7 7.7 43.7 32.4 43.7 60.1 0 34-26.4 61.5-59.1 62.4v16.8c0 9.2-7.4 16.6-16.6 16.6h-11.1c-9.2 0-16.6-7.4-16.6-16.6v-17c-15.5-.8-30.5-6.1-43-15.4-5.6-4.1-6.2-12.3-1.2-17.1l16.3-15.5c3.8-3.7 9.5-3.8 14-1 5.4 3.4 11.4 5.1 17.8 5.1h38.9c9 0 16.3-8.2 16.3-18.3 0-8.2-5-15.5-12.1-17.6l-62.3-18.7c-25.7-7.7-43.7-32.4-43.7-60.1.1-34 26.4-61.5 59.1-62.4zM480 352h-32.5c-19.6 26-44.6 47.7-73 64h63.8c5.3 0 9.6 3.6 9.6 8v16c0 4.4-4.3 8-9.6 8H73.6c-5.3 0-9.6-3.6-9.6-8v-16c0-4.4 4.3-8 9.6-8h63.8c-28.4-16.3-53.3-38-73-64H32c-17.7 0-32 14.3-32 32v96c0 17.7 14.3 32 32 32h448c17.7 0 32-14.3 32-32v-96c0-17.7-14.3-32-32-32z"/></svg>'},
                link: 'https://ko-fi.com/jorelali'
            },
            {icon: 'discord', link: 'https://discord.com/invite/G4SzSxZ'},
            {icon: 'github', link: 'https://github.com/CommandAPI/'}
        ]
    },
    markdown: {
        anchor: {
            slugify(str: string): string {
                return slugifyTitle(str);
            }
        },
        container: {
            infoLabel: '**Developer\'s Note:**',
        },
        math: true,
        shikiSetup: (shiki) => {
            // @ts-ignore
            shiki.loadLanguage({
                name: "mccmd",
                scopeName: "source.mccmd",
                patterns: [{
                    name: "constant.language.mccmd",
                    match: "(?<=^\\/)\\b[a-zA-Z0-9]+\\b"
                }, {
                    name: "comment.mccmd",
                    match: "\\s-\\s.*"
                }, {
                    name: "entity.name.tag.mccmd",
                    match: "@[aeprs](\\[.+\\])?"
                }, {
                    name: "variable.mccmd",
                    match: "(?<=\\s|^)-?[0-9]+(?=\\s|$)"
                }, {
                    name: "entity.other.attribute-name.mccmd",
                    match: "<[^>]+>"
                }, {
                    name: "entity.mccmd",
                    match: "\\b(align|anchored|as|at|facing|in|positioned|rotated|run|if|store|result|score|matches)\\b"
                }]
            }).then(_ => {
            })
            shiki.loadLanguage({
                name: 'diff',
                scopeName: 'source.diff'
            }).then(_ => {
            })
        },
        config: ( md) => {
            tabsPlugin(md);
            injectUpgradingPartsPlugin(md);
            mermaidSpaceConverter(md);
            exampleAutoAnchorPreprocessor(md);
            titleAnchorPreprocessor(md);
            mathjaxContainerPreprocessor(md);

            // Replace "kt" in the top right of code blocks with "kotlin"
            const render = md.renderer.rules.fence!;
            md.renderer.rules.fence = (tokens, idx, options, env, self) => {
                if (tokens[idx].info.startsWith("kt[")) {
                    tokens[idx].info = tokens[idx].info.replace("kt[", "kotlin[");
                }
                return render(tokens, idx, options, env, self);
            };
        }
    }
}

const vitePressI18nOptions: VitePressI18nOptions = {
    locales: supportLocales,
    rootLocale: defaultLocale,
    searchProvider: 'local',
    themeConfig: {
        en: {
            outline: {
                level: "2-3",
                label: "On this page"
            },
            nav: [
                {
                    text: 'Home',
                    link: '/'
                }, {
                    text: 'Documentation',
                    link: '/intro'
                }, {
                    component: 'VersionSwitcher'
                }, {
                    component: 'DownloadCard'
                }
            ]
        }
    }
};

const vitepressSidebarOptions = [
    ...supportLocales.map((lang) => {
        return {
            ...commonSidebarOptions,
            documentRootPath: `/docs/${lang}`,
            resolvePath: defaultLocale === lang ? '/' : `/${lang}/`,
            ...(defaultLocale === lang ? {} : {basePath: `/${lang}/`})
        };
    })
];

function getBase(): string {
    if (fs.existsSync("VER")) {
        return `/${fs.readFileSync("VER", 'utf8')}/`;
    } else {
        return '';
    }
}

export default withMermaid(withSidebar(withI18n(vitepressOptions, vitePressI18nOptions), vitepressSidebarOptions))