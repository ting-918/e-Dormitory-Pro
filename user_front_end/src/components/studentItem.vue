<template>
  <div class="wrapper">
    <el-tooltip :visible="tip1Visible" effect="light" placement="top-start">
      <template #content>
        <span style="color: #7d8d9b">姓名</span>
      </template>
      <div class="title" 
           @mouseover="tip1Visible=true"
           @mouseleave="tip1Visible=false"
      >{{ value.name }}</div>
    </el-tooltip>
    <el-tooltip :visible="tip2Visible" effect="light" placement="top-start">
      <template #content>
        <span style="color: #7d8d9b">學號</span>
      </template>
      <div class="conetent"
           style="left: 33%; top: 40%;"
           @mouseover="tip2Visible=true"
           @mouseleave="tip2Visible=false"
      >{{ value.studentid }}</div>
    </el-tooltip>
    <el-tooltip :visible="tip3Visible" effect="light" placement="top-start">
      <template #content>
        <span style="color: #7d8d9b">所屬寢室</span>
      </template>
      <div class="conetent"
           style="left: 52%; top: 38%;"
           @mouseover="tip3Visible=true"
           @mouseleave="tip3Visible=false"
      >{{ value.bid }} 樓 {{ value.did.replace(value.bid,'') }} 室</div>
    </el-tooltip>
    <div class="icon"
         v-if="value.gender"
         style="width: 4%; background-color: #a6cceb;">男
    </div>
    <div class="icon"
         v-else
         style="width: 4%; background-color: #f18585ab;">女
    </div>
    <el-tooltip :visible="tip4Visible" effect="light" placement="top">
      <template #content>
        <span style="color: #7d8d9b">{{ this.tipContent }}</span>
      </template>
      <my-button :btn_name="btn_name"
                :disable="checkLateValid"
                :top="top" 
                :right="right"
                @click="handle"
                @mouseover="tip4Visible=checkLateValid"
                @mouseleave="tip4Visible=false"
      ></my-button>
    </el-tooltip>
  </div>
</template>

<script>
import { Picture as IconPicture } from '@element-plus/icons-vue'
import MyButton from "@/components/myButton";
export default {
  name: 'studentItem',
  props: ['btn_name', 'value', 'existList'],
  data() {
    return{
      btn_name: this.btn_name,
      top: 35,
      right: 15,
      tip1Visible: false,
      tip2Visible: false,
      tip3Visible: false,
      tip4Visible: false,
      tipContent: null,
    }
  },
  components: {MyButton, IconPicture},
  computed: {
    checkLateValid() {
      if(this.btn_name == "登記夜歸") {
        var myDate = new Date();
        var myTime = myDate.getHours();
        //在門禁時間(23:00～05:00)才能登記夜歸
        if(myTime < 23 && myTime > 5) {
          this.tipContent = "門禁時間23:00~05:00";
          return true;
        }
        if(this.existList.includes(this.value.studentid)) {
          this.tipContent = "今日已登記";
          return true;
        }
      }
      return false;
    }
  },
  methods: {
    handle() {
      this.$emit('handleClick', ['block', this.value]);
    },
  },created() {
  }
}
</script>
<style scoped>
p {
  font-size: 1vw;
  font-weight: bold;
  color: #6d869a;
}
.wrapper {
  position: relative;
  width: 70vw;
  height: 15vh;
  margin-top: 3vh;
  margin-left: 7vw;
  background-color: #f6fcffd1;
  border-radius: 1.3em;
  transition-property: transform;
  transition-duration: 0.5s;
}
.wrapper:hover {
  transition-duration: 0.5s;
  transform: scale(103%);
}
.title {
  position: absolute;
  top: 28%;
  left: 15%;
  width: 15%;
  text-align: left;
  font-size: 1.5vw;
  font-weight: bold;
  color: #6d869a;
}
.conetent {
  position: absolute;
  width: 15%;
  text-align: left;
  font-size: 1.2vw;
  font-weight: bold;
  color: #6d869a;
}
.icon {
  position: absolute;
  top: 58%;
  left: 15%;
  height: 20%;
  font-size: 1.05vw;
  text-align: center;
  border-radius: 0.5em;
  color: white;
}
section {
  position: absolute;
  left: 33%;
  width: 52%;
  line-height: 2em;
  text-align: left;
  font-size: 1.1vw;
  color: #7d8d9b;
}

</style>
