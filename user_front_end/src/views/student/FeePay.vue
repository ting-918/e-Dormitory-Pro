<template >
  <div class="container">
    <div class="item_wrap" v-if="!valid">
      <div class="title">{{ this.fee.did + " 宿舍水電費用"}}</div>
      <el-table :data="feeList" v-bind:style="{'height' : auto_height}">
        <el-table-column prop="feeDate" label="日期" sortable>
          <template #default="scope">
            {{scope.row.feeDate}}
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
        <el-table-column prop="operation" label="操作">
          <template #default="scope">
            <button class="pay"
                    :disabled="(scope.row.status!=0)"
                    v-bind:style="{'cursor':((scope.row.status==0) ? 'pointer' : 'not-allowed')}"
                    @click="handleAgree(scope.row)">支付</button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="item_wrap" v-else>
    <div class="prompt">非住宿生權限不足</div>
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
  name: "FeePay",
  data() {
    return {
      feeList: [],
      fee: {
        did: null,
        feeDate: null,
      },
      valid: true,
    }
  },
  components: {  },
  computed: {
    auto_height() {
      if(this.feeList.length < 10)
        return 'auto';
      else
        return 61.3+'vh'
    },
  },
  methods: {
    handleAgree(data) {
      this.fee.did = data.did;
      this.fee.feeDate = data.feeDate;
      axios.put("/path/fee", this.fee)
               .then((response) => {
                if(response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.loadFee();
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
    },
    loadFee() {
      if(!this.valid) {
        axios.get('/path/fee/'+ this.fee.did)
          .then((response) => {
            this.feeList = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
      }
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
          this.fee.did = cookies.get('studentToken').info;
          if(this.fee.did != null && this.fee.did != '')
            this.valid = false;
      }
    }
  },
  created() {
    this.interceptor();
    this.loadFee();
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
.pay {
  width: 4.5vw;
  height: 4vh;
  margin-right: 0.6vw;
  color: #ffffff;
  background-color: #66a3d5d6;
  border: none;
  border-radius: 0.5em;
}
.pay:hover {
  cursor: pointer;
  background-color: #4c94d0d6;
}
.prompt {
  position: absolute;
  top: 45%;
  width: 100%;
  text-align: center;
  font-size: 1.4vw;
  color: #819aaf;
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
