<template >
  <div class="container">
    <div class="item_wrap">
      <div class="title">{{ this.bid + "宿舍樓 水電費用" }}</div>
      <el-table :data="records" v-bind:style="{'height' : auto_height}">
        <el-table-column prop="feeDate" label="日期" sortable>
          <template #default="scope">
            {{scope.row.feeDate}}
          </template>
        </el-table-column>
        <el-table-column prop="did" label="宿舍" sortable>
          <template #default="scope">
            {{scope.row.did}}
          </template>
        </el-table-column>
        <el-table-column prop="waterFee" label="水費" sortable>
          <template #default="scope">
            {{scope.row.waterFee}}
          </template>
        </el-table-column>
        <el-table-column prop="electricFee" label="電費" sortable>
          <template #default="scope">
            {{scope.row.electricFee}}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="狀態" sortable>
          <template #default="scope">
            {{ (scope.row.status==0)? "未繳納" : "已繳清" }}
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
export default {
  name: "BuildingRecord",
  data() {
    return {
      bid: null,
      records: [],
    }
  },
  components: {  },
  computed: {
    auto_height() {
      if(this.records.length < 11)
        return 'auto';
      else
        return 61.3+'vh'
    }
  },
  methods: {
    loadRecord() {
      axios.get('/path/fee/building/'+this.bid)
          .then((response) => {
            this.records = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    interceptor() {
      if(!cookies.isKey('managerToken')){
        ElMessage({
                    message: "請重新登入",
                    type: 'warning',
                  });
        this.$router.push('/');
      }
      else {
        this.bid = cookies.get('managerToken').id;
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
