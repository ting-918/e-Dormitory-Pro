<template>
  <button type="btn"
          class="mybutton"
          :disabled="disable"
          v-bind:style="{'top': top + '%', 'right': right + '%', 'cursor': getStatus, 'background-image': getBackground}"
  >
    {{ btn_name }}
  </button>
</template>

<script>
export default {
  name: "myButton",
  props: ['btn_name', 'top', 'right', 'disable'],
  computed: {
    getStatus(){
      if(this.disable){
        return "not-allowed";
      }
      return "pointer";
    },
    getBackground(){
      if(this.disable){
        return "linear-gradient(to right,#B8BABB,#999EA9,#A6B3BD,#B8BABB)"
      }
      else{
        return "linear-gradient(to right,#98c4eae8,#b2b4debc,#a8b8e0bc,#98c4eae8)"
      }
    }
  },
}
</script>

<style scoped>
.mybutton {
  position: absolute;
  width: 6vw;
  height: 4.5vh;
  border: none;
  border-radius: 0.5em;
  line-height: 4.5vh;
  text-align: center;
  font-size: 1vw;
  color: white;
  /* 背景渐变色大小 */
  background-size: 600%;
  animation: colorful 30s infinite;
}
.mybutton:hover {
  animation: none;
}
@keyframes colorful {
  100% {
    background-position: -600% 0;
  }
}
</style>
