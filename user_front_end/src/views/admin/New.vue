<template>
  <div class="container">
    <div class="item_wrap">
      <el-upload
          ref="upload"
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          accept=".xlsx"
          :on-change="handleFile"
          :limit="1"
        >
        <my-button :btn_name="btn_name"
                   :top="top"
                   :right="right"
                   :disable="false"
                   v-if="getStatus">
        </my-button>
      </el-upload>
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">下載規範的名單模板</span>
        </template>
        <button class="download-button" 
                @click="downloadTemplate" 
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false">⬇
        </button>
      </el-tooltip>
      <div class="prompt" v-if="getStatus">請點擊按鈕, 上傳規範的名單, 進行新生入住工作。</div>
      <div class="title" v-else>新生分配名單</div>
      <el-table :data="assignedList" 
                v-bind:style="{'height' : auto_height}" 
                :cell-style="cellStyle" 
                v-else>
        <el-table-column prop="studentid" label="學號" sortable>
          <template #default="scope">
            {{scope.row.studentid}}
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" sortable>
          <template #default="scope">
            {{scope.row.name}}
          </template>
        </el-table-column>
        <el-table-column prop="classes" label="班級" sortable>
          <template #default="scope">
            {{scope.row.classes}}
          </template>
        </el-table-column>
        <el-table-column prop="gender" label="性別" sortable>
          <template #default="scope">
            {{ scope.row.gender }}
            <div v-if="scope.row.gender"
                class="icon"
                style="background-color: #a6cceb;">男
            </div>
            <div v-else
                class="icon"
                style="background-color:  #f18585ab;">女
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="did" label="分配宿舍" sortable>
          <template #default="scope">
            {{ scope.row.did }}
          </template>
        </el-table-column>
      </el-table>
    </div>  
  </div>
</template>
<script>
import Item from "@/components/item";
import MyButton from "@/components/myButton";
import MyDialog from "@/components/myDialog";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import { ElMessage } from 'element-plus'
import { ElNotification } from 'element-plus'
import axios from "axios";
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
export default {
  name: "New",
  data() {
    return {
      students: [],
      assignedList: [],
      fileName: null,
      btn_name: "上傳名單",
      top: 10,
      right: 45,
      tipVisible: false,
    }
  },
  computed: {
    auto_height() {
      if(this.assignedList.length < 10)
        return 'auto';
      else
        return 61.3+'vh'
    },
    getStatus() {
      return !(this.assignedList.length > 0);
    }
  },
  components: {Item, MyDialog, MyButton},
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      //動特修改單元格字體顏色
      if (columnIndex === 3) {
        return {color: '#e4ecf394'};
      }
      else return {color: '#7d8d9b'};
    },
    handleFile(file) {
      const properties = ["学号", "姓名", "性别", "班级"];
      const headers = ["studentid", "name", "gender", "classes"];
      let redundant = [];
      let xlsx = require('xlsx');
      let reader = new FileReader();
      var existList = this.students.map(i=>{return i.studentid;});
      reader.readAsArrayBuffer(file.raw);
      if(file.name != this.fileName){
        reader.onload=()=>{ 
          this.fileName = file.name;
          let target = xlsx.read(reader.result,{type:"array"});
          let sheet = target.Sheets[target.SheetNames[0]];
          let data = xlsx.utils.sheet_to_json(sheet);
          let keys = Object.keys(data[0]);
          // 確認屬性是否齊全無誤
          let check = properties.every(property=>{ 
                        if(!keys.includes(property)){
                          ElMessage({
                            message: "文件格式不規範",
                            type: 'error',
                          });
                          //清除已上傳文件
                          file = null;
                          //跳出循環
                          return false;
                        }
                        return true;
                      });
          if(check){
            //找出無用屬性並紀錄
            keys.forEach(key=>{
              if(!properties.includes(key)){
                redundant.push(key);
              }
            });
            data.forEach((item)=>{
              //將無用屬性的值設為undefined,以便stringify時被刪除
              redundant.forEach((key)=>{ item[key]=undefined; });
              //檢查學號是否重複
              let id = item['学号'];
              if(existList.includes(id)){
                item['学号'] = undefined;
              }
              else
                existList.push(id);
            });
            //過濾不合法的數據
            let validData = data.filter((row)=>([0, 1].includes(row['性别'])) && (row['姓名']!=undefined) && (/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z])/.test(row['姓名'])) && (row['学号']!=undefined) && (/^([a-zA-Z0-9])/.test(row['学号'])) && (row['班级']!=undefined) && (/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z])/.test(row['班级'])));
            //轉換屬性名稱,以便後端識別
            var temp = xlsx.utils.json_to_sheet(validData, {header:properties});
            const readyData = xlsx.utils.sheet_to_json(temp, {header:headers, range:1});
            console.log(readyData);
            //存在合法數據則繼續操作
            if(validData!='') {
              axios.post("/path/student", readyData)
                    .then((response) => {
                    this.assignedList = response.data.data;
                    if(response.data.code == 350)
                      ElMessage({
                        message: response.data.msg,
                        type: 'warning',
                      });
                    if(response.data.code == 200)
                      ElMessage({
                        message: response.data.msg,
                        type: 'success',
                      });
                    if(this.assignedList.length > 0) 
                      this.downloadAssignedList();
                  }).catch((error) => {
                    console.log(error);
              });
            }
            else {
              ElMessage({
                message: "上傳文件不存在合法數據",
                type: 'error',
              });
              this.$router.push('/new');
            }
          }
        }
      }
    },
    downloadAssignedList() {
      let xlsx = require('xlsx');
      let data = this.assignedList.map(item => ({
        '學號': item.studentid,
        '姓名': item.name,
        '性別': (item.gender==0)? "女" : "男",
        '班級': item.classes,
        '分配宿舍': item.did,
        '默認密碼': item.pwd,
      }));
      let worksheet = xlsx.utils.json_to_sheet(data);
      let workbook = xlsx.utils.book_new();
      xlsx.utils.book_append_sheet(workbook, worksheet, '名單');
      xlsx.writeFile(workbook, '新生分配名單.xlsx')
      ElNotification({
                title: '已完成下載',
                message: '新生分配名單自動下載完成，請查收。',
                type: 'success',
              })
    },
    downloadTemplate() {
      let a = document.createElement("a");
      a.href = "./static/uploadTemplate.xlsx"; //文件資源路徑
      a.download = "名單模板.xlsx";             //下載後顯示的文件名
      a.style.display = "none";
      document.body.appendChild(a);
      a.click();
      a.remove();
    },
    loadStudents() {
      axios.get('/path/student/all')
           .then((response) => {
            this.students = response.data.data;
           }).catch((error) => {
            console.log(error);
      });
    },
    interceptor() {
        if(!cookies.isKey('adminToken')) {
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
.download-button {
  width: 3vw;
  height: 5vh;
  right: 3%;
  top: 3.5vh;
  position: absolute;
  border: none;
  border-radius: 50%;
  background-color: #94bbdabd;
  font-size: 1.7vw;
  color: rgba(255, 255, 255, 0.895);
}
.download-button:hover {
  cursor: pointer;
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
.prompt {
  position: absolute;
  top: 45%;
  width: 100%;
  text-align: center;
  font-size: 1.2vw;
  color: #819aaf;
}
.icon {
  position: absolute;
  left: 3%;
  top: 10%;
  width: 25%;
  height: 75%;
  font-size: 1.05vw;
  display: flex;
  /*水平置中*/
  justify-content: center;
  /*垂直置中*/
  align-items: center;
  border-radius: 0.5em;
  color: white;
}
.el-table {
  width: 80%; 
  top: 11%; 
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
