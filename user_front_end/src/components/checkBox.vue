<template>
  <div class="dialog" v-bind:style="{'display':visible}">
    <section>{{ description }}</section>
    <button class="submit"
            @click="handleSubmit">確定
    </button>
    <button class="decline"
            @click="hidden">取消
    </button>
  </div>
</template>

<script>
export default {
  name: "checkBox",
  data(){
    return{
    }
  },
  props: ['visible','value','description'],
  methods: {
    hidden(){
      this.$emit('handleDecline', "none");
    },
    handleSubmit(){
      this.$emit('handleSubmit', this.value);
    },
  }
}
</script>

<style scoped>
/*彈窗*/
.dialog {
  width: 30vw;
  height: 20vh;
  left: 35vw;
  top: 25vh;
  position: fixed; /* 相對於瀏覽器窗口固定 */
  background: rgba(251, 217, 216, 0.839);
  background: linear-gradient(135deg, rgba(251, 217, 216, 0.839) 0%, rgba(194, 219, 239, 0.919) 100%);
  border-radius: 1.3em;
  box-shadow: -5px 5px 12px #6a7f90c3;
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
  width: 15%;
  height: 20%;
  left: 35%;
  bottom: 25%;
  border: none;
  border-radius: 0.5em;
  background-color: #94badad2;
  color: white;
  font-size: 1vw;
}
.submit:hover {
  cursor: pointer;
}
.decline {
  position: absolute;
  width: 15%;
  height: 20%;
  left: 55%;
  bottom: 25%;
  border: none;
  border-radius: 0.5em;
  background-color: #df6165d2;
  color: white;
  font-size: 1vw;
}
.decline:hover {
  cursor: pointer;
}

</style>

