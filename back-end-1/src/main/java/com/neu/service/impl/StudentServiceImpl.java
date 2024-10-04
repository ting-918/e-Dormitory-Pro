package com.neu.service.impl;

import com.neu.domain.*;
import com.neu.dto.StudentDTO;
import com.neu.mapper.*;
import com.neu.service.StudentService;
import com.neu.utils.ResultModel;
import com.neu.vo.DormitoryVO;
import com.neu.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    DormitoryMapper dormitoryMapper;
    @Autowired
    CardMapper cardMapper;
    @Autowired
    CheckInMapper checkInMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    CheckOutMapper checkOutMapper;
    @Autowired
    MaintainMapper maintainMapper;

    @Autowired
    FeeMapper feeMapper;

    private CheckIn checkin_bussiness(Boolean type, DormitoryVO dorm, String id) {
        //更新該宿舍的剩餘床位(床位-1)
        dormitoryMapper.decreaseRemain(dorm.getDid());
        //創建門禁卡
        Card card = new Card();
        card.setStudentid(id);
        card.setBid(dorm.getBid());
        card.setStatus(0);    //預設為未激活
        cardMapper.add(card);
        //創建住宿費帳單
        Bill bill = new Bill();
        bill.setStudentid(id);
        bill.setYear(null);
        bill.setPrice(2000); //設費用為2000
        bill.setStatus(0);   //預設為未繳清
        if(type){
            if(billMapper.findById(bill) != null)
                billMapper.updateStatus(bill);
            else
                billMapper.add(bill);
        }
        else
            billMapper.add(bill);
        //入住紀錄
        CheckIn checkIn = new CheckIn();
        checkIn.setStudentid(id);
        checkIn.setStatus(0); //預設已審批通過
        checkIn.setDid(dorm.getDid());
        return checkIn;
    }
    @Override
    public ResultModel<List<StudentVO>> findValidStudent() {
        ResultModel<List<StudentVO>> resultModel = new ResultModel<List<StudentVO>>();
        resultModel.setCode(200);
        resultModel.setMsg("查找成功");
        resultModel.setData(studentMapper.findValid(null));
        return resultModel;
    }

    @Override
    public ResultModel<List<StudentVO>> findAll() {
        ResultModel<List<StudentVO>> resultModel = new ResultModel<List<StudentVO>>();
        resultModel.setCode(200);
        resultModel.setMsg("查找成功");
        resultModel.setData(studentMapper.findAll());
        return resultModel;
    }
    @Override
    public ResultModel<List<Student>> addStudent(List<StudentDTO> studentList) {
        ResultModel<List<Student>> resultModel = new ResultModel<List<Student>>();
        //按性別排序studentList(實現方法詳見StudentDTO類)
        Collections.sort(studentList);
        //遍歷studentList,依序分配宿舍並新建學生紀錄
        int preGender = 0;
        DormitoryVO dorm = new DormitoryVO();
        List<Student> assignedList = new ArrayList<Student>();
        for(StudentDTO s : studentList){
            //學生DTO一>domain
            String id = s.getStudentid();
            int gender = s.getGender();
            Student student = new Student();
            student.setStudentid(id);
            student.setName(s.getName());
            student.setClasses(s.getClasses());
            student.setGender(gender);
            student.setStatus(0); //默認為在讀住宿生狀態
            student.setPwd(id.substring(id.length()-5)); //預設密碼即學號末5位
            boolean assigned = false;
            //分配宿舍操作
            if(dorm!=null && dorm.getRemain()>0 && preGender==gender)
                assigned = true;
            else {
                dorm = dormitoryMapper.findRemain(gender).get(0);
                if(dorm != null) assigned = true;
            }
            //分配成功
            if(assigned){
                //將學生分配到該宿舍,新建學生信息
                student.setDid(dorm.getDid());
                studentMapper.add(student);
                assignedList.add(student);
                //辦理入住統一事務
                CheckIn checkIn = checkin_bussiness(false,dorm, id);
                //創建入宿紀錄
                checkInMapper.add(checkIn);
                //紀錄當前學生性別,用於下一輪判斷
                preGender = gender;
            }
            //若沒能成功分配,說明找不到合適的宿舍安排
            else{
                resultModel.setCode(350);
                resultModel.setMsg("安排中斷,缺少合適的宿舍");
                resultModel.setData(assignedList);
                return resultModel;
            }
        }
        resultModel.setCode(200);
        resultModel.setMsg("安排成功");
        resultModel.setData(assignedList);
        return resultModel;
    }
    @Override
    public ResultModel changeDid(StudentDTO studentDTO) {
        ResultModel resultModel = new ResultModel();
        //獲取學生原宿舍號
        String id = studentDTO.getStudentid();
        StudentVO s = studentMapper.findById(id);
        String origin_did = s.getDid();
        //若該宿舍只剩該名學生,調宿後需刪除維修紀錄,水電紀錄
        if(dormitoryMapper.findAmount(origin_did) == 1) {
            maintainMapper.deleteByDid(origin_did);
            feeMapper.deleteByDid(origin_did);
        }
        //釋出床位(原宿舍床位+1)
        dormitoryMapper.increaseRemain(origin_did);
        //更新該學生所屬寢室信息
        String new_did = studentDTO.getDid();
        Student student = new Student();
        student.setStudentid(id);
        student.setDid(new_did);
        studentMapper.updateById(student);
        //更新門禁卡信息
        Card card = new Card();
        card.setStudentid(id);
        card.setBid(dormitoryMapper.findByDid(new_did).getBid());
        cardMapper.updateById(card);
        //修改新宿舍剩餘床位(床位-1)
        dormitoryMapper.decreaseRemain(new_did);

        resultModel.setCode(200);
        resultModel.setMsg("修改成功");
        return resultModel;
    }
    @Override
    public ResultModel checkIn(String id) {
        ResultModel resultModel = new ResultModel();
        //查找該學生信息
        StudentVO s = studentMapper.findById(id);
        //分配宿舍操作
        DormitoryVO dorm = dormitoryMapper.findRemain(s.getGender()).get(0);
        if(dorm != null){
            //將學生分配到該宿舍,更新學生信息
            Student student = new Student();
            student.setStudentid(id);
            student.setDid(dorm.getDid());
            student.setStatus(0); //設置為在讀住宿生狀態
            studentMapper.updateById(student);
            //辦理入住統一事務
            CheckIn checkIn = checkin_bussiness(true,dorm, id);
            //更新入住紀錄
            checkInMapper.updateById(checkIn);
            //刪除曾經的退宿紀錄
            checkOutMapper.deleteById(id);
        }
        //若沒能成功分配,說明找不到合適的宿舍安排
        else {
            resultModel.setCode(350);
            resultModel.setMsg("無合適的宿舍安排");
            return resultModel;
        }
        resultModel.setCode(200);
        resultModel.setMsg("入住手續完成");
        return resultModel;
    }
    @Override
    public ResultModel checkOut(String id, String reason) {
        ResultModel resultModel = new ResultModel();
        //查找該學生信息
        StudentVO s = studentMapper.findById(id);
        //若該宿舍只剩該名學生,退宿後需刪除維修紀錄,水電紀錄
        if(dormitoryMapper.findAmount(s.getDid()) == 1) {
            maintainMapper.deleteByDid(s.getDid());
            feeMapper.deleteByDid(s.getDid());
        }
        //釋出床位(宿舍床位+1)
        dormitoryMapper.increaseRemain(s.getDid());
        Student student = new Student();
        if(reason.equals("畢業")) {
            //設置學生狀態為已畢業4
            student.setStatus(4);
        }
        else{
            if(reason.equals("走讀"))
                student.setStatus(2);
            if(reason.equals("休學"))
                student.setStatus(3);
            //刪除入住紀錄
            checkInMapper.deleteById(id);
        }
        //刪除門禁卡
        cardMapper.deleteById(id);
        //修改學生信息
        student.setStudentid(id);
        student.setDid("");
        studentMapper.updateById(student);
        //更新退宿紀錄
        CheckOut checkOut = new CheckOut();
        checkOut.setStudentid(id);
        checkOut.setStatus(0); //設為已審批通過
        checkOutMapper.updateById(checkOut);

        resultModel.setCode(200);
        resultModel.setMsg("退宿手續完成");
        return resultModel;
    }

    @Override
    public ResultModel<List<StudentVO>> findByBid(String bid) {
        ResultModel<List<StudentVO>> resultModel = new ResultModel<List<StudentVO>>();
        resultModel.setData(studentMapper.findValid(bid));
        resultModel.setCode(200);
        resultModel.setMsg("查詢完成");
        return resultModel;
    }
}
