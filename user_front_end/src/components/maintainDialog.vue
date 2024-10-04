<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">寢室報修</div>
    <el-tooltip :visible="tip1Visible" effect="light" placement="top">
      <template #content>
        <span style="color: #7d8d9b">必填</span>
      </template>
      <el-input v-model="apply.item"
                placeholder="請輸入待維修設備名稱"
                maxLength="10"
                show-word-limit
                clearable
                @mouseover="tip1Visible=true"
                @mouseleave="tip1Visible=false"
                style="top: 25%;"
      />
    </el-tooltip>
    <el-tooltip :visible="tip2Visible" effect="light" placement="top">
      <template #content>
        <span style="color: #7d8d9b">選填</span>
      </template>
      <el-input v-model="apply.reason"
                placeholder="請簡述具體情況或原因"
                maxLength="20"
                show-word-limit
                clearable
                @mouseover="tip2Visible=true"
                @mouseleave="tip2Visible=false"
                style="top: 42%;"
      >
      </el-input>
    </el-tooltip>
    <el-tooltip :visible="tip3Visible" effect="light" placement="top">
      <template #content>
        <span style="color: #7d8d9b">選填</span>
      </template>
      <el-input v-model="apply.phone"
                placeholder="請輸入聯絡電話"
                maxLength="11"
                clearable
                @mouseover="tip3Visible=true"
                @mouseleave="tip3Visible=false"
                oninput="value= Number(value) || value.replace(/[^0-9]+/g, '')"
                style="top: 59%;"
      >
        <template #prepend>+86</template>
      </el-input>
    </el-tooltip>
    <button class="submit"
            @click="handleSubmit"
            :disabled="isInputValid"
            v-bind:style="{'cursor':(this.isInputValid ? 'not-allowed' : 'pointer')}">提交
    </button>
  </div>
</template>

<script>
export default {
  name: "maintainDialog",
  data(){
    return{
      apply: {
        did: null,
        item: null,
        reason: null,
        phone: null,
      },
      tip1Visible: false,
      tip2Visible: false,
      tip3Visible: false,
    }
  },
  props: ['visible', 'data'],
  computed: {
    isInputValid() {
      return (this.apply.item == null || this.apply.item == '');
    },
  },
  methods: {
    hidden(){
      this.apply.item = '';
      this.apply.reason = null;
      this.apply.phone = null;
      this.tip1Visible = false;
      this.tip2Visible = false;
      this.tip3Visible = false;
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      this.apply.did = this.data;
      this.$emit('handleSubmit', this.apply);  
    },
  }
}
</script>

<style scoped>
/*彈窗*/
.dialog {
  width: 35vw;
  left: 32vw;
  top: 27vh;
  height: 40vh;
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
.submit {
  position: absolute;
  width: 4.2vw;
  height: 4vh;
  left: 45%;
  bottom: 12%;
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

