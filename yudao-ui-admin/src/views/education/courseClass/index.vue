<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入班级名称" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="班级编码" prop="classCode">
        <el-input v-model="queryParams.classCode" placeholder="请输入班级编码" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="课程编码" prop="courseCode">
        <el-input v-model="queryParams.courseCode" placeholder="请输入课程编码" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="任课老师" prop="teacherCode">
        <el-input v-model="queryParams.teacherCode" placeholder="请输入任课老师" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="课程老师" prop="classRoomCode">
        <el-input v-model="queryParams.teacherCode" placeholder="请输入课程老师" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['education:course-class:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['education:course-class:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="班级名称" align="center" prop="name" />
      <el-table-column label="课程编码" align="center" prop="courseCode" />
      <el-table-column label="任课老师" align="center" prop="teacherName" />
      <el-table-column label="课程教室" align="center" prop="classRoomName" />
      <el-table-column label="课程周几" align="center" prop="classTimeDicValue" :formatter="classWeekFormat"/>
      <el-table-column label="课程时间" align="center" prop="classDicValue" :formatter="classTimeFormat" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['education:course-class:update']">修改班级</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleStudent(scope.row)"
                     v-hasPermi="['education:course-class:student']">班级学员</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleClassing(scope.row)"
                     v-hasPermi="['education:course-class:classing']">上课考勤</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['education:course-class:delete']">删除班级</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>


    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="班级编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级编码" />
        </el-form-item>
        <el-form-item label="课程编码" prop="courseCode">
           <el-select v-model="form.courseCode" placeholder="请选课程" clearable style="width: 100%">
              <el-option v-for="dict in courseList" :key="dict.courseCode" :label="dict.courseName" :value="dict.courseCode"/>
            </el-select>
        </el-form-item>
        <el-form-item label="任课老师" prop="teacherName">
          <el-select v-model="form.teacherCode" placeholder="请选择任课老师" clearable style="width: 100%">
            <el-option v-for="dict in teacherList" :key="dict.id" :label="dict.nickname" :value="dict.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="课程教室" prop="classRoomCode">
          <el-select v-model="form.classRoomCode" placeholder="请选择课程教室" clearable style="width: 100%">
            <el-option v-for="dict in classRoomList" :key="dict.id" :label="dict.name" :value="dict.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="课程周几" prop="classStartTime">
          <el-select v-model="form.classTimeDicValue" placeholder="请选择课程周几" clearable style="width: 100%" >
            <el-option v-for="dict in classWeekList" :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="课程时长" prop="classEndTime">
           <el-select v-model="form.classDicValue" placeholder="请选择课程时长" clearable style="width: 100%" >
            <el-option v-for="dict in classTimeList" :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(学生) -->
    <el-dialog :title="title" :visible.sync="openClassing" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col span="18">
            <el-form-item label="班级名称" prop="name">
              <el-input v-model="form.name" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="18">
            <el-form-item label="课程名称" prop="name">
              <el-input v-model="form.courseCode" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="18">
            <el-form-item label="课程老师" prop="name">
              <el-input v-model="form.teacherName" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="18">
            <el-form-item label="课次编号" prop="name">
              <el-input v-model="form.courseRecordCode" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <!-- 列表 -->
              <el-table v-loading="loading" :data="classStudentList">
              <el-table-column label="学员编号" align="center" prop="studentCode" />
              <el-table-column label="学员姓名" align="center" prop="studentName" />
              <el-table-column label="学员年龄" align="center" prop="studentAge" />
              <el-table-column label="学员性别" align="center" prop="studentSex" />
              <el-table-column label="学员地址" align="center" prop="studentAddress" />
              <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.createTime) }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button size="mini" type="text" icon="el-icon-edit" @click="handleStudentAssign(form,scope.row)"
                             v-hasPermi="['zhh:class-student:classing']">到课</el-button>
                  <el-button size="mini" type="text" icon="el-icon-edit" @click="handleStudentUnIn(form, scope.row)"
                             v-hasPermi="['zhh:class-student:unclass']">请假</el-button>
                </template>
              </el-table-column>
            </el-table>

        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelStudent">完成</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(学生) -->
    <el-dialog :title="title" :visible.sync="openStudent" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col span="18">
            <el-form-item label="班级名称" prop="name">
              <el-input v-model="form.name" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
            <el-form-item label="课程名称" prop="name">
              <el-input v-model="form.courseCode" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
            <el-form-item label="课程老师" prop="name">
              <el-input v-model="form.teacherName" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="12">
            <el-form-item label="学员" prop="studentCode">
              <el-select v-model="form.studentCode" filterable @change="selectStudent" placeholder="请选择">
                <el-option v-for="item in studentList" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-button type="primary" @click="submitStudent">添加</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="8">
            <el-form-item label="学员姓名" prop="studentCode">
               <el-input :disabled="true" v-model="selectedStudent.name" />
            </el-form-item>
          </el-col>

          <el-col span="8">
            <el-form-item label="学员年龄" prop="studentCode">
               <el-input :disabled="true" v-model="selectedStudent.age" />
            </el-form-item>
          </el-col>

          <el-col span="8">
            <el-form-item label="学员性别" prop="studentCode">
               <el-input :disabled="true" v-model="selectedStudent.sex" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col span="24">
            <el-form-item label="学员地址" prop="studentCode">
              <el-input v-model="selectedStudent.address"  :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <!-- 列表 -->
              <el-table v-loading="loading" :data="classStudentList">
              <el-table-column label="学员编号" align="center" prop="studentCode" />
              <el-table-column label="学员姓名" align="center" prop="studentName" />
              <el-table-column label="学员年龄" align="center" prop="studentAge" />
              <el-table-column label="学员性别" align="center" prop="studentSex" />
              <el-table-column label="学员地址" align="center" prop="studentAddress" />
              <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.createTime) }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                             v-hasPermi="['zhh:class-student:update']">修改</el-button>
                  <el-button size="mini" type="text" icon="el-icon-delete" @click="handleStudentDelete(scope.row)"
                             v-hasPermi="['zhh:class-student:delete']">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!-- 分页组件 -->
            <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                        @pagination="getList"/>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelStudent">完成</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import { getDictDataLabel, getDictDatas, DICT_TYPE } from '@/utils/dict'
import { getClassRoomPage } from "@/api/assets/classRoom";
import { listPostUsers } from "@/api/system/user";
import { getCoursePage } from "@/api/education/course";
import { createCourseClass, updateCourseClass, deleteCourseClass, getCourseClass, getCourseClassPage, exportCourseClassExcel } from "@/api/education/courseClass";
import { createClassStudent, updateClassStudent, deleteClassStudent, getClassStudent, getClassStudentPage, exportClassStudentExcel } from "@/api/education/classStudent";
import { createCostClassRecord, updateCostClassRecord, deleteCostClassRecord, getCostClassRecord, getCostClassRecordPage, exportCostClassRecordExcel } from "@/api/education/costClassRecord";
import { get, getPage } from "@/api/student/student";


export default {
  name: "CourseClass",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程班级列表
      list: [],
      studentList: [],
      selectedStudent: {},
      classStudentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openStudent: false,
      openClassing: false,
      dateRangeClassPlanTime: [],
      dateRangeClassStartTime: [],
      dateRangeClassEndTime: [],
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        classCode: null,
        courseCode: null,
        teacherCode: null,
        classRoomCode: null,
      },
      // 表单参数
      form: {},
      studentParam: {},
      classStudentParam: {},
      studentForm: {},
      courseList:{},
      teacherList:{},
      classRoomList:{},
      classWeekList:{},
      classTimeList:{},
      courseClassRecordList:{},
      courseMap:{},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    getCoursePage().then(response => {
      this.courseList = response.data.list;
      for(var index in this.courseList ) {
        this.courseMap[this.courseList[index].courseCode] = this.courseList[index]
      }
    })
    listPostUsers(8).then(response => {
      this.teacherList = response.data;
    })
    getClassRoomPage().then(response => {
      this.classRoomList = response.data.list;
    })
    this.classWeekList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_WEEK)

  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeClassPlanTime, 'classPlanTime');
      this.addBeginAndEndTime(params, this.dateRangeClassStartTime, 'classStartTime');
      this.addBeginAndEndTime(params, this.dateRangeClassEndTime, 'classEndTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCourseClassPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    courseChange() {
      if (this.courseMap[this.form.courseCode].courseTime === "120") {
        this.classTimeList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_TIME_120)
      } else {
        this.classTimeList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_TIME_90)
      }
    },
     classWeekFormat(row, column) {
      return getDictDataLabel(DICT_TYPE.ASSIGN_CLASS_WEEK, row.classDicValue)
    },
    classTimeFormat(row, column) {
      if (!this.courseMap[row.courseCode]) {
        return
      }
      if (this.courseMap[row.courseCode].courseTime === "120") {
        return getDictDataLabel(DICT_TYPE.ASSIGN_CLASS_TIME_120, row.classTimeDicValue)
      } else {
        return getDictDataLabel(DICT_TYPE.ASSIGN_CLASS_TIME_90, row.classTimeDicValue)
      }
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 取消按钮 */
    cancelStudent() {
      this.openStudent = false;
      this.reset();
    },
    /** 取消按钮 */
    cancelClassing() {
      this.openClassing = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        classCode: undefined,
        courseCode: undefined,
        teacherCode: undefined,
        classPlanTime: undefined,
        classRoomCode: undefined,
        classStartTime: undefined,
        classEndTime: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeClassPlanTime = [];
      this.dateRangeClassStartTime = [];
      this.dateRangeClassEndTime = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程班级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCourseClass(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "管理班级学员";
      });
    },
    /** 修改按钮操作 */
    handleStudent(row) {
      this.reset();
      const id = row.id;
      this.openStudent = true;
      this.form = row;
      this.getStudent();
      this.getClassRecordList();
      this.getClassStudent();
    },
    /** 上课按钮操作 */
    handleClassing(row) {
      this.reset();
      const id = row.id;
      this.openClassing = true;
      this.form = row;
      var date = new Date();
      this.form.courseRecordCode = date.getFullYear() +''+ (date.getMonth() + 1) + date.getDate() + '-' + date.getDay() + '-' + row.teacherCode + '-' + row.classDicValue + '-' + row.classTimeDicValue
      this.getStudent();
      this.getClassStudent();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateCourseClass(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCourseClass(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    // 到课
    handleStudentAssign(form, row) {
      createCostClassRecord({
        studentCode: row.studentCode,
        studentName: row.studentName,
        classCode: form.classCode,
        courseCode: form.courseCode,
        courseRecordCode: form.courseRecordCode,
        costClassType: 1
      }).then(response => {
          this.msgSuccess("到课成功");
      })
    },
    // 请假
    handleStudentUnIn(form, row) {
      createCostClassRecord({
        studentCode: row.studentCode,
        studentName: row.studentName,
        classCode: form.classCode,
        courseCode: form.courseCode,
        courseRecordCode: form.courseRecordCode,
        costClassType: 0
      }).then(response => {
          this.msgSuccess("签到成功");
      })
    },
    selectStudent(id) {
      get(id).then(response => {
        this.selectedStudent = response.data
      })
    },
    getClassStudent() {
      this.classStudentParam = {classCode: this.form.id}
      getClassStudentPage(this.classStudentParam).then(response => {
        this.classStudentList = response.data.list
      })
    },
    getClassRecordList() {
      this.classRecordParam = {classCode: this.form.id}
      getCostClassRecordPage(this.classRecordParam).then(response => {

      })
    },
    getStudent() {
      getPage(this.studentParam).then(response => {
        this.studentList = response.data.list
      })
    },
    /** 提交按钮 */
    submitStudent() {
      createClassStudent({
        classCode : this.form.id,
        courseCode : this.form.courseCode,
        studentCode : this.selectedStudent.id,
        studentName : this.selectedStudent.name
      }).then(response => {
        this.getClassStudent()
      })
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除课程班级编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteCourseClass(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 删除按钮操作 */
    handleStudentDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除课程班级编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteClassStudent(id);
        }).then(() => {
          this.getClassStudent();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeClassPlanTime, 'classPlanTime');
      this.addBeginAndEndTime(params, this.dateRangeClassStartTime, 'classStartTime');
      this.addBeginAndEndTime(params, this.dateRangeClassEndTime, 'classEndTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有课程班级数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCourseClassExcel(params);
        }).then(response => {
          this.downloadExcel(response, '课程班级.xls');
        })
    }
  }
};
</script>
