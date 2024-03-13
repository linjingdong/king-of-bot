const AC_GAME_OBJECT = [];

export class AcGameObect {
    constructor() {
        AC_GAME_OBJECT.push(this);
        this.timedelta = 0; // 记录时间间隔
        this.has_callded_start = false; // 记录是否执行过
    }

    start() { // 只执行一次

    }

    update() { // 每一帧只执行一次，第一帧除外

    }

    on_destory() { // 删除之前执行

    }

    destory() {
        this.on_destory(); // 删除之前执行一次

        for (let i in AC_GAME_OBJECT) {
            const obj = AC_GAME_OBJECT[i]; // 取出每个对象
            if (obj == this) {
                AC_GAME_OBJECT.splice(i);
                break;
            }
        }
    }

}

let last_timestamp; //上一次执行的时间
let step = timestamp => {
    for (let obj of AC_GAME_OBJECT) {
        // 遍历所有对象，判断是否执行过一次
        if (!obj.has_callded_start) {
            obj.has_callded_start = true; // 记录执行过
            obj.start(); // 执行
        } else {
            obj.timedelta = timestamp - last_timestamp; // 记录时间间隔
            obj.update(); // 更新
        }
    }
    last_timestamp = timestamp;
    requestAnimationFrame(step) // 回调，循环执行
}

requestAnimationFrame(step)