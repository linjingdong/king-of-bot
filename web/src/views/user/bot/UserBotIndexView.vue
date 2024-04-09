<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width:100%">
                    </div>
                </div>
            </div>

            <div class="col-9">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-header" style="display:flex; align-items:center;">
                        <span class="header-text" style="font-size: 110%">我的Bot</span>
                        <button type="button" class="btn btn-primary" style="margin-left: auto;" data-bs-toggle="modal"
                            data-bs-target="#add_bot_btn">创建Bot</button>
                        <!-- add_btn_Modal -->
                        <div class="modal fade" id="add_bot_btn" tabindex="-1">
                            <div class="modal-dialog modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">创建Bot</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add_bot_title" class="form-label">名称</label>
                                            <input v-model="bot_add.title" type="text" class="form-control"
                                                id="add_bot_title" placeholder="请输入Bot名称">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add_bot_description" class="form-label">简介</label>
                                            <textarea v-model="bot_add.description" class="form-control"
                                                id="add_bot_description" rows="3" placeholder="请输入Bot简介"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add_bot_code" class="form-label">代码</label>
                                            <VAceEditor v-model:value="bot_add.content" lang="c_cpp" theme="textmate"
                                                style="height: 300px" :options="{
                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                    enableSnippets: true, // 启用代码段
                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                    fontSize: 14, //设置字号
                                                    tabSize: 3, // 标签大小
                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                    highlightActiveLine: true,
                                                }" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error_message">{{ bot_add.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_bot">创建</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td> {{ bot.title }} </td>
                                    <td> {{ bot.createTime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-secondary"
                                            style="margin-right: 10px; --bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                            data-bs-toggle="modal"
                                            :data-bs-target="'#update-bot-modal-' + bot.id">修改</button>
                                        <!-- update_bot_modal -->
                                        <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="exampleModalLabel">创建Bot</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="update_bot_title" class="form-label">名称</label>
                                                            <input v-model="bot.title" type="text" class="form-control"
                                                                id="update_bot_title" placeholder="请输入Bot名称">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update_bot_description"
                                                                class="form-label">简介</label>
                                                            <textarea v-model="bot.description" class="form-control"
                                                                id="update_bot_description" rows="3"
                                                                placeholder="请输入Bot简介"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update_bot_code" class="form-label">代码</label>
                                                            <VAceEditor v-model:value="bot.content" lang="c_cpp"
                                                                theme="textmate" style="height: 300px" :options="{
                                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                                    enableSnippets: true, // 启用代码段
                                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                                    fontSize: 14, //设置字号
                                                                    tabSize: 3, // 标签大小
                                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                                    highlightActiveLine: true,
                                                                }" />
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error_message">{{ bot.error_message }}</div>
                                                        <button type="button" class="btn btn-primary"
                                                            @click="update_bot(bot)">保存修改</button>
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-danger"
                                            style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                            @click="remove_bot(bot)">删除</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { getList } from '@/api/user/bot/GetBotList';
import { add } from '@/api/user/bot/AddBot';
import { remove } from '@/api/user/bot/RemoveBot';
import { update } from '@/api/user/bot/UpdateBot';
import { Modal } from 'bootstrap/dist/js/bootstrap.min.js';
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
import 'ace-builds/src-noconflict/mode-c_cpp';
import 'ace-builds/src-noconflict/mode-json';
import 'ace-builds/src-noconflict/theme-chrome';
import 'ace-builds/src-noconflict/ext-language_tools';

ace.config.set(
    "basePath",
    "https://cdn.jsdelivr.net/npm/ace-builds@" + import.meta.glob('ace-builds').version + "/src-noconflict/")

let bots = ref([]);



const bot_add = reactive({
    title: "",
    description: "",
    content: "",
    error_message: "",
})

const refresh_bots = async () => {
    await getList().then(resp => {
        bots.value = resp.data;
    })
}

const add_bot = async () => {
    bot_add.error_message = "";
    await add({
        title: bot_add.title,
        description: bot_add.description,
        content: bot_add.content
    }).then(resp => {
        if (resp.data.error_message === "success") {
            bot_add.title = "";
            bot_add.description = "";
            bot_add.content = "";
            Modal.getInstance("#add_bot_btn").hide();
            refresh_bots();
        } else {
            bot_add.error_message = resp.data.error_message;
        }
    })
}

const update_bot = async (bot) => {
    bot_add.error_message = "";
    console.log(bot)
    await update({
        bot_id: bot.id,
        title: bot.title,
        description: bot.description,
        content: bot.content
    }).then(resp => {
        if (resp.data.error_message === "success") {
            Modal.getInstance("#update-bot-modal-" + bot.id).hide();
            refresh_bots();
        } else {
            bot.error_message = resp.data.error_message;
        }
    })
}

const remove_bot = async (bot) => {
    await remove({
        bot_id: bot.id
    }).then(resp => {
        if (resp.data.error_message === "success") {
            refresh_bots();
        }
    })
}


refresh_bots();
</script>

<style scoped>
div.error_message {
    color: red;
}
</style>