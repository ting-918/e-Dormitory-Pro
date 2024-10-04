<template >
  <div class="container">
    <div class="item_wrap">
      <div class="title">{{ this.setTitle }}</div>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">發起繳費通知</span>
        </template>
        <button class="add-button" 
                :disabled="status"
                v-bind:style="{'cursor':(this.status ? 'not-allowed' : 'pointer')}"
                @click="add" 
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false">+</button>
      </el-tooltip>
      <el-table :data="fees" v-bind:style="{'height' : auto_height}">
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
import { ElNotification } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import qs from "qs";
import axios from "axios";
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
export default {
  name: "Fee",
  data() {
    return {
      fees: [],
      idList: [],
      status: true,
      tipVisible: false,
    }
  },
  components: {  },
  computed: {
    setTitle() {
      let year;
      let month;
      var myDate = new Date();
      if(myDate.getMonth() == 0){
        month = 12;
        year = (myDate.getFullYear()-1).toString();
      }
      else{
        month = myDate.getMonth();
        year = (myDate.getFullYear()).toString();
      }
      return year+"年"+month+"月份"+" 水電信息";
    },
    auto_height() {
      if(this.fees.length < 11)
        return 'auto';
      else
        return 61.3+'vh'
    }
  },
  methods: {
    add() {
      axios.post('/path/fee', this.didList)
          .then((response) => {
            if(response.data.code == 200)
              ElMessage({
                message: response.data.msg,
                type: 'success',
              });
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
      this.loadFees();
    },
    loadFees() {
      axios.get('/path/fee')
          .then((response) => {
            this.fees = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    check() {
      axios.get('/path/fee/check')
          .then((response) => {
            if(response.data.code == 201) {
              ElNotification({
                title: '尚未發起繳費通知',
                message: '點擊按鈕，盡速發起本季繳費通知。',
                type: 'warning',
              })
              this.didList = response.data.data;
              this.status = false;
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
    this.loadFees();
    this.check();
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
