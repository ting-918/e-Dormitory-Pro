<template>
  <div class="wrapper">
    <div class="item-title" v-if="value.bid != null && value.bid !== ''">{{ value.bid }} 宿舍樓</div>
    <div class="item-pwd" v-if="value.pwd != null && value.pwd !== ''">密碼： {{ value.pwd }}</div>
    <my-button :btn_name="btn1_name"
               :top="top" 
               :right="right1"
               @click="handleModify"
    ></my-button>
    <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">該樓棟尚有 {{ value.amount }} 人居住</span>
        </template>
        <my-button :btn_name="btn2_name"
                   :top="top" 
                   :right="right2"
                   :disable="deleteInvalid"
                   @click="handleDelete"
                   @mouseover="tipVisible=(true && deleteInvalid)"
                   @mouseleave="tipVisible=false"
        ></my-button>
    </el-tooltip>
  </div>
</template>

<script>
import MyButton from "@/components/myButton";
export default {
  name: 'buildingItem',
  props: ['value'],
  data() {
    return{
      btn1_name: "修改密碼",
      btn2_name: "刪除樓棟",
      top: 35,
      right1: 23,
      right2: 10,
      tipVisible: false,
    }
  },
  components: {MyButton},
  computed: {
    deleteInvalid() {
      return this.value.amount > 0;
    },
  },
  methods: {
    handleModify() {
      this.$emit('modifyClick', ['block', this.value]);
    },
    handleDelete() {
      this.$emit('deleteClick', ['block', this.value]);
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
.item-title {
  position: absolute;
  top: 37%;
  left: 12%;
  width: 25%;
  text-align: left;
  font-size: 1.5vw;
  font-weight: bold;
  color: #6d869a;
}
.item-pwd {
  position: absolute;
  top: 40%;
  left: 38%;
  width: 25%;
  text-align: left;
  font-size: 1.25vw;
  font-weight: bold;
  color: #848c92;
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
