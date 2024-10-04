<template>
  <router-view></router-view>
</template>


<script>
export default {
  name: "App",
  data() {
    return{}
  }
}
</script>

<style scoped>
</style>
<style>
#app{
  height: 100%;
}
.el-menu--collapse .el-menu .el-submenu, .el-menu--popup{
  min-width: 10vw !important;
  border: none !important;
}
</style>
