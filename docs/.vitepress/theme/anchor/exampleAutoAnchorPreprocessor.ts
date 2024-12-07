import MarkdownIt from "markdown-it";
import slugify from "@sindresorhus/slugify";

export const exampleAutoAnchorPreprocessor = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'example-auto-anchor', (state) => {
        const lines = state.src.split("\n");
        const processedLines = lines.map((line) => {
            if (line.startsWith(":::") && (line.includes(":::tip Example") || line.includes("::: tip Example"))) {
                const title = line.split(/tip (.+)/)[1]
                const processedTitle = slugify(title
                    .replace(" – ", "-")
                    .replace(":", "-")
                    .replace("\"", "")
                    .replace("'", "")
                    .replace("<", "")
                    .replace(">", "")
                );
                return `
<div style="height: 0; color: rgba(0,0,0,0%);position: relative;top: 32px">
    <h3 id="${processedTitle}">${title}
        <a class="header-anchor" href="#${processedTitle}">​</a>
    </h3>                
</div>

${line}
`;
            } else {
                return line;
            }
        });
        state.src = processedLines.join("\n");
    });
}

