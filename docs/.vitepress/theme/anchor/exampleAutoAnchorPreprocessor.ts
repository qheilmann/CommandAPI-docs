import MarkdownIt from "markdown-it";
import slugifyTitle from "./slugify";

export const exampleAutoAnchorPreprocessor = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'example-auto-anchor', (state) => {
        if (!state.src.includes("tip Example")) {
            return;
        }
        const lines = state.src.split("\n");
        const processedLines = lines.map((line) => {
            if (line.startsWith(":::") && (line.includes(":::tip Example") || line.includes("::: tip Example"))) {
                const title = line.split(/tip (.+)/)[1]
                const processedTitle = slugifyTitle(title);
                return `
<div style="height: 0;
 color: rgba(0,0,0,0%);
 position: relative;
 top: 32px;">
 <TitleAnchor anchor="${processedTitle}">
    <h3 id="${processedTitle}" style="
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    ">${title}
        <a class="header-anchor" href="#${processedTitle}">​</a>
    </h3>          
 </TitleAnchor>      
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

