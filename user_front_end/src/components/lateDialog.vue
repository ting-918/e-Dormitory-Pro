<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">登記夜歸</div>
    <section style="top: 22%; left: 40%;">{{ "寢室 : "+ data.did }}</section>
    <section style="top: 32%; left: 40%;">{{ "姓名 : "+ data.name }}</section>
    <section style="top: 42%; left: 40%;">{{ "學號 : "+ data.studentid }}</section>
    <div>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b;">選填</span>
        </template>
        <el-input v-model="late.reason"
                  placeholder="請輸入夜歸原因"
                  maxLength="50"
                  show-word-limit
                  clearable
                  @mouseover="tipVisible=true"
                  @mouseleave="tipVisible=false"
                  style="top: 57%;">
        </el-input>
      </el-tooltip>
    </div>
    <button class="submit"
            @click="handleSubmit">登記
    </button>
  </div>
</template>

<script>
export default {
  name: "lateDialog",
  data(){
    return{
      late: {
        studentid: null,
        reason: null
      },
    }
  },
  props: ['visible', 'data'],
  computed: { },
  methods: {
    hidden(){
      this.late.reason = null;
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      this.late.studentid = this.data.studentid;
      this.$emit('handleSubmit', this.late);
      this.hidden();
    }
  }
}
</script>

<style scoped>
/*彈窗*/
.dialog {
  width: 35vw;
  height: 35vh;
  left: 32vw;
  top: 27vh;
  position: fixed; /* 相對於瀏覽器窗口固定 */
  background: rgba(251, 217, 216, 0.839);
  background: linear-gradient(135deg, rgba(251, 217, 216, 0.839) 0%, rgba(194, 219, 239, 0.919) 100%);
  border-radius: 1.3em;
  box-shadow: -5px 5px 12px #6a7f90c3;
}
/*彈窗的關閉按鈕*/
.close-button {
  width: 2vw;
  height: 4vh;
  right: 5%;
  top: 5%;
  position: absolute;
  border: none;
  border-radius: 50%;
  background-color: #94badad2;
  color: white;
}
.close-button:hover {
  cursor: pointer;
}
.item-title {
  position: absolute;
  top: 8%;
  width: 100%;
  text-align: center;
  font-size: 1.3vw;
  font-weight: bold;
  color: #819aaf;
}
section {
  position: absolute;
  line-height: 2em;
  font-size: 1vw;
  color: #818d97;
}
.submit {
  position: absolute;
  width: 4.2vw;
  height: 4vh;
  left: 45%;
  bottom: 10%;
  border: none;
  border-radius: 0.5em;
  background-color: #94badad2;
  color: white;
  font-size: 1vw;
}
.submit:hover {
  cursor: pointer;
}
.el-input {
  position: absolute;
  left: 20%;
  width: 60%;
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

