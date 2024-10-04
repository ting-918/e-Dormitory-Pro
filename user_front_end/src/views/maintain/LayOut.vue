<template>
  <el-container class="layout-container-demo" style="height: 100%">
    <el-container>
      <el-header>
        <div class="logo">
          宿舍信息管理系統
        </div>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :router="true"
        >
          <el-menu-item index="/record">報修紀錄</el-menu-item>
          <el-menu-item index="/fee">水電費用</el-menu-item>
        </el-menu>
        <div class="user">
          <el-tooltip :visible="tipVisible"
                      effect="light" 
                      placement="bottom">
            <template #content>
              <span style="color: #7d8d9b">登出系統</span>
            </template>
            <button class="logout"
                    @click="LogOut"
                    @mouseover="tipVisible=true"
                    @mouseleave="tipVisible=false">㋡ 丹田物業
            </button>
          </el-tooltip>
        </div>
      </el-header>

      <el-main>
        <el-scrollbar>
         <router-view></router-view>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
import { Menu as IconMenu, Message, Setting,Location } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
export default {
  name: "LayOut",
  components:{
    IconMenu,
    Message,
    Setting,
    Location
  },
  data() {
    return{
      tipVisible: false,
    }
  },
  methods: {
    LogOut() {
      ElMessage({
                  message: "登出成功",
                  type: 'success',
                });
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>

.layout-container-demo .el-header {
  position: relative;
  text-align: center;
  font-size: 1.3vw;
  line-height: 10vh;
  height: 10vh;
}
.logo {
  position: absolute;
  left: 0;
  width: 20vw;
  height: 100%;
  line-height: 10vh;
  background-color: #f6fcff;
  color: #BCCFDF;
  font-size: 1.3vw
}
.logo:hover{
  cursor: pointer;
  font-size: 1.35vw;
  color: #758c9e;
}
.user {
  position: absolute;
  right: 0;
  width: 10vw;
  height: 100%;
  line-height: 10vh;
  background-color: #f6fcff;
}
.logout {
  position: absolute;
  top: 2vh;
  left: 0;
  width: 80%;
  height: 60%;
  border-radius: 0.8em;
  border-color: #BCCFDF ;
  background-color: #f6fcff;
  color: #758c9e;
  font-size: 1.05vw
}
.logout:hover {
  cursor: pointer;
}
.toolbar {
  position: absolute;
  right: 0;
  width: 10vw;
  height: 100%;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  background-color: #f6fcff;
  color: #BCCFDF;
}
.el-menu {
  position: absolute;
  left: 20vw;
  width: 70vw;
  height: 100%;
  border: none;
  background-color: #f6fcff;
}
.el-menu .el-menu-item {
  width: 20vw;
  font-size: 1.3vw;
  color: #BCCFDF;
  margin-left: 2vw;
  margin-right: 3vw;
}
.el-menu .el-menu-item:hover {
  font-size: 1.35vw;
  cursor: pointer;
  color: #758c9e !important;
  background-color: transparent !important;
}
.el-menu .el-sub-menu {
  margin-left: 2vw;
  margin-right: 3vw;
}
.el-menu .el-sub-menu :hover {
  cursor: pointer;
  color: #BCCFDF !important;
  background-color: transparent !important;
}
.option {
  width: 10vw;
  height: 10vh;
  margin-top: -5px;
  margin-bottom: -5px;
  background-color: #f6fcff;
  color: #BCCFDF;
}
.option .el-menu-item {
  width: 10vw !important;
  height: 5vh !important;
  color: #BCCFDF !important;
  background-color: transparent !important;
}
.layout-container-demo .el-main {
  padding: 0;
  background-color: #f6fcff;
}
</style>
