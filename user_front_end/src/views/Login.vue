<template>
<div class="background">
  <div class="logo">
          宿舍信息管理系統
  </div>
  <div class="container">
    <el-radio-group v-model="option" :fill="'#80afde'">
      <el-radio-button label="student">學生</el-radio-button>
      <el-radio-button label="manager">宿管</el-radio-button>
      <el-radio-button label="admin">社區</el-radio-button>
      <el-radio-button label="maintain">物業</el-radio-button>
    </el-radio-group>

    <el-tooltip :visible="tipVisible" effect="light" placement="right">
      <template #content>
        <span v-bind:style="{'color':(this.tipColor)}">{{ this.tipContent }}</span>
      </template>
      <el-input v-if="option=='student'"
                v-model="account.id"
                placeholder="請輸入學號"
                maxLength="50"
                clearable
                show-word-limit
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false"
                @click.native="onClick"
                style="top: 18vh;"
      />
      <el-input v-else-if="option=='manager'"
                v-model="account.id"
                placeholder="請輸入樓棟編號"
                maxLength="50"
                clearable
                show-word-limit
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false"
                @click.native="onClick"
                style="top: 18vh;"
      />
      <el-input v-else-if="option=='admin'"
                v-model="account.id"
                placeholder="請輸入帳號"
                maxLength="10"
                clearable
                show-word-limit
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false"
                @click.native="onClick"
                style="top: 18vh;"
      />
      <el-input v-else
                v-model="account.id"
                placeholder="請輸入帳號"
                maxLength="10"
                clearable
                show-word-limit
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false"
                @click.native="onClick"
                style="top: 18vh;"
      />
    </el-tooltip>
    <el-tooltip :visible="tip1Visible" effect="light" placement="right">
      <template #content>
        <span v-bind:style="{'color':(this.tip1Color)}">{{ this.tip1Content }}</span>
      </template>
      <el-input v-model="account.pwd"
                  placeholder="請輸入密碼"
                  maxLength="50"
                  clearable
                  show-word-limit
                  @mouseover="tip1Visible=true"
                  @mouseleave="tip1Visible=false"
                  @click.native="onClick"
                  style="top: 25vh;"
        />
    </el-tooltip>
    <my-button :btn_name="btn_name"
               :top="top"
               :right="right"
               :disable="isInputValid"
               @click="login">
    </my-button>
  </div>
</div>
</template>

<script>
import { ElMessage } from 'element-plus'
import MyButton from "@/components/myButton";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
axios.defaults.headers['Content-Type'] = 'application/json;charset=UTF-8';

export default {
  name: "Login",
  components: {MyButton},
  data() {
    return {
      btn_name: "登入系統",
      top:70,
      right:44,
      option: "student",
      tipColor: "#7d8d9b",
      tipContent: "必填",
      tipVisible: false,
      tip1Color: "#7d8d9b",
      tip1Content: "必填",
      tip1Visible: false,
      incorrect: {
        type: null,
        content: null,
      },
      account: {
        id: null,
        pwd: null,
      },
      token: {
        id: null,
        info: null,
        name: null,
      }
    }
  },
  computed: {
    isInputValid() {
        return (this.account.id == null || this.account.id == '' || this.account.pwd == null || this.account.pwd == '' );
    },
  },
  methods: {
    onClick() {
      if((this.incorrect.type == 0)) {
        this.tipColor = "#7d8d9b";
        this.tipContent = "必填";
        this.tipVisible = false;
      }
      if((this.incorrect.type == 1)) {
        this.tip1Color = "#7d8d9b";
        this.tip1Content = "必填";
        this.tip1Visible = false;
      }
    },
    login() {
      axios.post("/path/login/"+this.option, this.account)
          .then((response) => {
            console.log(response);
            if (response.data.code == 401) {
              this.incorrect.type = 0;
              this.incorrect.content = this.account.id;
              this.tipContent = response.data.msg;
              this.tipColor = "#CC3B3B";
              this.tipVisible = true;
            }
            if (response.data.code == 402) {
              this.incorrect.type = 1;
              this.incorrect.content = this.account.pwd;
              this.tip1Content = response.data.msg;
              this.tip1Color = "#CC3B3B";
              this.tip1Visible = true;
            }
            if (response.data.code == 200) {
              this.token.id = this.account.id;
              if(this.option == "student"){
                this.token.info = response.data.data['did'];
                this.token.name = response.data.data['name'];
              }
              if(this.option == "manager")
                this.token.info = response.data.data['bid'];
              //設置cookie
              cookies.set(this.option+'Token', JSON.stringify(this.token));
              //登入成功提示
              ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
              //頁面跳轉
              this.$router.push('/'+this.option);
            }
          })
          .catch((error) => {
            console.log(error)
          });
    },
    clear() {
      let opt = ["student", "manager", "admin", "maintain"]
      opt.forEach((o) => {
        let x = o+'Token';
        if(cookies.isKey(x))
          cookies.remove(x)
      });
    }
  },
  created() {
    this.clear();
  }
}
</script>

<style scoped>
.background {
  background: #f6fcff;
  width: 100vw;
  height: 100vh;
}
.logo {
  position: absolute;
  top: 13vh;
  left: 43vw;
  line-height: 10vh;
  background-color: #f6fcff;
  color: #BCCFDF;
  font-size: 1.8vw
}
.logo:hover{
  cursor: pointer;
  color: #758c9e;
}
.container {
  position: relative;
  width: 50vw;
  height: 50vh;
  top: 25vh;
  left: 25vw;
  background-color: #bccfdf94;
  border-radius: 1.3em;
}
.el-radio-group {
  position: absolute;
  top: 8vh;
  left: 17vw;
}
.el-input {
  position: absolute;
  left: 30%;
  width: 40%;
  /*默认边框颜色*/
  --el-input-border-color: transparent;
  /*背景颜色*/
  --el-input-bg-color: #f6fafcdf;
  /*获取焦点后的边框颜色*/
  --el-input-focus-border-color: transparent;
  /*鼠标悬停边框颜色*/
  --el-input-hover-border-color: transparent;
}
:deep(.el-input__inner){
  font-size: 1vw;
  color: #7d8d9b;
}
:deep(.el-input__wrapper) {
  border-radius: 0.8em;
}
</style>
