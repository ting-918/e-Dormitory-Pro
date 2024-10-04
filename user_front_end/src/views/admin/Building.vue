<template >
  <div class="container">
    <div class="item_wrap" v-bind:style="{'height' : auto_height + 'vh'}">
      <el-tooltip :visible="tipVisible" effect="light" placement="top">
        <template #content>
          <span style="color: #7d8d9b">新增樓棟</span>
        </template>
        <button class="add-button" 
                @click="add" 
                @mouseover="tipVisible=true"
                @mouseleave="tipVisible=false">+</button>
      </el-tooltip>
      <template v-for="building in buildings">
        <building-item @modifyClick="modify"
                       @deleteClick="check"
                       v-bind:value="building">{{building}}
        </building-item>
      </template>
    </div>
  </div>
  <building-dialog :visible="dialogVisible"
                   :title="dialogTitle"
                   :is-add="isAdd"
                   :existList="buildings"
                   :data="building"
                   @closeDialog="hiddenDialog"
                   @handleSubmit="handle">
  </building-dialog>
  <check-box :visible="boxVisible"
             :value="this.building.bid"
             :description="description"
             @handleDecline="hiddenBox"
             @handleSubmit="handleDelete">
  </check-box>
</template>

<script>
import BuildingItem from "@/components/buildingItem";
import BuildingDialog from "@/components/buildingDialog";
import CheckBox from "@/components/checkBox";
import { ElMessage } from 'element-plus'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();
import axios from "axios";
import qs from "qs";
axios.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';
export default {
  name: "Building",
  data() {
    return {
      buildings: [],
      building: {
        bid: null,
        pwd: null,
        amount: null,
      },
      dialogTitle: null,
      isAdd: null,
      tipVisible: false,
      dialogVisible: "none",
      boxVisible: "none",
    }
  },
  components: { BuildingDialog, BuildingItem, CheckBox },
  computed: {
    auto_height(){
      if(this.buildings == null || this.buildings == [] || this.buildings.length < 4){
        return 80;
      }
      return (this.buildings.length)*18+3;
    },
    description() {
      return "確定要刪除 "+this.building.bid+"宿舍樓 嗎？本操作不可逆"
    }
  },
  methods: {
    modify(data) {
        this.isAdd=false;
        this.dialogTitle="修改密碼";
        this.building=data[1];
        this.dialogVisible=data[0];
    },
    add() {
        this.isAdd=true;
        this.dialogTitle="新增樓棟";
        this.dialogVisible='block';
    },
    check(data) {
        this.building=data[1];
        this.boxVisible=data[0];
    },
    handleDelete(data){
      axios.delete("/path/building/delete/"+data)
           .then((response) => {
            if(response.data.code == 200){
              ElMessage({
                message: response.data.msg,
                type: 'success',
              });
              this.hiddenBox('none');
              this.loadBuildings();
            }
            }).catch((error) => {
              console.log(error);
            });
    },
    hiddenDialog(data) {
      this.dialogVisible=data;
    },
    hiddenBox(data) {
      this.boxVisible=data;
    },
    handle(data) {
        if(this.isAdd) {
          data['floor'] = parseInt(data['floor']);
          data['room'] = parseInt(data['room']);
          data['bed'] = parseInt(data['bed']);
          axios.post("/path/building", data)
               .then((response) => {
                if (response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.hiddenDialog('none');
                  this.loadBuildings();
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
        }
        else {
          axios.put("/path/building/edit/", data)
               .then((response) => {
                if (response.data.code == 200) {
                  ElMessage({
                    message: response.data.msg,
                    type: 'success',
                  });
                  this.hiddenDialog('none');
                  this.loadBuildings();
                }
                console.log(response);
               })
               .catch((error) => {
                 console.log(error);
               });
        }
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
</style>
