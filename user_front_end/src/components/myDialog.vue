<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">{{ title }}</div>
    <section>{{ description }}</section>
    <div v-if="isDonate">
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">選填</span>
        </template>
        <el-input v-model="donate.donor"
                  placeholder="留下你的姓名"
                  maxLength="10"
                  show-word-limit
                  @mouseover="tipVisible=true"
                  @mouseleave="tipVisible=false"
                  style="top: 35%;"
        />
      </el-tooltip>
      <el-tooltip :visible="tip2Visible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">選填</span>
        </template>
        <el-input v-model="donate.phone"
                  placeholder="輸入手機號"
                  maxLength="11"
                  clearable
                  @mouseover="tip2Visible=true"
                  @mouseleave="tip2Visible=false"
                  oninput="value=value.replace(/[^0-9]+/g, '')"
                  style="top: 50%;"
        >
          <template #prepend>＋86</template>
        </el-input>
      </el-tooltip>
      <el-input v-model="donate.amount"
                ref="amount"
                placeholder="輸入捐贈金額"
                maxLength="5"
                clearable
                oninput="value = Number(value) || value.replace(/[^1-9]+/g, '')"
                style="top: 65%;"
      >
        <template #prepend>＄</template>
      </el-input>
    </div>
    <div v-else>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填</span>
        </template>
        <el-input v-model="adopter.name"
                  placeholder="留下你的姓名"
                  maxLength="10"
                  show-word-limit
                  @mouseover="tipVisible=true"
                  @mouseleave="tipVisible=false"
                  style="top: 35%;"
        />
      </el-tooltip>
      <el-tooltip :visible="tip2Visible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填</span>
        </template>
        <el-input v-model="adopter.phone"
                  placeholder="輸入手機號"
                  maxLength="11"
                  clearable
                  @mouseover="tip2Visible=true"
                  @mouseleave="tip2Visible=false"
                  oninput="value=value.replace(/[^0-9]+/g, '')"
                  style="top: 50%;"
        >
          <template #prepend>＋86</template>
        </el-input>
      </el-tooltip>
    </div>
    <button class="submit"
            @click="handleSubmit"
            :disabled="isInputValid"
            v-bind:style="{'cursor':(this.isInputValid ? 'not-allowed' : 'pointer')}">提交</button>
  </div>
</template>

<script>
export default {
  name: "myDialog",
  data(){
    return{
      donate:{
        transaction_id:null,
        fundraising_id:null,
        donor:null,
        phone:null,
        amount:null
      },
      adopter:{
        name:null,
        phone:null
      },
      tipVisible: false,
      tip2Visible: false,
    }
  },
  props: ['visible', 'title', 'description', 'isDonate'],
  computed: {
    isInputValid() {
      if(this.isDonate){
        return (this.donate.amount == null);
      }
      else{
        return (this.adopter.name == null || this.adopter.name == '' || this.adopter.phone == null || this.adopter.phone == '' || this.adopter.phone.length < 11);
      }
    }
  },
  methods: {
    hidden(){
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      if(this.isDonate){
        this.$emit('handleSubmit', this.donate);
      }
      else{
        this.$emit('handleSubmit', this.adopter);
      }
    },
  }
}
</script>

<style scoped>
/*彈窗*/
.dialog {
  width: 35vw;
  height: 50vh;
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
  width: 7%;
  height: 9%;
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
  top: 20%;
  width: 100%;
  line-height: 2em;
  text-align: center;
  font-size: 1.1vw;
  color: #758c9e;
}
.submit {
  position: absolute;
  width: 12%;
  height: 8%;
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

