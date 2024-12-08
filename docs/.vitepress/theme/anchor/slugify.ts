import slugify from "@sindresorhus/slugify";

export default function slugifyTitle(title: string): string {
    return slugify(title
        .replace(" – ", "-")
        .replace(":", "-")
        .replace("\"", "")
        .replace("'", "")
        .replace("<", "")
        .replace(">", "")
    );
}