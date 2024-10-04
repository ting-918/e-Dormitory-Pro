<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">{{ this.setTitle }}</div>
    <div>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填</span>
        </template>
        <el-input v-model="price"
                placeholder="輸入本年度住宿費用"
                maxLength="5"
                clearable
                oninput="value = Number(value) || value.replace(/[^0-9]+/g, '')"
                style="top: 38%;"
      >
        <template #prepend>＄</template>
      </el-input>
      </el-tooltip>
    </div>
    <button class="submit"
            @click="handleSubmit"
            :disabled="isInputValid"
            v-bind:style="{'cursor':(this.isInputValid ? 'not-allowed' : 'pointer')}">發起</button>
  </div>
</template>

<script>
export default {
  name: "myDialog",
  data(){
    return{
      price: null,
      year: null,
      tipVisible: false,
    }
  },
  props: ['visible'],
  computed: {
    isInputValid() {
      return (this.price == null || this.price == '' || this.price <= 0);
    },
    setTitle() {
      var myDate = new Date();
      if(myDate.getMonth()+1 < 7)
        this.year = (myDate.getFullYear()-1).toString();
      else
        this.year = (myDate.getFullYear()).toString();
      return this.year+" 年度繳費通知"; 
    }
  },
  methods: {
    hidden(){
      this.price = null;
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      this.$emit('handleSubmit', [this.price, this.year]);
    },
  }
}
</script>

<style scoped>
/*彈窗*/
.dialog {
  width: 28vw;
  height: 25vh;
  left: 36vw;
  top: 28vh;
  position: fixed; /* 相對於瀏覽器窗口固定 */
  background: rgba(251, 217, 216, 0.839);
  background: linear-gradient(135deg, rgba(251, 217, 216, 0.839) 0%, rgba(194, 219, 239, 0.919) 100%);
  border-radius: 1.3em;
  box-shadow: -5px 5px 12px #6a7f90c3;
}
/*彈窗的關閉按鈕*/
.close-button {
  width: 6%;
  height: 14%;
  right: 5%;
  top: 8%;
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
  top: 10%;
  width: 100%;
  text-align: center;
  font-size: 1.3vw;
  font-weight: bold;
  color: #819aaf;
}
.submit {
  position: absolute;
  width: 15%;
  height: 16%;
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
  left: 22%;
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

