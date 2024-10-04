import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/Login";

import AdminLayOut from "@/views/admin/LayOut";
import Building from "@/views/admin/Building";
import New from "@/views/admin/New";
import Change from "@/views/admin/Change";
import Bill from "@/views/admin/Bill";
import CheckIn from "@/views/admin/CheckIn";
import CheckOut from "@/views/admin/CheckOut";

import StudentLayOut from "@/views/student/LayOut";
import BillPay from "@/views/student/BillPay";
import FeePay from "@/views/student/FeePay";
import LateRecord from "@/views/student/LateRecord";
import CheckInApply from "@/views/student/CheckInApply";
import CheckOutApply from "@/views/student/CheckOutApply";
import MaintainApply from "@/views/student/MaintainApply";

import ManagerLayOut from "@/views/manager/LayOut";
import Late from "@/views/manager/Late";
import BuildingRecord from "@/views/manager/BuildingRecord";
import FeeRecord from "@/views/manager/FeeRecord";

import MaintainLayOut from "@/views/maintain/LayOut";
import Fee from "@/views/maintain/Fee";
import Record from "@/views/maintain/Record";

const routes = [
  {
    path: '/',
    redirect: '/login',
  },{
    path: '/login',
    name: 'Login',
    component: Login,
  },{
    path: '/student',
    name: 'StudentLayOut',
    component: StudentLayOut,
    children: [{
      path: '/student',
      redirect: '/bill/pay',
    },{
      path: '/bill/pay',
      name: 'BillPay',
      component: BillPay,
    },{
      path: '/fee/pay',
      name: 'FeePay',
      component: FeePay,
    },{
      path: '/late/record',
      name: 'LateRecord',
      component: LateRecord,
    },{
      path: '/apply/0',
      name: 'CheckInApply',
      component: CheckInApply,
    },{
      path: '/apply/1',
      name: 'CheckOutApply',
      component: CheckOutApply,
    },{
      path: '/apply/maintain',
      name: 'MaintainApply',
      component: MaintainApply,
    }]
  },{
    path: '/manager',
    name: 'ManagerLayOut',
    component: ManagerLayOut,
    children: [{
      path: '/manager',
      redirect: '/late',
    },{
      path: '/late',
      name: 'Late',
      component: Late,
    },{
      path: '/building/maintain',
      name: 'BuildingRecord',
      component: BuildingRecord,
    },{
      path: '/building/fee',
      name: 'FeeRecord',
      component: FeeRecord,
    }]
  },{
    path: '/maintain',
    name: 'MaintainLayOut',
    component: MaintainLayOut,
    children: [{
      path: '/maintain',
      redirect: '/fee',
    }, {
      path: '/fee',
      name: 'Fee',
      component: Fee,
    }, {
      path: '/record',
      name: 'Record',
      component: Record,
    }]
  },{
    path: '/admin',
    name: 'AdminLayOut',
    component: AdminLayOut,
    children: [{
      path: '/admin',
      redirect: '/buildings',
    }, {
      path: '/buildings',
      name: 'Building',
      component: Building,
    }, {
      path: '/new',
      name: 'New',
      component: New,
    },{
      path: '/change',
      name: 'Change',
      component: Change,
    }, {
      path: '/bill',
      name: 'Bill',
      component: Bill,
    }, {
      path: '/censor/0',
      name: 'CheckIn',
      component: CheckIn,
    }, {
      path: '/censor/1',
      name: 'CheckOut',
      component: CheckOut,
    }]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

