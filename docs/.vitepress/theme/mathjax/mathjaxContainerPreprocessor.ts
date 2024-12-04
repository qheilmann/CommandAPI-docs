import MarkdownIt from "markdown-it";

export const mathjaxContainerPreprocessor = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'mathjax-container', (state) => {
        const lines = state.src.split("\n");
        const processedLines = lines.map((line) => {
            if (line.startsWith("$$\\begin")) {
                return `
<div style="overflow-x: auto">
<div style="width: fit-content;">

${line}
`;
            } else if (line.trim().endsWith("}$$")) {
                return `
${line}

</div>
</div>
`;
            } else {
                return line;
            }
        });
        state.src = processedLines.join("\n");
    });
}
