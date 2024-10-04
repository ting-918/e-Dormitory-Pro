<template>
<div class="container"
      v-if="valid === 1">
  <div class="title">入住申請</div>
  <section style="top: 27%; left: 32%;">
    學生須遵守《宿舍樓棟管理辦法》, 在宿期間如有違規將進行懲處。
  </section>
  <section style="top: 34%; left: 32%;">
    入住申請通過後, 您須盡快繳納住宿費用, 以開通住宿生權益。
  </section>
  <section style="top: 41%; left: 32%;">
    住宿費用繳納完成後, 樓棟門禁卡方能生效。
  </section>
  <section style="top: 48%; left: 32%;">
    如有調寢需求, 請至社區中心諮詢辦理。
  </section>
  <section style="top: 55%; left: 32%;">
    若您已悉知相關規定, 請點選下方按鈕, 向社區中心提出入住申請。
  </section>
  <my-button :btn_name="btn_name"
             :top="72" :right="47"
             @click="handleSubmit"
  ></my-button>
</div>
<div class="container"
     v-else-if="valid === 2">
<div class="prompt">申請尚未審批通過, 請耐心等待</div>
</div>
<div class="container"
     v-else>
<div class="prompt">不符合申請資格</div>
</div>
</template>

<script>
import { ElMessage } from 'element-plus'
import axios from "axios";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import MyButton from "@/components/myButton";
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

export default {
  name: "Apply",
  data(){
    return {
      btn_name:"提交申請",
      valid: null,
      did: null,
      apply: {
        type: 0,
        studentid: null,
      },
    }
  },
  components: { MyButton },
  computed: {
  },
  methods: {
    handleSubmit() {
      axios.post("/path/apply", this.apply)
          .then((response) => {
            if (response.data.code == 200) {
              ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
              this.$router.push('/student');
            }
          })
          .catch((error) => {
            console.log(error)
          });
    },
    ApplyValid(type) {
      axios.get("/path/apply/"+type+"/"+ this.apply.studentid)
          .then((response) => {
            if (response.data.code == 200) {
              this.valid = response.data.data;
            }
          })
          .catch((error) => {
            console.log(error)
          });
    },
    interceptor() {
      if(!cookies.isKey('studentToken')){
        ElMessage({
                    message: "請重新登入",
                    type: 'warning',
                  });
        this.$router.push('/');
      }
      else {
        this.apply.studentid = cookies.get('studentToken').id;
      }
    }
  },
  created() {
    this.interceptor();
    let type = (this.apply.type == 0)? "checkin":"checkout";
    this.ApplyValid(type);
  }
}
</script>

<style scoped>
.container {
  position: relative;
  width: 80vw;
  height: 65vh;
  top: 5vh;
  left: 10vw;
  background-color: #bccfdf94;
  border-radius: 1.3em;
}
.title {
  position: absolute;
  top: 13%;
  width: 100%;
  text-align: center;
  font-size: 1.5vw;
  font-weight: bold;
  color: #819aaf;
}
.prompt {
  position: absolute;
  top: 45%;
  width: 100%;
  text-align: center;
  font-size: 1.4vw;
  color: #819aaf;
}
section {
  position: absolute;
  line-height: 2em;
  font-size: 1.1vw;
  color: #758c9e;
}
.el-radio-group {
  position: absolute;
  top: 49.5%;
  left: 42%;
  margin-right: 2vw;
}
.el-radio /deep/ .el-radio__input.is-checked .el-radio__inner {
  background: #88b9e1 !important;
  border-color: #88b9e1 !important;
}
.el-radio /deep/ .el-radio__input.is-checked + .el-radio__label {
  color: #6c94b4 !important;
}
.el-radio /deep/ .el-radio__label {
  font-size:1.2vw !important;
  color: #7d8d9b;
}

</style>
