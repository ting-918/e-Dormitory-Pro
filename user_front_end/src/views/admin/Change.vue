<template>
  <div class="container">
    <el-input v-model="search" placeholder="搜索" />
    <div class="item_wrap" v-bind:style="{'height' : auto_height + 'vh'}">
      <template v-for="student in filterData">
        <student-item @handleClick="modify"
                      :btn_name="btn_name"
                      v-bind:value="student">{{student}}
        </student-item>
      </template>
    </div>
  </div>
  <change-dialog :visible="dialogVisible"
                 :title="dialogTitle"
                 :existBuildings="buildings"
                 :validRoom="validRoom"
                 :data="student"
                 @closeDialog="hiddenDialog"
                 @handleSubmit="handle">
  </change-dialog>
</template>

<script>
import StudentItem from "@/components/studentItem";
import ChangeDialog from "@/components/changeDialog";
import { ElMessage } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
import qs from "qs";
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
export default {
  name: "Change",
  data() {
    return {
      btn_name: "寢室調動",
      buildings: [],
      validRoom: [],
      students: [],
      student: {
        studentid: null,
        did: null
      },
      search: null,
      dialogTitle: "寢室調動",
      dialogVisible: "none",
    }
  },
  components: { ChangeDialog, StudentItem},
  computed: {
    auto_height(){
      if(this.students == null || this.students == [] || this.students.length < 4){
        return 80;
      }
      return (this.students.length)*18+3;
    },
    filterData() {
      return (this.search==null||this.search=='')? this.students: this.students.filter((row)=>(row['studentid'].includes(this.search)) || (row['name'].includes(this.search)) || (row['did'].includes(this.search)));
    }
  },
  methods: {
    hiddenDialog(data) {
      this.dialogVisible=data;
    },
    modify(data) {
        this.student=data[1];
        axios.get('/path/dormitory/'+data[1].gender)
          .then((response) => {
            this.validRoom = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
        });
        this.dialogVisible=data[0];
    },
    handle(data){
      console.log(data);
      axios.put("/path/student/change", data)
           .then((response) => {
              if (response.data.code == 200) {
                ElMessage({
                  message: response.data.msg,
                  type: 'success',
                });
                this.loadStudents();
              }
              console.log(response);
           })
           .catch((error) => {
              console.log(error);
           });
    },
    loadStudents() {
      axios.get('/path/student')
          .then((response) => {
            this.students = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    loadBuildings() {
      axios.get('/path/building')
          .then((response) => {
            this.buildings = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    interceptor() {
        if(!cookies.isKey('adminToken')){
          ElMessage({
                    message: "請重新登入",
                    type: 'warning',
                  });
          this.$router.push('/');
        }
    }
  },
  created() {
    this.interceptor();
    this.loadStudents();
    this.loadBuildings();
  }
}
</script>

<style scoped>
.container {
  overflow: auto;
  width: 100vw;
  height: 90vh;
  right: 0;
  bottom: 0;
  position: fixed; /* 相對於瀏覽器窗口固定 */
}
.add-button {
  width: 3vw;
  height: 5vh;
  right: 3%;
  top: 3.5vh;
  position: absolute;
  border: none;
  border-radius: 50%;
  background-color: #94badad2;
  font-size: 2vw;
  color: white;
}
.add-button:hover {
  cursor: pointer;
}
.item_wrap {
  position: absolute;
  width: 85vw;
  top: 5vh;
  left: 8vw;
  background-color: #bccfdf94;
  border-radius: 1.3em;
}
.el-input {
  position: absolute;
  left: 30%;
  width: 40%;
  height: 4.5%;
  margin-bottom: 3vh;
  /*默认边框颜色*/
  --el-input-border-color: transparent;
  /*背景颜色*/
  --el-input-bg-color: #bccfdf7b;
  /*获取焦点后的边框颜色*/
  --el-input-focus-border-color: transparent;
  /*鼠标悬停边框颜色*/
  --el-input-hover-border-color: transparent;

}
:deep(.el-input__inner){
  font-size: 1vw;
  color: #6d869a;
}
:deep(.el-input__wrapper) {
  border-radius: 0.8em;
}
</style>
