import { AcGameObect } from "./AcGameObject";
import { Snake } from "./Snake";
import { Wall } from "./Wall";

export class GameMap extends AcGameObect {
    /** 
     * ctx 画布
     * parent 定义一个父对象去框住画布，锁定画布的长和宽
    */
    constructor(ctx, parent) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0; // 定义绝对距离，默认每个单位的距离大小

        this.rows = 13; // 行数
        this.cols = 14; // 列数

        this.walls = []; // 墙
        this.inner_walls_count = 20; //障碍物

        this.snakes = [
            new Snake({ id: 0, color: "#f94848", r: this.rows - 2, c: 1 }, this),
            new Snake({ id: 1, color: "#4876ec", r: 1, c: this.cols - 2 }, this),
        ];

    }

    check_connectivity(g, sx, sy, tx, ty) {
        if (sx == tx && sy == tx) return true;
        g[sx][sy] = true;

        //设置偏移量
        let dx = [-1, 0, 1, 0], dy = [0, 1, 0, -1];
        for (let i = 0; i < 4; i++) {
            let x = sx + dx[i], y = sy + dy[i];
            if (!g[x][y] && this.check_connectivity(g, x, y, tx, ty))
                return true;
        }

        return false;
    }

    // 创建墙与障碍物
    create_walls() {
        const g = [];
        // 先遍历gamemap的每个单位让真假墙数组数组的值都为false
        for (let r = 0; r < this.rows; r++) {
            g[r] = [];
            for (let c = 0; c < this.cols; c++) {
                g[r][c] = false;
            }
        }

        // 遍历gamemap的行，给两边的真假墙数组的数值上true（给四周加上墙）
        for (let r = 0; r < this.rows; r++) {
            g[r][0] = g[r][this.cols - 1] = true;
        }

        // 遍历gamemap的列，给上下两边的真假墙数组的数值上false
        for (let c = 0; c < this.cols; c++) {
            g[0][c] = g[this.rows - 1][c] = true;
        }

        //创建随机障碍物
        for (let i = 0; i < this.inner_walls_count / 2; i++) {
            for (let j = 0; j < 1000; j++) {
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.cols);
                if (g[r][c] || g[this.rows - 1 - r][this.cols - 1 - c]) continue; // 中心对称，实现公平竞技
                if (r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2) continue;

                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = true;
                break;
            }
        }

        const copy_g = JSON.parse(JSON.stringify(g));
        if (!this.check_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2)) return false;

        // 遍历gamemap的每个单位，给map的上墙与障碍物
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if (g[r][c]) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }
        return true;
    }

    add_listening_events() {
        this.ctx.canvas.focus();

        const [snake0, snake1] = this.snakes;

        this.ctx.canvas.addEventListener("keydown", e => {
            if (e.key === 'w') snake0.set_direction(0);
            else if (e.key === 'd') snake0.set_direction(1);
            else if (e.key === 's') snake0.set_direction(2);
            else if (e.key === 'a') snake0.set_direction(3);
            else if (e.key === 'i') snake1.set_direction(0);
            else if (e.key === 'l') snake1.set_direction(1);
            else if (e.key === 'k') snake1.set_direction(2);
            else if (e.key === 'j') snake1.set_direction(3);
        });
    }

    start() {
        for (let i = 0; i < 1000; i++) {
            if (this.create_walls()) break;
        }

        this.add_listening_events();
    }

    // 更新时每个单位的大小每一帧刷新一遍
    update_size() {
        // 每个单位的大小
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        // 画布的宽
        this.ctx.canvas.width = this.L * this.cols;
        // 画布的长
        this.ctx.canvas.height = this.L * this.rows;
    }

    check_ready() { // 判断两条蛇是否准备好下一回合了
        for (const snake of this.snakes) {
            if (snake.status !== "idle") return false;
            if (snake.direction === -1) return false;
        }
        return true;
    }

    next_step() {
        for (const snake of this.snakes) {
            snake.next_step();
        }
    }

    check_valid(cell) { // 检测目标位置是否合法，没有撞到两条蛇的身体或障碍物
        for (const wall of this.walls) {
            if (wall.r === cell.r && wall.c === cell.c)
                return false;
        }

        for (const snake of this.snakes) {
            let k = snake.cells.length;
            if (!snake.check_tail_increasing()) { // 当蛇尾会前进的时候，就不要判断
                k--;
            }
            for (let i = 0; i < k; i++) {
                if (snake.cells[i].r === cell.r && snake.cells[i].c === cell.c)
                    return false;
            }
        }
        return true;
    }


    update() {
        this.update_size();
        if (this.check_ready()) {
            this.next_step();
        }
        this.render();
    }

    render() {
        const color_even = "#aad751", color_odd = "#a2d149";

        for (let r = 0; r <= this.rows; r++) {
            for (let c = 0; c <= this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
}