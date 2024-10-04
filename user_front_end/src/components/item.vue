<template>
  <div class="wrapper">
    <div class="build-title" v-if="value.bid != null && value.bid !== ''">{{ value.bid }} 宿舍樓</div>
    <div class="build-pwd" v-if="value.pwd != null && value.pwd !== ''">密碼： {{ value.pwd }}</div>
    <my-button :btn_name="btn_name"
               :top="top" :right="right"
               :disable="isDisable"
               @click="handle"
    ></my-button>
  </div>
</template>

<script>
import MyButton from "@/components/myButton";
export default {
  name: 'item',
  props: ['btn_name', 'btn_top', 'btn_right', 'value'],
  data() {
    return{
      btn_name: this.btn_name,
      top: this.btn_top,
      right: this.btn_right,
    }
  },
  components: {MyButton},
  computed: {
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
.build-title {
  position: absolute;
  top: 37%;
  left: 12%;
  width: 25%;
  text-align: left;
  font-size: 1.5vw;
  font-weight: bold;
  color: #6d869a;
}
.build-pwd {
  position: absolute;
  top: 40%;
  left: 38%;
  width: 25%;
  text-align: left;
  font-size: 1.25vw;
  font-weight: bold;
  color: #848c92;
}
</style>
