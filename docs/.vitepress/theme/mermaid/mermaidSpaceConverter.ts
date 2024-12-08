import MarkdownIt from "markdown-it";

export const mermaidSpaceConverter = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'mermaid-space-converter', (state) => {
        if(!state.src.includes("```mermaid")) {
            return;
        }
        const blocks = state.src.split("```mermaid");
        state.src = blocks.map((block) => {
            const split = block.split("```");
            split[0] = split[0].replace(/(\[.*?]|\{.*?}|\(.*?\))/g, (match) => {
                return match.replace(/ /g, '&nbsp;');
            });
            return split.join("```");
        }).join("```mermaid");
    });
}

