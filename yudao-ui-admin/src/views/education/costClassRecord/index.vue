<template>
  <div class="app-container">

    <!-- 搜索工作栏  -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-select v-model="form.studentCode" filterable placeholder="请选择">
          <el-option v-for="item in studentList" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="班级编码" prop="classCode">
          <el-select v-model="form.classCode" placeholder="请选择">
            <el-option v-for="item in courseClassList" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
           </el-select>
      </el-form-item>
      <el-form-item label="请选课程" prop="courseCode">
         <el-select v-model="queryParams.courseCode" placeholder="请选课程" @change="courseChange()" clearable style="width: 100%">
            <el-option v-for="dict in courseList" :key="dict.courseCode" :label="dict.courseName" :value="dict.courseCode"/>
          </el-select>
      </el-form-item>
      <el-form-item label="消课老师" prop="costTeacherCode">
        <el-select v-model="queryParams.costTeacherCode" placeholder="请选择任课老师" clearable style="width: 100%" @keyup.enter.native="handleQuery">
          <el-option v-for="dict in teacherList" :key="dict.id" :label="dict.nickname" :value="dict.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="课程课次" prop="courseRecordCode">
        <el-input v-model="queryParams.courseRecordCode" placeholder="请输入课程记录编码" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="消课时间">
        <el-date-picker v-model="dateRangeCostTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
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
                   v-hasPermi="['zhh:cost-class-record:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['zhh:cost-class-record:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="课程课次" align="center" prop="courseRecordCode" />
      <el-table-column label="学员编号" align="center" prop="studentCode" />
      <el-table-column label="学员姓名" align="center" prop="studentName" />
      <el-table-column label="班级编码" align="center" prop="classCode" />
      <el-table-column label="课程编码" align="center" prop="courseCode" />
      <el-table-column label="消课老师" align="center" prop="costTeacherName" />
      <el-table-column label="消课时间" align="center" prop="costTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.costTime) }}</span>
        </template>
      </el-table-column>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['zhh:cost-class-record:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['zhh:cost-class-record:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="学生id" prop="studentCode">
          <el-input v-model="form.studentCode" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="班级编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级编码" />
        </el-form-item>
        <el-form-item label="课程编码" prop="courseCode">
          <el-input v-model="form.courseCode" placeholder="请输入课程编码" />
        </el-form-item>
        <el-form-item label="消课老师" prop="costTeacherCode">
          <el-input v-model="form.costTeacherCode" placeholder="请输入消课老师" />
        </el-form-item>
        <el-form-item label="课程记录编码" prop="courseRecordCode">
          <el-input v-model="form.courseRecordCode" placeholder="请输入课程记录编码" />
        </el-form-item>
        <el-form-item label="课程内容编码" prop="courseContentCode">
          <el-input v-model="form.courseContentCode" placeholder="请输入课程内容编码" />
        </el-form-item>
        <el-form-item label="消课时间" prop="costTime">
          <el-date-picker clearable size="small" v-model="form.costTime" type="date" value-format="yyyy-MM-dd" placeholder="选择消课时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createCostClassRecord, updateCostClassRecord, deleteCostClassRecord, getCostClassRecord, getCostClassRecordPage, exportCostClassRecordExcel } from "@/api/education/costClassRecord";
import { listPostUsers } from "@/api/system/user";
import { getCoursePage } from "@/api/education/course";
import { get, getPage } from "@/api/student/student";
import {  getCourseClassPage } from "@/api/education/courseClass";

export default {
  name: "CostClassRecord",
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
      // 消课记录列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      teacherList: [],
      studentList: [],
      courseClassList: [],
      courseList:{},
      dateRangeCostTime: [],
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        studentCode: null,
        classCode: null,
        courseCode: null,
        costTeacherCode: null,
        courseRecordCode: null,
        courseContentCode: null,
      },
      // 表单参数
      form: {},
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
    getPage().then(response => {
        this.studentList = response.data.list
    })
    getCourseClassPage().then(response => {
        this.courseClassList = response.data.list
    })
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCostTime, 'costTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCostClassRecordPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        studentCode: undefined,
        classCode: undefined,
        courseCode: undefined,
        costTeacherCode: undefined,
        courseRecordCode: undefined,
        courseContentCode: undefined,
        costTime: undefined,
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
      this.dateRangeCostTime = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加消课记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCostClassRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改消课记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateCostClassRecord(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCostClassRecord(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除消课记录编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteCostClassRecord(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCostTime, 'costTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有消课记录数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCostClassRecordExcel(params);
        }).then(response => {
          this.downloadExcel(response, '消课记录.xls');
        })
    }
  }
};
</script>
