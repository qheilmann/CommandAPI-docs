const notFoundMessages = [
    "{} experienced kinetic energy",
    "{} blew up",
    "{} was killed by [Intentional Game Design]",
    "{} hit the ground too hard",
    "{} fell out of the water",
    "{} was doomed to fall",
    "{} went off with a bang",
    "{} tried to swim in lava",
    "{} was obliterated by a sonically-charged shriek",
    "{} suffocated in a wall",
    "{} was squished too much",
    "{} left the confines of this world",
    "{} withered away",
];

export default function getRandomNotFoundMsg(): string {
    return notFoundMessages[Math.floor(Math.random() * notFoundMessages.length)]
        .replace("{}", "This page");
}