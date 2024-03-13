import { AcGameObect } from "./AcGameObject";

export class Wall extends AcGameObect {
    constructor(r, c, gamemap) {
        super();

        this.r = r;
        this.c = c;
        this.gamemap = gamemap;
        this.color = "#b37226"
    }

    update() {
        this.render();
    }

    render() {
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx;

        ctx.fillStyle = this.color;
        ctx.fillRect(this.c * L, this.r * L, L, L);
    }
}