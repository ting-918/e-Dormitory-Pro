<template>
<div class="container">
  <div class="item_wrap" v-if="!valid">
    <div class="title">{{ this.did + " 宿舍報修紀錄" }}</div>
    <el-tooltip :visible="tipVisible" effect="light" placement="top">
      <template #content>
        <span style="color: #7d8d9b">申請報修</span>
      </template>
      <button class="add-button" 
              :disabled="valid"
              @click="add" 
              @mouseover="tipVisible=true"
              @mouseleave="tipVisible=false">+</button>
    </el-tooltip>
    <el-table :data="applyList" v-bind:style="{'height' : auto_height}">
      <el-table-column prop="item" label="維修項目" sortable>
        <template #default="scope">
          {{scope.row.item}}
        </template>
      </el-table-column>
      <el-table-column prop="applytime" label="上報日期" sortable>
        <template #default="scope">
          {{scope.row.applytime}}
        </template>
      </el-table-column>
      <el-table-column prop="handletime" label="維修日期" sortable>
        <template #default="scope">
          {{ (scope.row.handletime == null)? "無" : scope.row.handletime}}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="狀態" >
          <template #default="scope">
            {{ (scope.row.handletime == null)? "待維修" : "已完成"}}
          </template>
        </el-table-column>
    </el-table>
  </div>
  <div class="item_wrap" v-else>
    <div class="prompt">非住宿生權限不足</div>
  </div>
</div>
<maintain-dialog :visible="dialogVisible"
                 :data="did"
                 @closeDialog="hiddenDialog"
                 @handleSubmit="handle">
</maintain-dialog>
</template>

<script>
import { ElMessage } from 'element-plus'
import axios from "axios";
import maintainDialog from "@/components/maintainDialog";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

export default {
  name: "MaintainApply",
  data(){
    return {
      tipVisible: false,
      dialogVisible: 'none',
      applyList: [],
      did: null,
      valid: true,
    }
  },
  components: { maintainDialog },
  computed: {
    auto_height() {
      if(this.applyList.length < 10)
        return 'auto';
      else
        return 61.3+'vh'
    },
  },
  methods: {
    add() {
      this.dialogVisible='block';
    },
    hiddenDialog() {
      this.dialogVisible='none';
    },
    handle(data) {
      axios.post("/path/maintain", data)
        .then((response) => {
          if (response.data.code == 200) {
            ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
            this.loadApply();
          }
        })
        .catch((error) => {
          console.log(error)
        });
      this.loadApply();
      this.hiddenDialog();
    },
    loadApply() {
      if(!this.valid){
        axios.get("/path/maintain/"+this.did)
          .then((response) => {
            if (response.data.code == 200) {
              console.log(response.data.data);
              this.applyList = response.data.data;
            }
          })
          .catch((error) => {
            console.log(error)
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
        this.did = cookies.get('studentToken').info;
        if(this.did != null && this.did != '')
          this.valid = false;
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
  width: 70%; 
  top: 16%; 
  left: 15%;
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
