<template >
  <div class="container">
    <div class="item_wrap">
      <div class="title">報修申請紀錄</div>
      <el-table :data="records" v-bind:style="{'height' : auto_height}">
        <el-table-column prop="applytime" label="報修日期" sortable fixed>
          <template #default="scope">
            {{scope.row.applytime}}
          </template>
        </el-table-column>
        <el-table-column prop="did" label="宿舍" sortable fixed width="100">
          <template #default="scope">
            {{scope.row.did}}
          </template>
        </el-table-column>
        <el-table-column prop="item" label="報修項目" sortable>
          <template #default="scope">
            {{scope.row.item}}
          </template>
        </el-table-column>
        <el-table-column prop="reason" label="情況描述">
          <template #default="scope">
            {{ (scope.row.reason == null)? "無" : scope.row.reason}}
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="聯繫電話">
          <template #default="scope">
            {{ (scope.row.phone == null)? "無" : scope.row.phone}}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="狀態" fixed="right" width="200">
          <template #default="scope">
            {{ (scope.row.handletime != null)? scope.row.handletime+" 完成" : "待維修" }}
          </template>
        </el-table-column>
        <el-table-column prop="operation" label="操作" fixed="right" width="110">
          <template #default="scope">
            <button class="complete"
                    :disabled="(scope.row.handletime != null)"
                    v-bind:style="{'cursor':((scope.row.handletime == null) ? 'pointer' : 'not-allowed')}"
                    @click="handle(scope.row)">完成</button>
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
  name: "Record",
  data() {
    return {
      records: [],
      tipVisible: false,
    }
  },
  components: {  },
  computed: {
    auto_height() {
      if(this.records.length < 10)
        return 'auto';
      else
        return 61.3+'vh'
    }
  },
  methods: {
    handle(data) {
      axios.put("/path/maintain/"+data.id)
               .then((response) => {
                if(response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.loadRecord();
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
    },
    loadRecord() {
      axios.get('/path/maintain')
          .then((response) => {
            this.records = response.data.data;
            let recordList = response.data.data.filter((row)=>((row['handletime']==null)));
            console.log(recordList);
            if(recordList.length > 0) {
              ElNotification({
                title: '尚未處理所有報修項目',
                message: '盡速處理待維修的報修申請。',
                type: 'warning',
              })
            }
            else {
              ElNotification({
                title: '暫無待維修的項目',
                message: '您已完成所有報修申請的維修。',
                type: 'success',
              })
            }
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    interceptor() {
        if(!cookies.isKey('maintainToken')){
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
.item_wrap {
  position: absolute;
  width: 85vw;
  height: 80vh;
  top: 5vh;
  left: 8vw;
  background-color: #bccfdf94;
  border-radius: 1.3em;
}
.complete {
  width: 4.5vw;
  height: 4vh;
  color: #ffffff;
  background-color: #66a3d5d6;
  border: none;
  border-radius: 0.5em;
}
.complete:hover {
  cursor: pointer;
  background-color: #4c94d0d6;
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
