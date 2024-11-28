import MarkdownIt from "markdown-it";
import slugify from "@sindresorhus/slugify";

export const exampleAutoAnchorPreprocessor = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'example-auto-anchor', (state) => {
        const lines = state.src.split("\n");
        const processedLines = lines.map((line) => {
            if (line.startsWith(":::") && (line.includes(":::tip Example") || line.includes("::: tip Example"))) {
                const title = line.split(/tip (.+)/)[1]
                return `
<div style="height: 0; color: rgba(0,0,0,0%);position: relative;top: 50px">
<h3 id="${slugify(title.replace(" – ", ""))}">${title}</h3>                
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

