<template>
<div class="container">
  <div class="item_wrap">
    <div class="title">夜歸紀錄</div>
    <el-table :data="records" v-bind:style="{'height' : auto_height}">
      <el-table-column prop="lateDate" label="登記日期" sortable>
        <template #default="scope">
          {{scope.row.lateDate}}
        </template>
      </el-table-column>
      <el-table-column prop="recordTime" label="登記時間">
        <template #default="scope">
          {{scope.row.recordTime}}
        </template>
      </el-table-column>
      <el-table-column prop="reason" label="夜歸原因">
        <template #default="scope">
          {{ (scope.row.reason == null)? "無" : scope.row.reason}}
        </template>
      </el-table-column>
    </el-table>
  </div>
</div>
</template>

<script>
import { ElMessage } from 'element-plus'
import axios from "axios";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

export default {
  name: "LateRecord",
  data(){
    return {
      records: [],
      studentid: null,
    }
  },
  components: { },
  computed: {
    auto_height() {
      if(this.records.length < 11)
        return 'auto';
      else
        return 61.3+'vh'
    },
  },
  methods: {
    loadRecord() {
      axios.get("/path/late/"+this.studentid)
          .then((response) => {
            if (response.data.code == 200) {
              console.log(response.data.data);
              this.records = response.data.data;
            }
          })
          .catch((error) => {
            console.log(error)
          });
    },
    interceptor() {
      if(!cookies.isKey('studentToken')){
        ElMessage({
                    message: "請重新登入",
                    type: 'warning',
                  });
        this.$router.push('/');
      }
      else {
        this.studentid = cookies.get('studentToken').id;
      }
    }
  },
  created() {
    this.interceptor();
    this.loadRecord();
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
.prompt {
  position: absolute;
  top: 45%;
  width: 100%;
  text-align: center;
  font-size: 1.4vw;
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
.el-table {
  width: 65%; 
  top: 16%; 
  left: 17.5%;
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
