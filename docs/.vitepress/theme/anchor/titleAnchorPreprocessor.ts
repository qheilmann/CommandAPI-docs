import MarkdownIt from "markdown-it";
import slugifyTitle from "./slugify";

export const titleAnchorPreprocessor = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'title-anchor', (state) => {
        const lines = state.src.split("\n");
        const processedLines = lines.map((line) => {
            if (line.startsWith("#")) {
                const title = line.replace(new RegExp("#", "g"), "");
                const processedTitle = slugifyTitle(title);
                return `

<TitleAnchor anchor="${processedTitle}">

${line}

</TitleAnchor>

`;
            } else {
                return line;
            }
        });
        state.src = processedLines.join("\n");
    });
}
