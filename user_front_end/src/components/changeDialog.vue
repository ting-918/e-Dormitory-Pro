<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">{{ title }}</div>
    <section>{{ this.data.name }} / {{ this.data.did }} 寢室 </section>
    <div>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span v-bind:style="{'color':(this.tipColor)}">{{ this.tipContent }}</span>
        </template>
        <el-input v-model="new_dorm.bid"
                  placeholder="請輸入樓棟編號"
                  maxLength="50"
                  show-word-limit
                  @mouseover="tipVisible=true"
                  @mouseleave="tipVisible=false"
                  oninput="value=value.replace(/[^0-9A-Za-z]+/g, '')"
                  style="top: 40%;">
          <template #append>樓</template>
        </el-input>
      </el-tooltip>
      <el-tooltip :visible="tip1Visible" effect="light" placement="top">
        <template #content>
          <span v-bind:style="{'color':(this.tip1Color)}">{{ this.tip1Content }}</span>
        </template>
        <el-input v-model="new_dorm.room"
                  placeholder="請輸入寢室號"
                  maxLength="50"
                  show-word-limit
                  @mouseover="tip1Visible=true"
                  @mouseleave="tip1Visible=false"
                  oninput="value=value.replace(/[^0-9]+/g, '')"
                  style="top: 58%;">
          <template #append>室</template>
        </el-input>
      </el-tooltip>
    </div>
    <button class="submit"
            @click="handleSubmit"
            :disabled="isInputValid"
            v-bind:style="{'cursor':(this.isInputValid ? 'not-allowed' : 'pointer')}">提交
    </button>
  </div>
</template>

<script>
export default {
  name: "changeDialog",
  data(){
    return{
      new_dorm:{
        bid: null,
        room: null,
      },
      student:{
        studentid: null,
        did: null,
      },
      tipVisible: false,
      tipContent: "必填",
      tipColor: "#7d8d9b",
      tip1Visible: false,
      tip1Content: "必填",
      tip1Color: "#7d8d9b",
    }
  },
  props: ['visible', 'title', 'data', 'existBuildings', 'validRoom'],
  computed: {
    isInputValid() {
      if(this.new_dorm.bid != null && this.new_dorm.bid != ''){
        if(this.new_dorm.room != null && this.new_dorm.room != '') {
          let did = this.new_dorm.bid+this.new_dorm.room
          if(did == this.data.did) {
            this.tip1Content = "與原寢室相同";
            this.tip1Color = "#CC3B3B";
            this.tip1Visible = true;
            return true;
          }
          if(!(this.validRoom.map(function (item) { return item.did; }).includes(did))) {
            this.tip1Content = "該寢室不存在/條件不符";
            this.tip1Color = "#CC3B3B";
            this.tip1Visible = true;
            return true;
          }
          this.setDefault1();
          return false;
        }
        else{
          this.setDefault1();
          if(!(this.existBuildings.map(function (item) { return item.bid; }).includes(this.new_dorm.bid))) {
            this.tipContent = "該樓棟不存在";
            this.tipColor = "#CC3B3B";
            this.tipVisible = true;
            return true;
          }
        }
      }
      this.setDefault();
      return true
    },
  },
  methods: {
    hidden(){
      this.new_dorm.bid = '';
      this.new_dorm.room = '';
      this.tipVisible = false;
      this.tip1Visible = false;
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      this.student.did = (this.new_dorm.bid+this.new_dorm.room);
      this.student.studentid = this.data.studentid;
      this.$emit('handleSubmit', this.student);
      this.hidden();
    },
    setDefault(){
      this.tipContent = "必填";
      this.tipColor = "#7d8d9b";
      this.tipVisible = false;
    },
    setDefault1(){
      this.tip1Content = "必填";
      this.tip1Color = "#7d8d9b";
      this.tip1Visible = false;
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
  top: 25%;
  width: 100%;
  line-height: 2em;
  text-align: center;
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

