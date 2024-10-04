<template >
  <div class="container">
    <div class="item_wrap">
      <div class="title">入住申請審批</div>
      <el-table :data="applies" v-bind:style="{'height' : auto_height}">
        <el-table-column prop="name" label="姓名" sortable>
          <template #default="scope">
            {{scope.row.name}}
          </template>
        </el-table-column>
        <el-table-column prop="studentid" label="學號" sortable>
          <template #default="scope">
            {{scope.row.studentid}}
          </template>
        </el-table-column>
        <el-table-column prop="classes" label="班級" sortable>
          <template #default="scope">
            {{scope.row.classes}}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="狀態">
          <template #default="scope">
            {{ (scope.row.status==1)? "待審批" : "已通過" }}
          </template>
        </el-table-column>
        <el-table-column prop="operation" label="操作">
          <template #default="scope">
            <button class="agree"
                    @click="handleAgree(scope.row)">通過</button>
            <button class="refuse"
                    @click="handleRefuse(scope.row)">拒絕</button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { ElNotification } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
export default {
  name: "CheckIn",
  data() {
    return {
      applies: [],
      apply: {
        type: 0,
        studentid: null,
      },
      tipVisible: false,
    }
  },
  components: {  },
  computed: {
    auto_height() {
      if(this.applies.length < 10)
        return 'auto';
      else
        return 61.3+'vh'
    }
  },
  methods: {
    handleAgree(data) {
      this.apply.studentid = data.studentid;
      axios.put("/path/apply/handle", this.apply)
               .then((response) => {
                if(response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.loadApply();
                }
                else if(response.data.code == 350) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'error',
                  });
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
    },
    handleRefuse(data) {
      this.apply.studentid = data.studentid;
      axios.put("/path/apply/refuse", this.apply)
               .then((response) => {
                if(response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.loadApply();
                }
                else if(response.data.code == 350) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'error',
                  });
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
    },
    loadApply() {
      axios.get('/path/apply/checkin')
          .then((response) => {
            this.applies = response.data.data;
            if(this.applies.length > 0) {
              ElNotification({
                title: '尚未處理所有申請',
                message: '盡速處理待審批的入住申請。',
                type: 'warning',
              })
            }
            else {
              ElNotification({
                title: '暫無待審批的申請',
                message: '您已完成所有入住申請的審批。',
                type: 'success',
              })
            }
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
    this.loadApply();
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
.title {
  position: absolute;
  top: 6%;
  width: 100%;
  text-align: center;
  font-size: 1.6vw;
  font-weight: bold;
  color: #819aaf;
}
.item_wrap {
  position: absolute;
  width: 85vw;
  height: 80vh;
  top: 5vh;
  left: 8vw;
  background-color: #bccfdf94;
  border-radius: 1.3em;
}
.agree {
  width: 4.5vw;
  height: 4vh;
  margin-right: 0.6vw;
  color: #ffffff;
  background-color: #66a3d5d6;
  border: none;
  border-radius: 0.5em;
}
.agree:hover {
  cursor: pointer;
  background-color: #4c94d0d6;
}
.refuse {
  width: 4.5vw;
  height: 4vh;
  color: #ffffff;
  background-color: #da6262db;
  border: none;
  border-radius: 0.5em;
}
.refuse:hover {
  cursor: pointer;
  background-color: #cf5151db;
}
.el-table {
  width: 80%; 
  top: 16%; 
  left: 10%;
  color: #7d8d9b;
  font-size: 1.2vw;
}
::v-deep .el-table, .el-table__expanded-cell {
  background-color: #e4ecf394 !important;
}
::v-deep .el-table th, .el-table tr, .el-table td {
  background-color: #e4ecf394 !important;
  color: #7d8d9b;
}
::v-deep .el-table__body tr, .el-table__body td {
  background-color: #e4ecf394 !important;
}
</style>
