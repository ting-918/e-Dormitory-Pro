<template>
  <div class="dialog" v-bind:style="{'display':visible,'height':isAdd? '50vh':'20vh'}">
    <button class="close-button" @click="hidden">X</button>
    <div class="item-title">{{ title }}</div>
    <div v-if="isAdd">
      <el-tooltip :visible="tip1Visible" effect="light" placement="top">
        <template #content>
          <span v-bind:style="{'color':(this.tip1Color)}">{{ this.tip1Content }}</span>
        </template>
        <el-input v-model="new_building.bid"
                  placeholder="請輸入樓棟編號"
                  maxLength="50"
                  show-word-limit
                  @mouseover="tip1Visible=true"
                  @mouseleave="tip1Visible=false"
                  style="top: 20%;"
        />
      </el-tooltip>
      <el-tooltip :visible="tip2Visible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填, 至少為2</span>
        </template>
        <el-input v-model="new_building.floor"
                  placeholder="請輸入樓層數"
                  maxLength="3"
                  clearable
                  @mouseover="tip2Visible=true"
                  @mouseleave="tip2Visible=false"
                  oninput="value= Number(value) || value.replace(/[^0-9]+/g, '')"
                  style="top: 35%;"
        >
          <template #append>層</template>
        </el-input>
      </el-tooltip>
      <el-tooltip :visible="tip3Visible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填, 至少為1</span>
        </template>
        <el-input v-model="new_building.room"
                  placeholder="請輸入每層房數"
                  maxLength="3"
                  clearable
                  @mouseover="tip3Visible=true"
                  @mouseleave="tip3Visible=false"
                  oninput="value= Number(value) || value.replace(/[^0-9]+/g, '')"
                  style="top: 50%;"
        >
          <template #append>房</template>
        </el-input>
      </el-tooltip>
      <el-tooltip :visible="tip4Visible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">必填, 至少為1</span>
        </template>
        <el-input v-model="new_building.bed"
                  placeholder="請輸入床位數"
                  maxLength="1"
                  clearable
                  @mouseover="tip4Visible=true"
                  @mouseleave="tip4Visible=false"
                  oninput="value= Number(value) || value.replace(/[^1-9]+/g, '')"
                  style="top: 65%;"
        >
          <template #append>人間</template>
        </el-input>
      </el-tooltip>
    </div>
    <div v-else>
      <el-tooltip :visible="tip1Visible" effect="light" placement="top">
        <template #content>
          <span v-bind:style="{'color':(this.tip1Color)}">{{ this.tip1Content }}</span>
        </template>
        <el-input v-model="modify_building.pwd"
                  placeholder="請輸入新密碼" 
                  maxLength="10"
                  clearable
                  show-word-limit
                  @mouseover="tip1Visible=true"
                  @mouseleave="tip1Visible=false"
                  oninput="value = value.replace(/[^0-9A-Za-z]+/g, '')"
                  style="top: 35%;"
        />
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
  name: "buildingDialog",
  data(){
    return{
      new_building:{
        bid:null,
        floor:null,
        room:null,
        bed:null,
      },
      modify_building:{
        bid:null,
        pwd:''
      },
      tip1Visible: false,
      tip2Visible: false,
      tip3Visible: false,
      tip4Visible: false,
      tip1Content: "必填",
      tip1Color: "#7d8d9b",
    }
  },
  props: ['visible', 'title', 'isAdd', 'existList', 'data'],
  computed: {
    isInputValid() {
      if(this.isAdd){
        if(this.existList.map(function (item) { return item.bid; }).includes(this.new_building.bid)) {
          this.tip1Content = "該樓棟已存在";
          this.tip1Color = "#CC3B3B";
          this.tip1Visible = true;
          return true;
        }
        else{
          this.tip1Content = "必填";
          this.tip1Color = "#7d8d9b";
        }
        return (this.new_building.bid == null || this.new_building.bid == '' || this.new_building.floor == null || this.new_building.floor < 2 || this.new_building.room == null || this.new_building.room < 1 || this.new_building.bed == null || this.new_building.bed < 1);
      }
      else {
        if(this.modify_building.pwd == this.data.pwd) {
          this.tip1Content = "與原密碼相同";
          this.tip1Color = "#CC3B3B";
          this.tip1Visible = true;
          return true;
        }
        else{
          this.tip1Content = "必填";
          this.tip1Color = "#7d8d9b";
        }
        return (this.modify_building.pwd == null || this.modify_building.pwd == '' );
      }
    },
  },
  methods: {
    hidden(){
      this.modify_building.pwd = '';
      this.new_building.bid = null;
      this.new_building.floor = null;
      this.new_building.room = null;
      this.new_building.bed = null;
      this.tip1Visible = false;
      this.tip2Visible = false;
      this.tip3Visible = false;
      this.tip4Visible = false;
      this.$emit('closeDialog', "none");
    },
    handleSubmit(){
      if(this.isAdd){
        this.$emit('handleSubmit', this.new_building);
      }
      else{
        this.modify_building.bid = this.data.bid;
        this.$emit('handleSubmit', this.modify_building);
      }
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
  top: 20%;
  width: 100%;
  line-height: 2em;
  text-align: center;
  font-size: 1.1vw;
  color: #758c9e;
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

