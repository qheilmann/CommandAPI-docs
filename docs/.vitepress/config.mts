// @ts-ignore
import {repository, homepage} from '../../package.json';
import {defineConfig, UserConfig} from 'vitepress';
import {withI18n} from 'vitepress-i18n';
import {VitePressI18nOptions} from 'vitepress-i18n/dist/types';
import {VitePressSidebarOptions, withSidebar} from "vitepress-sidebar";
import {tabsPlugin} from "./theme/tabs/codesMarkdownPlugin";

const defaultLocale: string = 'en';
const supportLocales: string[] = [defaultLocale, 'zhHans'];
const editLinkPattern = `${repository.url}/edit/master/docs/:path`;

const commonSidebarOptions: VitePressSidebarOptions = {
    excludeFilesByFrontmatterFieldName: "hidden",
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
    description: "Docs of CommandAPI",
    cleanUrls: true,
    metaChunk: true,
    ignoreDeadLinks: true, // TODO remove when all things are done
    rewrites: {
        'en/:rest*': ':rest*'
    },
    base: 'BASE_INJECT_POINT',
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
        editLink: {
            pattern: editLinkPattern
        },
        socialLinks: [
            {
                icon: 'github',
                link: 'https://github.com/CommandAPI/CommandAPI'
            }
        ]
    },
    markdown: {
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
                    match: "(?<=^\\/)[a-zA-Z0-9]+"
                }, {
                    name: "entity.name.tag.mccmd",
                    match: "@[aeprs](\\[.+\\])?"
                }, {
                    name: "variable.mccmd",
                    match: "-?[0-9]+"
                }, {
                    name: "comment.mccmd",
                    match: "<[^>]+>"
                }, {
                    name: "entity.mccmd",
                    match: "\\b(align|anchored|as|at|facing|in|positioned|rotated|run|if|store|result|score|matches)\\b"
                }]
            }).then(_ => {
            })
        },
        config: (md) => {
            tabsPlugin(md)
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
                    text: 'Introduction',
                    link: '/intro'
                },
            ]
        },
        zhHans: {
            outline: {
                level: "2-3",
                label: "在本页"
            },
            nav: [
                {
                    text: '介绍',
                    link: '/zhHans/intro'
                },
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

export default defineConfig(withSidebar(withI18n(vitepressOptions, vitePressI18nOptions), vitepressSidebarOptions))