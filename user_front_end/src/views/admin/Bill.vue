<template >
  <div class="container">
    <div class="item_wrap">
      <div class="title">{{ this.setTitle }}</div>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">發起繳費通知</span>
        </template>
        <button class="add-button" 
                style="top: 3.5vh;"
                :disabled="status"
                v-bind:style="{'cursor':(this.status ? 'not-allowed' : 'pointer')}"
                @click="add" 
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false">+</button>
      </el-tooltip>
      <el-tooltip :visible="tip1Visible" effect="light" placement="bottom">
        <template #content>
          <span style="color: #7d8d9b">下載未繳費名單</span>
        </template>
        <button class="add-button" 
                style="top: 10.5vh; font-size: 1.5vw;"
                :disabled="valid2download"
                @click="downloadList" 
                @mouseover="tip1Visible=true"
                @mouseleave="tip1Visible=false">⬇
        </button>
      </el-tooltip>
      <el-table :data="bills" v-bind:style="{'height' : auto_height}">
        <el-table-column prop="year" label="年度" sortable>
          <template #default="scope">
            {{scope.row.year}}
          </template>
        </el-table-column>
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
        <el-table-column prop="price" label="費用" sortable>
          <template #default="scope">
            {{scope.row.price}}
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
  <bill-dialog :visible="dialogVisible"
               @closeDialog="hiddenDialog"
               @handleSubmit="handle">
  </bill-dialog>
</template>

<script>
import billDialog from '@/components/billDialog.vue';
import { ElMessage } from 'element-plus'
import { ElNotification } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
export default {
  name: "Bill",
  data() {
    return {
      bills: [],
      bill: {
        price: null,
        year: null,
      },
      status: true,
      tipVisible: false,
      tip1Visible: false,
      dialogVisible: "none",
    }
  },
  components: { billDialog },
  computed: {
    setTitle() {
      let year;
      var myDate = new Date();
      if(myDate.getMonth()+1 < 7)
        year = (myDate.getFullYear()-1).toString();
      else
        year = (myDate.getFullYear()).toString();
      
      return year+"年度繳費信息";
    },
    valid2download() {
      return !((this.bills.filter((row)=>([0].includes(row['status'])))).length > 0)
    },
    auto_height() {
      if(this.bills.length < 11)
        return 'auto';
      else
        return 61.3+'vh'
    },
  },
  methods: {
    add() {
        this.dialogVisible='block';
    },
    hiddenDialog(data) {
      this.dialogVisible=data;
    },
    downloadList() {
      let xlsx = require('xlsx');
      let filterData = this.bills.filter((row)=>([0].includes(row['status'])));
      let data = filterData.map(item => ({
        '學號': item.studentid,
        '姓名': item.name,
        '班級': item.classes,
        '住宿費': item.price,
        '狀態': "未繳納",
      }));
      let worksheet = xlsx.utils.json_to_sheet(data);
      let workbook = xlsx.utils.book_new();
      xlsx.utils.book_append_sheet(workbook, worksheet, '名單');
      xlsx.writeFile(workbook, '未繳費名單.xlsx')
      ElNotification({
                title: '已完成下載',
                message: '未繳費名單已下載完成，請查收。',
                type: 'success',
              })
    },
    handle(data) {
      this.bill.price = parseInt(data[0]);
      this.bill.year = data[1];
      axios.post('/path/bill', this.bill)
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
      this.dialogVisible='none';
      this.$router.push('/bill');
    },
    loadBills() {
      axios.get('/path/bill')
          .then((response) => {
            this.bills = response.data.data;
            console.log(response);
          }).catch((error) => {
            console.log(error);
      });
    },
    check() {
      axios.get('/path/bill/check')
          .then((response) => {
            if(response.data.code == 201) {
              ElNotification({
                title: '尚未發起繳費通知',
                message: '點擊按鈕，盡速發起本年度繳費通知。',
                type: 'warning',
              })
              this.status = false;
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
    this.loadBills();
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
