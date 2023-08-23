<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="课程编码" prop="courseCode">
        <el-input v-model="queryParams.courseCode" placeholder="请输入课程编码" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="排课时长">
        <el-date-picker v-model="dateRangeCourseTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
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
                   v-hasPermi="['education:course:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['education:course:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="课程编码" align="center" prop="courseCode" />
      <el-table-column label="排课时长" align="center" prop="courseTime" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['education:course:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleAssign(scope.row)"
                     v-hasPermi="['education:course:assign']">排课</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['education:course:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程编码" prop="courseCode">
          <el-input v-model="form.courseCode" placeholder="请输入课程编码" />
        </el-form-item>
        <el-form-item label="排课时长" prop="courseTime">
          <el-input v-model="form.courseTime" placeholder="选择排课时长:1:1.5小时;2:2小时" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 对话框(分派) -->
    <el-dialog :title="title" :visible.sync="openAssign" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col span="8">
            <el-form-item label="课程名称" prop="courseName" span = "8">
              <el-input v-model="form.courseName" :disabled="true" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
          <el-col span="8">
            <el-form-item label="课程编码" prop="courseCode" span = "8">
              <el-input v-model="form.courseCode" :disabled="true" placeholder="请输入课程编码" />
            </el-form-item>
          </el-col>
          <el-col span="8">
            <el-form-item label="排课时长" prop="courseTime" span = "8">
              <el-input v-model="form.courseTime" :disabled="true" placeholder="" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="12">
            <el-form-item label="班级教室" prop="courseTime">
              <el-select v-model="form.classRoomId" placeholder="请选择">
                <el-option
                  v-for="item in classRoomList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="班级老师" prop="courseTime">
              <el-select v-model="form.teacherId" placeholder="请选择">
                <el-option
                  v-for="item in postUserList"
                  :key="item.id"
                  :label="item.nickname"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col span="12">
            <el-form-item label="班级周几" prop="courseTime">
              <el-select v-model="form.classDicValue" placeholder="请选择">
                <el-option
                  v-for="item in classDicDataList"
                  :key="item.id"
                  :label="item.label"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item label="班级时间" prop="courseTime">
              <el-select v-model="form.classTimeDicValue" placeholder="请选择">
                <el-option
                  v-for="item in timeDicDataList"
                  :key="item.id"
                  :label="item.label"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAssign">确 定</el-button>
        <el-button @click="cancelAssign">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createCourse, updateCourse, deleteCourse, getCourse, getCoursePage, exportCourseExcel, assignCourse } from "@/api/education/course";
import { getClassRoomPage } from "@/api/assets/classRoom";
import { getDicts } from "@/api/system/dict/data";
import { listPostUsers } from "@/api/system/user";

export default {
  name: "Course",
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
      // 课程列表
      list: [],
      classRoomList: [],
      postUserList: [],
      classDicDataList: [],
      timeDicDataList: [],
      time90DicDataList: [],
      time120DicDataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openAssign: false,
      dateRangeCourseTime: [],
      dateRangeClassPlanTime: [],
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        courseName: null,
        courseCode: null,
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
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCourseTime, 'courseTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCoursePage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 教室列表 */
    getClassRoomList() {
      this.loading = true;
      // 处理查询参数
      let params = {};

      // 执行查询
      getClassRoomPage(params).then(response => {
        this.classRoomList = response.data.list;
      });

      listPostUsers(8).then(response => {
        this.postUserList = response.data;
      })
    },
    /** 教室列表 */
    getDicData(courseTimeType) {

      // 处理查询参数
      let params = {};

      // 排课-周几
      getDicts('assign_class_week').then(response => {
        this.classDicDataList = response.data;
      });

    if (courseTimeType === '90') {
      // 排课时间1.5h
      getDicts('assign_class_time_90').then(response => {
        this.timeDicDataList = response.data;
      });
    } else if (courseTimeType === '60') {
       // 排课时间1h
       getDicts('assign_class_time_60').then(response => {
         this.timeDicDataList = response.data;
       });
     } else {
      // 排课时间2h
      getDicts('assign_class_time_120').then(response => {
        this.timeDicDataList = response.data;
      });
    }
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 取消按钮 */
    cancelAssign() {
      this.openAssign = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        courseName: undefined,
        courseCode: undefined,
        courseTime: undefined,
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
      this.dateRangeCourseTime = [];
      this.dateRangeClassPlanTime = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 修改按钮操作 */
    handleAssign(row) {
      this.reset();
      this.getClassRoomList();
      this.getDicData(row.courseTime);

      const id = row.id;
      getCourse(id).then(response => {
        this.form = response.data;
        this.openAssign = true;
        this.title = "分派课程";
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
          updateCourse(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCourse(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 提交按钮 */
    submitAssign() {
      // 修改的提交
      if (this.form.id == null) {
        this.msgError("无效的课程");
        return;
      }
      // 修改的提交
      if (this.form.classRoomId == null) {
        this.msgError("请选择教室");
        return;
      }
      assignCourse(this.form).then(response => {
        this.msgSuccess("排课成功");
        this.openAssign = false;
        this.handleQuery()
      });
      return;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除课程编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteCourse(id);
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
      this.addBeginAndEndTime(params, this.dateRangeCourseTime, 'courseTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有课程数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCourseExcel(params);
        }).then(response => {
          this.downloadExcel(response, '课程.xls');
        })
    }
  }
};
</script>
